package application.signin;

import java.net.URL;
import java.util.ResourceBundle;

import application.Navigation;
import application.StandardClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class SignIn implements Initializable {
	
	private String userName;
	private String email;
	private String serverAddress;
	private String portNo;
	
	private boolean connectRequirementCheck;
	private boolean connectionCheck;
	
	private StandardClient client;
	
	@FXML
	public void handleConnectButton() {
		connectRequirementCheck = true;
		
		userName = userNameEntry.getText().toString();
		email = emailEntry.getText().toString();
		serverAddress = serverAddressEntry.getText().toString();
		portNo = portNumberEntry.getText().toString();
		
		if(serverAddress.equals("")) {
			connectRequirementCheck = false;
			serverAddressError.setText("Server Address is required");
		}
		
		if(portNo.equals("")) {
			connectRequirementCheck = false;
			serverAddressError.setText("Port number is required");
		}
		
		if(serverAddress.equals("") && portNo.equals("")) {
			connectRequirementCheck = false;
			serverAddressError.setText("Server Address and Port number is required");
		}
		
		if(email.equals("")) {
			connectRequirementCheck = false;
			emailError.setText("Email is required");
		}
		
		if(userName.equals("")) {
			connectRequirementCheck = false;
			userNameError.setText("Username is required");
		}
		
		connectRequirementCheck = true; // This should be removed
		if(connectRequirementCheck) {
			connectStatusResult.setTextFill(Color.rgb(0, 0, 210));
			connectStatusResult.setText("Connecting to Server...");

			client = new StandardClient();

//			int portNumber = Integer.parseInt(portNo);
//			client.setServerAddress(serverAddress);
//			client.setPortNumber(portNumber);
			
			client.setServerAddress("192.168.0.63");	//This should be removed	
			client.setPortNumber(3000);	//This should be removed
			connectionCheck = client.connectToServer();
			
			if(connectionCheck) {
				Navigation.loadContent(Navigation.CHAT_Board);
			} else {
				connectStatusResult.setTextFill(Color.rgb(232,0,5));
				connectStatusResult.setText("No Server found!!");
			}
		} else {
			connectStatusResult.setTextFill(Color.rgb(232,0,5));
			connectStatusResult.setText("Connection not possible!! Please fillup all the fields.");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userNameEntry.setFocusTraversable(false);
		emailEntry.setFocusTraversable(false);
		serverAddressEntry.setFocusTraversable(false);
		portNumberEntry.setFocusTraversable(false);
	}
	
	@FXML private Label userNameError;
	@FXML private Label emailError;
	@FXML private Label serverAddressError;
	@FXML private Label connectStatusResult;
	
	@FXML private TextField userNameEntry;
	@FXML private TextField emailEntry;
	@FXML private TextField serverAddressEntry;
	@FXML private TextField portNumberEntry;
	
	@FXML private Button connectButton;
}
