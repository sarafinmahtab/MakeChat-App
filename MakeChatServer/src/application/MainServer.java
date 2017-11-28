package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class MainServer extends Thread {
	
	private int port;
	private List<DataOutputStream> clientsList;
	private ServerSocket serverSocket;
	
	public MainServer(int port) {
		this.port = port;
		this.clientsList = new ArrayList<DataOutputStream>();
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(port) {
				protected void finalize() throws IOException {
					this.close();
				}
			};
			System.out.println("Port is now open.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true) {
			// accepts a new client
			Socket clientSocket;
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Connection established with client: " + clientSocket.getRemoteSocketAddress());

				MainController.connectedDeviceList.add(clientSocket.getRemoteSocketAddress().toString());
				// add client message to list
				this.clientsList.add(new DataOutputStream(clientSocket.getOutputStream()));
				
				// create a new thread for client handling
				new Thread(new ClientHandler(this, clientSocket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	void broadcastMessages(String msg) throws IOException{
		for (DataOutputStream outputStream : this.clientsList) {
			outputStream.writeUTF(msg);
		}
	}
}

class ClientHandler implements Runnable {

	private MainServer server;
	private Socket serverSocket;

	public ClientHandler(MainServer server, Socket serverSocket) {
		this.server = server;
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		
		try {
			// when there is a new message, broadcast to all
			DataInputStream dataInputStream = new DataInputStream(serverSocket.getInputStream());
			
			while(serverSocket.isConnected()) {
				String message = dataInputStream.readUTF();
				System.out.println(message);
				server.broadcastMessages(message);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
