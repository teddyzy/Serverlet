package ServerContain;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServletProcessor {
	public static void processServletRequest(Request req, 
			Response res) throws Exception{
		req.parse();
		String uri = req.getUri();	
		String servletName = getServerName(uri);
		System.out.println("Processing servlet: " + servletName);
		Servlet servlet = loadServlet(servletName);
		callService(servlet, req, res);
	}
	
	private static Servlet loadServlet(String servletName) throws MalformedURLException {
		String servletURL = "../" + servletName.replace('.', '/');
		
		File file = new File(servletURL);

		@SuppressWarnings("deprecation")
		URL url = file.toURL();
		URLClassLoader loader = new URLClassLoader(
				new URL[] { url }, Thread.currentThread().getContextClassLoader());
		Servlet servlet = null;
		
		try {
			@SuppressWarnings("unchecked")
			Class<Servlet> servletClass = (Class<Servlet>) loader
					.loadClass(servletName);
			servlet = (Servlet) servletClass.newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return servlet;
	}
	
	private static void callService(Servlet servlet, ServletRequest request,
			ServletResponse response) {
		try {
			servlet.service(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getServerName(String uri)
		throws Exception{
		SAXReader reader = new SAXReader();
		File file = new File("web.xml");
		String servletName=null;
		String servletClass=null;
		Document document = reader.read(file);
		Element root = document.getRootElement();
		List<Element> mapList =root.elements("servlet-mapping");
		for(int i=0;i<mapList.size();i++){
			Element ele = mapList.get(i);
			if(ele.elementText("url-pattern").equals(uri)){
			servletName=ele.elementText("servlet-name");
			break;
			}
		}
		List<Element> servletList =root.elements("servlet");
		for(int i=0;i<servletList.size();i++){
			Element ele = servletList.get(i);
			if(ele.elementText("servlet-name").equals(servletName)){
				servletClass=ele.elementText("servlet-class");
				System.out.println(servletClass);
				break;
			}
		}
		return servletClass;
	
	}
	
}
