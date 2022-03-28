package droolsexample.priority;
	
import java.io.Console;		
import java.util.Scanner;

import db.GUI.DepartmentMenuController;
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

public class LaunchApp2 extends Application {
	
	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		Scanner in;
		in = new Scanner(System.in);
		Boolean user_exists = false;
		String user_type= "";
		String user_name= "";
		String user_password= "";
		SQLiteManager SQLmanager = new SQLiteManager();
		boolean everything_ok = SQLmanager.Connect();
		User current_user;
		
		System.out.println("Bienvenido a HODSSPITAL\n");
		System.out.println("La interfaz gráfica asociada a este log in aún está en desarrollo\n");
		System.out.println("Tiene usted usuario? (Y/N)\n");
		do{
			String action = in.nextLine();
		
		
			if(action.equals("Y")) {
				user_exists = true;
				System.out.println("Es usted un department u hospital?:\n");
				user_type = in.nextLine();
				System.out.println("Por favor introduzca su nombre de usuario:\n");
				user_name = in.nextLine();
				System.out.println("Por favor introduzca su contraseña:\n");
				user_password = in.nextLine();
				break;
			}
			if(action.equals("N")) {
				System.out.println("Es usted un department u hospital?:\n");
				user_type = in.nextLine();
				System.out.println("Por favor introduzca su nombre de usuario deseado:\n");
				user_name = in.nextLine();
				System.out.println("Por favor introduzca su contraseña deseada:\n");
				user_password = in.nextLine();
				break;
			}
			else {
				System.out.println("Por favor introduzca Y para Sí o N para No\n");
			}
		}while (in.nextLine()!=null);
		in.close();
		if(user_exists == false) {
			System.out.println("se mete:\n");
			current_user = SQLmanager.getMethods().Insert_new_user_by_name(user_name, user_password);
			System.out.println("sigue:\n");
			if(current_user != null) {
				if(user_type.equals("department")) {
					System.out.println("Es department:\n");
					Department deparment_account = new Department(user_name,0, 0f, 0, 0, false, 0 );
					LinkedList<Resource> wishlistshopping = new LinkedList<Resource>();
					deparment_account.setWishlistshopping(wishlistshopping);
					SQLmanager.getMethods().Insert_new_department(deparment_account);
					if(deparment_account != null) {

						try {
							DepartmentMenuController.setValues(SQLmanager, deparment_account);
							Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/DepartmentMenuView.fxml"));
							primaryStage.setTitle("HODDSPITAL");
							Scene scene = new Scene(root);
							scene.setFill(Color.TRANSPARENT);
							primaryStage.setScene(scene);
							//primaryStage.initStyle(StageStyle.TRANSPARENT);
							stage = primaryStage;
							primaryStage.show();
						} catch (IOException fatal_error) {
							fatal_error.printStackTrace();
							System.exit(0);
						}
					} 
					} 
					if(user_type.equals("hospital")) {
						System.out.println("Es hospital:\n");
						Hospital hospital_account = new Hospital(user_name, 0f );
						SQLmanager.getMethods().Insert_new_hospital(hospital_account);

					    if(hospital_account != null) {
					    	try {
								ManagementMenuController.setValues(SQLmanager, hospital_account);
								Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/ManagementMenuView.fxml"));
								primaryStage.setTitle("HODDSPITAL");
								Scene scene = new Scene(root);
								scene.setFill(Color.TRANSPARENT);
								primaryStage.setScene(scene);
								//primaryStage.initStyle(StageStyle.TRANSPARENT);
								stage = primaryStage;
								primaryStage.show();
							} catch (IOException fatal_error) {
								fatal_error.printStackTrace();
								System.exit(0);
							}

					    	
					    }
					}
			}
		}
		else {
			current_user = SQLmanager.getMethods().Search_stored_user(user_name, user_password);
		}
		System.out.println("user:"+current_user);
		if(current_user != null) {
			if(user_type.equals("department")) {
				System.out.println("Es department:\n");
				Department deparment_account = SQLmanager.getMethods().Search_department_by_name(current_user.getUserName());
				if(deparment_account != null) {

					try {
						DepartmentMenuController.setValues(SQLmanager, deparment_account);
						Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/DepartmentMenuView.fxml"));
						primaryStage.setTitle("HODDSPITAL");
						Scene scene = new Scene(root);
						scene.setFill(Color.TRANSPARENT);
						primaryStage.setScene(scene);
						//primaryStage.initStyle(StageStyle.TRANSPARENT);
						stage = primaryStage;
						primaryStage.show();
					} catch (IOException fatal_error) {
						fatal_error.printStackTrace();
						System.exit(0);
					}
				} 
				} 
				if(user_type.equals("hospital")) {
					System.out.println("Es hospital:\n");
					Hospital hospital_account = SQLmanager.getMethods().Search_hospital_by_name(current_user.getUserName());
				    if(hospital_account != null) {
				    	try {
							ManagementMenuController.setValues(SQLmanager, hospital_account);
							Parent root = FXMLLoader.load(getClass().getResource("/db/GUI/ManagementMenuView.fxml"));
							primaryStage.setTitle("HODDSPITAL");
							Scene scene = new Scene(root);
							scene.setFill(Color.TRANSPARENT);
							primaryStage.setScene(scene);
							//primaryStage.initStyle(StageStyle.TRANSPARENT);
							stage = primaryStage;
							primaryStage.show();
						} catch (IOException fatal_error) {
							fatal_error.printStackTrace();
							System.exit(0);
						}

				    	
				    }
				}
			} 
			else {
				SQLmanager.Close_connection();
			}

	}
	

	public static void main(String[] args) {
		
		launch(args);
	}
	
	public static Stage getStage() {
		return stage;
	}
}
