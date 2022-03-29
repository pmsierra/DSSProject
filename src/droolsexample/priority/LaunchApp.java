package droolsexample.priority;
	
import java.io.Console;		
import java.util.Scanner;

import db.GUI.DepartmentMenuController;
import db.GUI.LogInController;
import db.GUI.ManagementMenuController;
import db.GUI.ShowWishlistController;

import java.io.IOException;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class LaunchApp extends Application {
	
	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		SQLiteManager manager = new SQLiteManager();
		//manager.Stablish_connection();
        //manager.Create_tables();
        //manager.Close_connection();
        //manager = null;
		
		try {
			
			LogInController.setValues(primaryStage);
			Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/LogInView.fxml"));
			primaryStage.setTitle("Log in page");
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			stage = primaryStage;
			primaryStage.show();
		} catch (IOException fatal_error) {
			fatal_error.printStackTrace();
			System.exit(0);
		}
	}
	
	

	public static void main(String[] args) {
		
		launch(args);
	}
	
	public static Stage getStage() {
		return stage;
	}
}
