package application.chatboard;

import java.net.URL;
import java.util.ResourceBundle;

import application.DataSender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ChatBoard implements Initializable{

	private static String connectionString;
	private String clientMessage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connectionStatus.setText(connectionString);
	}
	
	public static void setConnectionString(String connectionString) {
		ChatBoard.connectionString = connectionString;
	}
	
	public static String getConnectionString() {
		return connectionString;
	}
	
	@FXML
	public void sendMsg() {
		clientMessage = textMessage.getText().toString();
		
		new DataSender(clientMessage).runOperation();
	}
	
	@FXML
	public void refreshMsg() {
		
	}
	
	@FXML private Label messages;
	@FXML private TextField textMessage;
	
	@FXML private Label connectionStatus;
}
