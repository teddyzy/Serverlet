package ServerContain;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Server {
 
 // 关闭服务命令
 private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
 
 public static void main(String[] args) {
	 Server server = new Server();
 //等待连接请求
 server.await();
 }
 
 public void await() {
 ServerSocket serverSocket = null;
 int port = 8080;
 try {
 //服务器套接字对象
 serverSocket = new ServerSocket(port);
 System.out.println("服务器正在监视端口： " + serverSocket.getLocalPort());
 } catch (IOException e) {
 e.printStackTrace();
 System.exit(1);
 }
 
 // 循环等待请求
 while (true) {
 Socket socket = null;
 InputStream input = null;
 OutputStream output = null;
 try {
 //等待连接，连接成功后，返回一个Socket对象
 socket = serverSocket.accept();
 input = socket.getInputStream();
 output = socket.getOutputStream();
 
 // 创建Request对象并解析
 Request request = new Request(input);
 
 // 检查是否是关闭服务命令

 // 创建 Response 对象
 Response response = new Response(output);
 

 ServletProcessor.processServletRequest(request, response);
 
 // 关闭 socket
 socket.close();
 
 } catch (Exception e) {
 e.printStackTrace();
 System.exit(1);
 }
 }
 }
}
