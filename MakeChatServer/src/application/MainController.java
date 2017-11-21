package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainController implements Initializable {
		
	private Thread thread;
	
	@FXML
	public void openServer() {
		thread = new MainServer(this);
		thread.start();
		
		connectionStatus.setText("Server is running!!");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setStatus(String status) {
		System.out.println(status);
	}

	@FXML private Label connectionStatus;
}
