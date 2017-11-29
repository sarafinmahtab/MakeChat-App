package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
		
	public static ArrayList<String> connectedDeviceList = new ArrayList<>();
	private ObservableList<String> dataObservableList;
	private Thread t;
	
	@FXML
	public void openServer() {
		t = new MainServer(3002);
		t.start();
		
		connectionStatus.setText("Server is running!!");
	}
	
	@FXML
	public void stopServer() throws InterruptedException {
		if(t.isAlive()) {
			t.join();
		}
		
		System.exit(0);
	}
	
	@FXML
	public void devices() {
        final Stage deviceListStage = new Stage();
        deviceListStage.initModality(Modality.APPLICATION_MODAL);
        deviceListStage.initOwner(Main.getStage());
        
        dataObservableList = FXCollections.observableArrayList();
        
        for(int j = 0; j < connectedDeviceList.size(); j++) {
        	dataObservableList.add(connectedDeviceList.get(j));
        }
        
        ListView<String> list = new ListView<String>();
        list.setItems(dataObservableList);
        
        StackPane stack = new StackPane();
        stack.getChildren().add(list);
        
        Scene dialogScene = new Scene(stack, 450, 200);
        deviceListStage.setScene(dialogScene);
        deviceListStage.setTitle("List of Connected Devices");
        deviceListStage.show();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setStatus(String status) {
		System.out.println(status);
	}

	@FXML private Label connectionStatus;
}
