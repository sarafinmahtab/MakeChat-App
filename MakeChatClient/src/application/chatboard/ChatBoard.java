package application.chatboard;

import java.net.URL;
import java.util.ResourceBundle;

import application.DataSender;
import bubble.BubbleSpec;
import bubble.BubbledLabel;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class ChatBoard implements Initializable{

	private String clientMessage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connectionStatus.setText("Connected to Server");
	}
	
	@FXML
	public void sendMsg() {
		clientMessage = textMessage.getText().toString();
		textMessage.clear();
		
		if(!clientMessage.isEmpty()) {
			new DataSender(clientMessage).runOperation();
		}
	}
	
	public synchronized void addToChat(String message) {
		
        Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
//                Image image = new Image(getClass().getClassLoader().getResource("images/" + msg.getPicture() + ".png").toString());
//                ImageView profileImage = new ImageView(image);
//                profileImage.setFitHeight(32);
//                profileImage.setFitWidth(32);
                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText(message);

                bl6.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
                HBox x = new HBox();
                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                
                x.getChildren().addAll(bl6);
                return x;
            }
        };

        othersMessages.setOnSucceeded(event -> {
            chatPane.getItems().add(othersMessages.getValue());
        });


//        if (msg.getName().equals(usernameLabel.getText())) {
//            Thread t2 = new Thread(yourMessages);
//            t2.setDaemon(true);
//            t2.start();
//        }
        
        Thread t = new Thread(othersMessages);
        t.setDaemon(true);
        t.start();
	}
	
	@FXML private Label messages;
	@FXML private TextField textMessage;
	
	@FXML private ListView<HBox> chatPane;
	@FXML private Label connectionStatus;
}
