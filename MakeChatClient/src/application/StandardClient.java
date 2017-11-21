package application;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class StandardClient {
	
	private String serverAddress;
	private int portNumber;
	private boolean connectionCheck;
	
	private Socket clientSocket;
	
	public StandardClient() {
		connectionCheck = false;
	}
	
	public boolean connectToServer() {
		try {
			clientSocket = new Socket(serverAddress, portNumber);
			if(clientSocket.isConnected()) {
				connectionCheck = true;
			}
		} catch (UnknownHostException e) {
			connectionCheck = false;
			e.printStackTrace();
		} catch (IOException e) {
			connectionCheck = false;
			e.printStackTrace();
		}
		
		return connectionCheck;
	}
	
	//Getter and Setter
	public String getServerAddress() {
		return serverAddress;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
}
