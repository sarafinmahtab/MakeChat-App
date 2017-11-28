package application;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import application.chatboard.ChatBoard;


public class StandardClient extends Thread {
	
	private String host;
	private int port;
    public ChatBoard chatController;
    
    private String userName;
	private Socket client;
	
	public StandardClient(String host, int port, String userName, ChatBoard controller) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.chatController = controller;
	}

	@Override
	public void run() {
		try {
			client = new Socket(host, port);
			
			System.out.println("Client successfully connected to server!");

			// create a new thread for server messages handling
			new Thread(new ReceivedMessagesHandler(client, userName, chatController)).start();

			DataSender.setOutputStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ReceivedMessagesHandler implements Runnable {

	private Socket clientSocket;
	private ChatBoard chatController;
	
	private String userName;
    private String connectionText;
	
	public ReceivedMessagesHandler(Socket clientSocket, String userName, ChatBoard chatController) {
		this.clientSocket = clientSocket;
		this.userName = userName;
		this.chatController = chatController;
	}

	@Override
	public void run() {
		try {
			connectionText = "Connected To Server";
			
			chatController.initConnectionStatus(connectionText, userName);
			
			// receive server messages and print out to screen
			DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
			
			while(clientSocket.isConnected()) {
				String message = dataInputStream.readUTF();
				System.out.println(message);
				
				chatController.addToChat(message);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			connectionText = "FAILED";
			
			chatController.initConnectionStatus(connectionText, userName);
			e.printStackTrace();
		} catch (NullPointerException e) {
			connectionText = "FAILED";
			chatController.initConnectionStatus(connectionText, userName);
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
