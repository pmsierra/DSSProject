package db.GUI;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import droolsexample.priority.Hospital;
import droolsexample.priority.SQLiteManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;


public class ManagementMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static Hospital hospital_account;
	private static SQLiteManager SQL_manager_object;
	private ListAllDepartmentsController list_all_departments_controller;
	private AddBudgetController add_budget_controller;
	private DecisionAnalysisController decision_analysis_controller;

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private Pane main_pane;
	@FXML
	private JFXButton logOut_button;
	@FXML
	private JFXButton myAccount_button;
	@FXML
	private JFXButton listAllDepartments_button;
	@FXML
	private JFXButton addBudget_button;
	@FXML
	private JFXButton DecisionAnalysis_button;
	@FXML
	private ImageView minButton;
	@FXML
	private ImageView exitButton;
	@FXML
	private Label current_pane_option_label;
	@FXML
	private Label hospital_name;
	@FXML
	private Label budget;
	@FXML
	private static Stage stage_window;
	@FXML
	private static Stage stage_main;

	// -----> ESSENTIAL METHODS <-----

	public ManagementMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLiteManager SQL_manager, Hospital hospital) {
		SQL_manager_object = SQL_manager;
		hospital_account = hospital;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DecisionAnalysis_button.setDisable(true);
		
		try {
			setAllButtonsOn();
			listAllDepartments_button.setDisable(true);
			list_all_departments_controller.setValues(SQL_manager_object);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllDepartmentsView.fxml"));
			Pane list_all_clients_pane = loader.load();
			list_all_departments_controller = (ListAllClientsController)loader.getController();
			main_pane.getChildren().removeAll();
			main_pane.getChildren().setAll(list_all_clients_pane);
		} catch (IOException list_department_error) {
			list_department_error.printStackTrace();
		}
	}
	
	// -----> BUTTON METHODS <-----
	
	@FXML
	private void list_all_clients_button(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List all departments");
		setAllButtonsOn();
		listAllDepartments_button.setDisable(true);
		list_all_departments_controller.setValues(SQL_manager_object);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllDepartmentsView.fxml"));
		Pane list_all_clients_pane = loader.load();
		list_all_departments_controller = (ListAllClientsController)loader.getController();
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(list_all_clients_pane);
	}
	

	
	@FXML
	private void add_budget_button(MouseEvent event) throws IOException {

			current_pane_option_label.setText("Add category");
			setAllButtonsOn();
			addBudget_button.setDisable(true);
			add_budget_controller.setValues(SQL_manager_object);
			Pane add_budget_pane = FXMLLoader.load(getClass().getResource("AddBudgetView.fxml"));
			main_pane.getChildren().removeAll();
			main_pane.getChildren().setAll(add_budget_pane);
	
	}
	
	@FXML
	private void decision_analysis_button(MouseEvent event) throws IOException { 
		current_pane_option_label.setText("Decision Analysis");
		setAllButtonsOn();
		DecisionAnalysis_button.setDisable(true);
		DecisionAnalysisController.setValues(SQL_manager_object);    
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DecisionAnalysisView.fxml"));
		Pane decision_analysis_pane = loader.load();
		decision_analysis_controller = (DecisionAnalysisController) loader.getController();
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(decision_analysis_pane);
	}

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void log_out(MouseEvent event) {
		SQL_manager_object.Close_connection();
		LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void min_window(MouseEvent event) {
		Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
	}

	// -----> SET AND GET METHODS <-----
	
	public void setHospitalName(String name) {
		this.hospital_name.setText("Hospital name: " + name);
	}

	public void setBudget(float budget) {
	this.budget.setText("Budget: " + budget);
	}

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
	
	// -----> ANABLE/DISABLE BUTTONS <-----
	
	public void setAllButtonsOff() {
	    myAccount_button.setDisable(true);
	    listAllDepartments_button.setDisable(true);
	    addBudget_button.setDisable(true);
	    DecisionAnalysis_button.setDisable(true);
	    logOut_button.setDisable(true);
	    minButton.setDisable(true);
	    exitButton.setDisable(true);
	}
	
	public void setAllButtonsOn() {
		myAccount_button.setDisable(false);
		listAllDepartments_button.setDisable(false);
		addBudget_button.setDisable(false);
	    DecisionAnalysis_button.setDisable(false);
	    logOut_button.setDisable(false);
	    minButton.setDisable(false);
	    exitButton.setDisable(false);
	}
	
	// -----> UPDATE ACCOUNT METHOD <-----
		
	public void update_director_account() {
		hospital_account = SQL_manager_object.Search_hospital_by_name(hospital_account.getHospitalName());
		
	   	setBudget(hospital_account.getBudget());
	   	setHospitalName(hospital_account.getHospitalName());
	}
}



















