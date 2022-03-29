package db.GUI;

import java.io.IOException;
import db.GUI.DepartmentMenuController;
import db.GUI.ManagementMenuController;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import droolsexample.priority.Department;
import droolsexample.priority.Hospital;
import droolsexample.priority.LaunchApp;
import droolsexample.priority.Resource;
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

public class RegisterController implements Initializable {
	
	// -----> CLASS ATRIBUTES <-----
	

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane startUpMenu;
	@FXML
	private Pane logInPane;
	@FXML
	private Button signUpButton;
	@FXML
	private Button goBackButton;
	
	@FXML
	private TextField userNameField;
	
	@FXML
	private TextField registerName;
	
	@FXML
	private PasswordField passwordField; 
	
	@FXML
	private PasswordField registerPassword; 
	
	private String user_type;
	
	private SQLiteManager SQL_manager_object;
	
	private static Stage primary_Stage;
	@FXML
	private JFXCheckBox is_hospital_checkBox;
	
	private static Stage stage;
	
	private Boolean is_hospital;
	
	// -----> ESSENTIAL METHODS <-----

	public RegisterController() {
		// Default constructor
	}

	public static void setValues(Stage primaryStage) {
		primary_Stage = primaryStage;

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		signUpButton.setOnAction((ActionEvent event) -> {

			this.is_hospital = is_hospital_checkBox.isSelected();
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
							if(this.is_hospital == false) {
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
	private void register_button (MouseEvent event) throws IOException {
		
		this.is_hospital = is_hospital_checkBox.isSelected();
		String user_name = registerName.getText();
		String password = registerPassword.getText();
		SQL_manager_object = new SQLiteManager();
		boolean everything_ok = SQL_manager_object.Connect();
		User current_user;

		System.out.println("name: "+ user_name);
		System.out.println("password: "+ password);
		System.out.println("is: "+ this.is_hospital);
		try {
			// Code to open charging window
			if (!(user_name.equals("") | password.equals(""))) {
				System.out.println("semete");
					
					// The following code charges all the database info and tables
			
					// Next algorithm checks if the user account already exist when you create a new one or in 
					// case you access, if the account exist to charge it in all the user's tables (Client, Director, Worker)
					User user = SQL_manager_object.getMethods().Insert_new_user_by_name(user_name, password);
					System.out.println("User done: " + user);

					if(user != null) {
						System.out.println("dentro:\n");
						if(this.is_hospital.equals(false)) {
							System.out.println("New department done");
							Department new_department_account = new Department(user_name,0, 0f, 0, 0, false, 0 );
							LinkedList<Resource> wishlistshopping = new LinkedList<Resource>();
							new_department_account.setWishlistshopping(wishlistshopping);
							SQL_manager_object.getMethods().Insert_new_department(new_department_account);
							Department department_account = SQL_manager_object.getMethods().Search_department_by_name(user_name);
							charge_department_main_menu(department_account);
							LaunchApp.getStage().hide();
							    
							}
						} if(this.is_hospital.equals(true)){
							System.out.println("New hospital done");
							Hospital hospital_account = new Hospital(user_name, 0f );
							SQL_manager_object.getMethods().Insert_new_hospital(hospital_account);
				            LinkedList<Department> hospitalList = (LinkedList<Department>)SQL_manager_object.getMethods().List_all_departments();
				            hospital_account.setHospitalList(hospitalList);
				            hospital_account.setHospitalName(user_name);
				            hospital_account.setHighestDepartment(hospital_account.calculatePriorityList());
				        	//LinkedList<Resource> purchaseList = new LinkedList<Resource>();
				        	//LinkedList<Department> departmentOrder = new LinkedList<Department>();
				            List<String> purchaseList = new LinkedList<String>();
				            hospital_account.setBougthItems(purchaseList);
				 
				            List<String> decisionDepartments = new LinkedList<String>();
				            hospital_account.setDepartmentOrder(decisionDepartments);
						    charge_hospital_main_menu(hospital_account);
							LaunchApp.getStage().hide();
						}
						System.out.println("sale:\n");
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
					//scene.setFill(Color.TRANSPARENT);
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
				//scene.setFill(Color.TRANSPARENT);
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
	@FXML 
	// It is triggered when "go back" button is pressed
	private void back_to_menu(MouseEvent event) throws IOException {
		
		RegisterController.setValues(primary_Stage);
		Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/LogInView.fxml"));

		primary_Stage.setTitle("Registration Page");
		Scene scene = new Scene(root);
		//primary_Stage.initStyle(StageStyle.UNDECORATED);
		primary_Stage.setScene(scene);
		stage = primary_Stage;
		primary_Stage.show();
	}
	@FXML // It is triggered when "extitCross.png" is pressed
	private void close_app(MouseEvent event) throws IOException {
		System.exit(0);
	}
}
