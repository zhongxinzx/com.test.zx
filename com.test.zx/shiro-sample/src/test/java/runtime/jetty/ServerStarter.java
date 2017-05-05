package runtime.jetty;

/**
 * -Xms256m -Xmx1024m -XX:PermSize=256M -XX:MaxPermSize=256M
 */
public class ServerStarter {

	public static void main(String[] args) throws Exception {
		new RuntimeServer().start();
	}

}
