package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MainServer extends Thread {
	
	private ServerSocket serverSocket;
	private Socket server;
	
	final int portNumber = 3000;
	
	public MainServer(MainController mainController) {
		
		try {
			serverSocket = new ServerSocket(portNumber);
			/*server instantiates a ServerSocket object,
			denoting which port number communication is to occur on. */
		    serverSocket.setSoTimeout(30000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			server = serverSocket.accept(); //This method waits until a client connects to the server on the given port
			
//			String ipAddress = server.getRemoteSocketAddress().toString();
			
			if(server.isConnected()) {
				
			}
		} catch(SocketTimeoutException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
