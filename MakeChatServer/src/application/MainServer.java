package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
				new Thread(new ClientHandler(this, clientSocket.getInputStream())).start();
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
	private InputStream clientInputStream;

	public ClientHandler(MainServer server, InputStream clientInputStream) {
		this.server = server;
		this.clientInputStream = clientInputStream;
	}

	@Override
	public void run() {
		
		try {
			// when there is a new message, broadcast to all
			DataInputStream dataInputStream = new DataInputStream(this.clientInputStream);
			
			while(true) {
				String message = dataInputStream.readUTF();
				System.out.println(message);
				server.broadcastMessages(message);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage() + " Shit");
			e.printStackTrace();
		}
	}
}
