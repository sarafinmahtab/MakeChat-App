package application;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import application.chatboard.ChatBoard;


public class StandardClient extends Thread {
	
	private String host;
	private int port;
	private String userName;
    public ChatBoard chatController;

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

//			// ask for a nickname
//			Scanner sc = new Scanner(System.in);
//			System.out.print("Enter a nickname: ");
//			nickname = sc.nextLine();
//
//			// read messages from keyboard and send to server
//			System.out.println("Send messages: ");
			
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
	private String userName;
	private ChatBoard chatController;

	public ReceivedMessagesHandler(Socket clientSocket, String userName, ChatBoard chatController) {
		this.clientSocket = clientSocket;
		this.userName = userName;
		this.chatController = chatController;
	}

	@Override
	public void run() {
		try {
			// receive server messages and print out to screen
			DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
			
			while(clientSocket.isConnected()) {
				String message = dataInputStream.readUTF();
				System.out.println(message);
				
				chatController.addToChat(message);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
