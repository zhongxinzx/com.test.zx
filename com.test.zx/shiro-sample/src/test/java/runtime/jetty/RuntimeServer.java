package runtime.jetty;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class RuntimeServer {
	public static final String DEFAULT_PATH = "applicationContext.properties";
	
	private int port = 8000;

	private String host = "127.0.0.1";
	private String propertyPath = DEFAULT_PATH;
	private String contextPath = "/zx";

	private String warApp = "src/main/webapp";

	/**
	 * 
	 * @param propertyPath
	 * @return
	 */
	public RuntimeServer propertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
		return this;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public RuntimeServer port(int port) {
		this.port = port;
		return this;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public RuntimeServer host(String host) {
		this.host = host;
		return this;
	}

	/**
	 * @param warApp
	 *            the warApp to set
	 */
	public RuntimeServer warApp(String warApp) {
		this.warApp = warApp;
		return this;
	}

	public void start() throws Exception {

		//
		long begin = System.nanoTime();// .currentTimeMillis();
		//
		Server server = new Server();

		// thread pool
		// BoundedThreadPool threadPool = new BoundedThreadPool();
		// threadPool.setMaxThreads(100);
		// server.setThreadPool(threadPool);
		if (null != propertyPath) {
			/*ClassPathResource classPathResource = new ClassPathResource(propertyPath);
			Properties properties = new Properties();
			properties.load(classPathResource.getInputStream());

			port = Integer.parseInt(properties.getProperty("http.port"));
			contextPath = properties.getProperty("http.context.path");
			warApp = properties.getProperty("http.web.root.path");*/
		}
		//
		Connector connector = new SelectChannelConnector();

		/**
		 * log.warn(76) | header full: java.lang.ArrayIndexOutOfBoundsException:
		 * 4096 java.lang.ArrayIndexOutOfBoundsException: 4096 at
		 * org.mortbay.io.ByteArrayBuffer.poke(ByteArrayBuffer.java:268)
		 */
		// connector.setHeaderBufferSize(8192);

		connector.setPort(Integer.getInteger("jetty.port", port).intValue());
		// connector.setHost("127.0.0.1");
		server.setConnectors(new Connector[] { connector });

		//
		WebAppContext webAppContext = new WebAppContext(warApp, contextPath);
		// webAppContext.setContextPath(contextPath);
		// webAppContext.setWar(warUrl);
		/* Solving files are locked on Windows and can't be replaced */
		// if reference by jar, so the "webdefault.xml" must move out
		webAppContext.setDefaultsDescriptor("src/test/java/runtime/jetty/webdefault.xml");
		server.setHandler(webAppContext);

		host = (null == connector.getHost() ? host : connector.getHost());

		// RmiRegistry
		// new org.springframework.remoting.rmi.RmiRegistryFactoryBean().
		// afterPropertiesSet();

		// jmx support
		/*
		 * An example of how to start up Jetty with JMX programatically:
		 * http://docs.codehaus.org/display/JETTY/JMX
		 */
		// MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		// if (null == mBeanServer) {
		// mBeanServer = MBeanServerFactory.createMBeanServer();
		// }
		// MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		// server.getContainer().addEventListener(mBeanContainer);
		// mBeanContainer.start();
		// JMXServiceURL jmxServiceURL = new JMXServiceURL(
		// "service:jmx:rmi:///jndi/rmi://127.0.0.1:1099/jmxconnector");
		// JMXConnectorServer jmxConnectorServer =
		// JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null,
		// mBeanServer);
		// jmxConnectorServer.start();
		/* jetty log */
		// RequestLogHandler requestLogHandler = new RequestLogHandler();
		// File logFile = new File(new File("").getAbsoluteFile(), "jetty.log");
		// logFile.createNewFile();
		// NCSARequestLog requestLog = new
		// NCSARequestLog(logFile.getAbsolutePath());
		// requestLog.setExtended(false);
		// requestLogHandler.setRequestLog(requestLog);
		// server.addHandler(requestLogHandler);
		/* */
		// server.setStopAtShutdown(true);
		// server.setSendServerVersion(true);
		// SslListener listener = new SslListener();
		// listener.setMinThreads(10);
		// listener.setMaxThreads(200);
		// String strUrl = LoadPath.getRootPath(null);
		// listener.setKeystore(strUrl+"etc/maxnet.store");
		// listener.setKeyPassword("maxnet");
		// listener.setPassword("maxnet");
		// //listener.setHost(addr.getHostAddress());
		// listener.setPort(httpsPort);
		// listener.setProtocol("SSL");
		// listener.setConfidentialScheme("https");
		// service.addListener(listener);
		try {
			server.start();
			String url = "http://" + host + ":" + port + contextPath;
			System.out.println(
					"[Jetty Server started in " + (System.nanoTime() - begin) / 1000 / 1000 / 1000 + "s]: " + url);
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}

	}
}
