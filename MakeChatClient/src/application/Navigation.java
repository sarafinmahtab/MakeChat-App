package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;


public class Navigation {
	
	public static final String MAIN = "/application/Main.fxml";
	
	public static final String SIGNIN = "/application/signin/Signin.fxml";
	public static final String CHAT_Board = "/application/chatboard/ChatBoard.fxml";
	
	private static MainController MAINCONTROLLER;

	public static void setMainController(MainController mainController) {
		Navigation.MAINCONTROLLER = mainController;
	}
	
	public static void loadContent(String fxml) {
		try {
			MAINCONTROLLER.setVista(FXMLLoader.load(Navigation.class.getResource(fxml)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
