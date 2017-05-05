package runtime.tomcat;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class TomcatStarter {

	public static void main(String[] args) {
		Tomcat tomcat = new Tomcat();
		try {
			String projcetPath = new File("").getAbsolutePath();
			tomcat.setPort(8080);
			tomcat.addWebapp("/zx", projcetPath + "/src/main/webapp");
			tomcat.start();
			tomcat.getServer().await();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	
}