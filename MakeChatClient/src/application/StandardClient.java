package application;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class StandardClient extends Thread {
	
	private String host;
	private int port;
//	private String nickname;
	private Socket client;

	public StandardClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			client = new Socket(host, port);
			
			System.out.println("Client successfully connected to server!");

			// create a new thread for server messages handling
			new Thread(new ReceivedMessagesHandler(client.getInputStream())).start();

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

	private InputStream serverInputStream;

	public ReceivedMessagesHandler(InputStream serverInputStream) {
		this.serverInputStream = serverInputStream;
	}

	@Override
	public void run() {
		String message;
		try {
			// receive server messages and print out to screen
			DataInputStream dataInputStream = new DataInputStream(this.serverInputStream);
			
			while(true) {
				message = dataInputStream.readUTF();
				System.out.println(message);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
