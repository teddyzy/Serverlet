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
 
 // �رշ�������
 private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
 
 public static void main(String[] args) {
	 Server server = new Server();
 //�ȴ���������
 server.await();
 }
 
 public void await() {
 ServerSocket serverSocket = null;
 int port = 8080;
 try {
 //�������׽��ֶ���
 serverSocket = new ServerSocket(port);
 System.out.println("���������ڼ��Ӷ˿ڣ� " + serverSocket.getLocalPort());
 } catch (IOException e) {
 e.printStackTrace();
 System.exit(1);
 }
 
 // ѭ���ȴ�����
 while (true) {
 Socket socket = null;
 InputStream input = null;
 OutputStream output = null;
 try {
 //�ȴ����ӣ����ӳɹ��󣬷���һ��Socket����
 socket = serverSocket.accept();
 input = socket.getInputStream();
 output = socket.getOutputStream();
 
 // ����Request���󲢽���
 Request request = new Request(input);
 
 // ����Ƿ��ǹرշ�������

 // ���� Response ����
 Response response = new Response(output);
 

 ServletProcessor.processServletRequest(request, response);
 
 // �ر� socket
 socket.close();
 
 } catch (Exception e) {
 e.printStackTrace();
 System.exit(1);
 }
 }
 }
}
