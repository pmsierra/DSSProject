package db.GUI;

import java.io.IOException;
import db.GUI.DepartmentMenuController;
import db.GUI.ManagementMenuController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import droolsexample.priority.Department;
import droolsexample.priority.Hospital;
import droolsexample.priority.SQLiteManager;
import droolsexample.priority.User;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LogInController implements Initializable {
	
	// -----> CLASS ATRIBUTES <-----
	

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane startUpMenu;
	@FXML
	private Pane logInPane;
	@FXML
	private Button logInButton;
	@FXML
	private Button signUpButton;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField; 
	
	private String user_type;
	
	private SQLiteManager SQL_manager_object;
	
	private static Stage primary_Stage;
	
	private static Stage stage;
	// -----> ESSENTIAL METHODS <-----

	public LogInController() {
		// Default constructor
	}

	public static void setValues(Stage primaryStage) {
		primary_Stage = primaryStage;

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		logInButton.setOnAction((ActionEvent event) -> {


			String user_name = userNameField.getText();
			String password = passwordField.getText();
			SQL_manager_object = new SQLiteManager();
			boolean everything_ok = SQL_manager_object.Connect();
			User current_user;
			try {
				// Code to open charging window
				if (!(user_name.equals("") | password.equals(""))) {

						
						// The following code charges all the database info and tables
				
						// Next algorithm checks if the user account already exist when you create a new one or in 
						// case you access, if the account exist to charge it in all the user's tables (Client, Director, Worker)
						User user = SQL_manager_object.getMethods().Search_stored_user(user_name, password);
						if(user != null) {
							if(this.user_type == null) {
								Department department_account = SQL_manager_object.getMethods().Search_department_by_name(user_name);
								if(department_account != null) {
									charge_department_main_menu(department_account);
									//LaunchApplication.getStage().hide();
								} else {
								    Hospital hospital_account = SQL_manager_object.getMethods().Search_hospital_by_name(user_name);
								    if(hospital_account != null) {
								    	charge_hospital_main_menu(hospital_account);
										//LaunchApplication.getStage().hide();
								    } else {
								    	System.exit(0);
								    	}
								    }
								    
								}
							} else {
								SQL_manager_object.Close_connection();
							}
						} else {
							if (this.user_type == null) {
								SQL_manager_object.Close_connection();
							}
						}
					} catch (Exception error_occur) {
						error_occur.printStackTrace();
						SQL_manager_object.Close_connection();
					}

					passwordField.clear();
					userNameField.clear();
				
		});
	}

	// -----> BUTTON METHODS <-----

	@FXML
	private void open_registration(MouseEvent event) throws IOException {
		RegisterController.setValues(primary_Stage);
		Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/RegisterView.fxml"));

		primary_Stage.setTitle("Registration Page");
		Scene scene = new Scene(root);
		//primary_Stage.initStyle(StageStyle.UNDECORATED);
		primary_Stage.setScene(scene);
		stage = primary_Stage;
		primary_Stage.show();
	}
	
	@FXML
	private void log_in (MouseEvent event) throws IOException {
		String user_name = userNameField.getText();
		String password = passwordField.getText();
		SQL_manager_object = new SQLiteManager();
		boolean everything_ok = SQL_manager_object.Connect();
		User current_user;
		try {
			// Code to open charging window
			if (!(user_name.equals("") | password.equals(""))) {

					
					// The following code charges all the database info and tables
			
					// Next algorithm checks if the user account already exist when you create a new one or in 
					// case you access, if the account exist to charge it in all the user's tables (Client, Director, Worker)
					User user = SQL_manager_object.getMethods().Search_stored_user(user_name, password);
					if(user != null) {
						if(this.user_type == null) {
							Department department_account = SQL_manager_object.getMethods().Search_department_by_name(user_name);
							if(department_account != null) {
								charge_department_main_menu(department_account);
								//LaunchApplication.getStage().hide();
							} else {
							    Hospital hospital_account = SQL_manager_object.getMethods().Search_hospital_by_name(user_name);
							    if(hospital_account != null) {
							    	charge_hospital_main_menu(hospital_account);
									//LaunchApplication.getStage().hide();
							    } else {
							    	System.exit(0);
							    	}
							    }
							    
							}
						} else {
							SQL_manager_object.Close_connection();
						}
					} else {
						if (this.user_type == null) {
							SQL_manager_object.Close_connection();
						}
					}
				} catch (Exception error_occur) {
					error_occur.printStackTrace();
					SQL_manager_object.Close_connection();
				}

				passwordField.clear();
				userNameField.clear();
	}
	// -----> OTHER METHODS <-----

	public void charge_department_main_menu(Department department_account) {

			if(department_account != null) {

				try {
					DepartmentMenuController.setValues(SQL_manager_object, department_account);
					Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/DepartmentMenuView.fxml"));
					primary_Stage.setTitle("HODDSPITAL");
					Scene scene = new Scene(root);
					scene.setFill(Color.TRANSPARENT);
					primary_Stage.setScene(scene);
					//primary_Stage.initStyle(StageStyle.UNDECORATED);
					stage = primary_Stage;
					primary_Stage.show();
				} catch (IOException fatal_error) {
					fatal_error.printStackTrace();
					System.exit(0);
				}
			}
	}

	public void charge_hospital_main_menu(Hospital hospital_account) {
		if(hospital_account != null) {

			try {
				ManagementMenuController.setValues(SQL_manager_object, hospital_account);
				Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/ManagementMenuView.fxml"));
				primary_Stage.setTitle("HODDSPITAL");
				Scene scene = new Scene(root);
				scene.setFill(Color.TRANSPARENT);
				primary_Stage.setScene(scene);
				//primary_Stage.initStyle(StageStyle.UNDECORATED);
				stage = primary_Stage;
				primary_Stage.show();
			} catch (IOException fatal_error) {
				fatal_error.printStackTrace();
				System.exit(0);
			}
		}
	}

	@FXML // It is triggered when "extitCross.png" is pressed
	private void close_app(MouseEvent event) throws IOException {
		System.exit(0);
	}
}
