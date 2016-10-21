package ServerContain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class Request implements ServletRequest{  

private InputStream input = null;  
private String uri = null;   
private Map<String,String> paramters = new HashMap<String,String>();

public Request(InputStream input) {  
  this.input = input;  
}  

public void parse() {  

  StringBuffer request = new StringBuffer(2048);  
  int i;  
  byte[] buffer = new byte[2048];  
  try {  
    i = input.read(buffer);  
  }  
  catch (IOException e) {  
    e.printStackTrace();  
    i = -1;  
  }  
  for (int j=0; j<i; j++) {  
    request.append((char) buffer[j]);  
  }  
  System.out.print(request.toString());  
  uri = parseUri(request.toString());  
}  

private String parseUri(String requestString) {  
  int index1, index2,index3,index4,index5,index6;  
  String key1=null;
  String value1=null;
  String key2=null;
  String value2=null;
  index1 = requestString.indexOf(' ');  
  if (index1 != -1) {  
    index2 = requestString.indexOf(' ', index1 + 1);  
    if (index2 > index1)  {
    	index3=requestString.indexOf("?");
    	index4=requestString.indexOf("&");
    	index5=requestString.indexOf("=");
    	index6=requestString.indexOf("=", index5+1);
    	key1= requestString.substring(index3 + 1, index5);
    	value1= requestString.substring(index5 + 1, index4);
    	key2= requestString.substring(index4 + 1, index6);
    	value2=requestString.substring(index6 + 1, index2);
    	paramters.put(key1, value1);
    	paramters.put(key2, value2);
    	 return requestString.substring(index1 + 1, index3);  
    }
     
  }  
  return null;  
}  

public String getUri() {  
  return uri;  
}

@Override
public AsyncContext getAsyncContext() {
	return null;
}

@Override
public Object getAttribute(String arg0) {
	return null;
}

@Override
public Enumeration<String> getAttributeNames() {
	return null;
}

@Override
public String getCharacterEncoding() {
	return null;
}

@Override
public int getContentLength() {
	return 0;
}

@Override
public long getContentLengthLong() {
	return 0;
}

@Override
public String getContentType() {
	return null;
}

@Override
public DispatcherType getDispatcherType() {
	return null;
}

@Override
public ServletInputStream getInputStream() throws IOException {
	return null;
}

@Override
public String getLocalAddr() {
	return null;
}

@Override
public String getLocalName() {
	return null;
}

@Override
public int getLocalPort() {
	return 0;
}

@Override
public Locale getLocale() {
	return null;
}

@Override
public Enumeration<Locale> getLocales() {
	return null;
}

@Override
public String getParameter(String arg0) {
	
	if(this.paramters.get(arg0)!=null)
		return this.paramters.get(arg0);
	return null;
}

@Override
public Map<String, String[]> getParameterMap() {
	return null;
}

@Override
public Enumeration<String> getParameterNames() {
	return null;
}

@Override
public String[] getParameterValues(String arg0) {
	return null;
}

@Override
public String getProtocol() {
	return null;
}

@Override
public BufferedReader getReader() throws IOException {
	return null;
}

@Override
public String getRealPath(String arg0) {
	return null;
}

@Override
public String getRemoteAddr() {
	return null;
}

@Override
public String getRemoteHost() {
	return null;
}

@Override
public int getRemotePort() {
	return 0;
}

@Override
public RequestDispatcher getRequestDispatcher(String arg0) {
	return null;
}

@Override
public String getScheme() {
	return null;
}

@Override
public String getServerName() {
	return null;
}

@Override
public int getServerPort() {
	return 0;
}

@Override
public ServletContext getServletContext() {
	return null;
}

@Override
public boolean isAsyncStarted() {
	return false;
}

@Override
public boolean isAsyncSupported() {
	return false;
}

@Override
public boolean isSecure() {
	return false;
}

@Override
public void removeAttribute(String arg0) {
	
}

@Override
public void setAttribute(String arg0, Object arg1) {

}

@Override
public void setCharacterEncoding(String arg0)
		throws UnsupportedEncodingException {

}

@Override
public AsyncContext startAsync() throws IllegalStateException {
	return null;
}

@Override
public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1)
		throws IllegalStateException {
	return null;
 }  

}  
