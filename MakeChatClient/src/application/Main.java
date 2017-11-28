package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			stage.setScene(createScene(loadMainPane()));
			stage.setTitle("MakeChat - Chat with Others");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Pane loadMainPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		
		Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(Navigation.MAIN));
		
		MainController mainController = loader.getController();
		
		Navigation.setMainController(mainController);
		Navigation.loadContent(Navigation.SIGNIN);
		
		return mainPane;
	}
	
	private Scene createScene(Pane loadPane) {
		Scene scene = new Scene(loadPane);
		scene.getStylesheets().setAll(getClass().getResource("application.css").toExternalForm());
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
