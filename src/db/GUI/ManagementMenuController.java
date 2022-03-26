package db.GUI;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;



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

	private static Director director_account;
	private static SQLManager SQL_manager_object;
	private static JPAManager JPA_manager_object;
	private ListAllClientsController list_all_clients_controller;
	private ListAllWorkersController list_all_workers_controller;
	private DirectorFinantialStatusController finantial_status_controller;

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
	private JFXButton removeClient_button;
	@FXML
	private JFXButton listAllWorkers_button;
	@FXML	
	private JFXButton removeWorker_button;
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
	private Label director_name;
	@FXML
	private Label email;
	@FXML
	private Label telephone;
	@FXML
	private static Stage stage_window;
	@FXML
	private static Stage stage_main;

	// -----> ESSENTIAL METHODS <-----

	public ManagementMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager SQL_manager, JPAManager JPA_manager, Director director) {
		SQL_manager_object = SQL_manager;
		JPA_manager_object = JPA_manager;
		director_account = director;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		finantialStatus_button.setDisable(true);
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				AccountDirectorController.setValues(SQL_manager_object, director_account);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDirectorView.fxml"));
				Parent root = (Parent) loader.load();
				AccountDirectorController account_controller = new AccountDirectorController();
				account_controller = loader.getController();
				account_controller.getDoneButton().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_director_account();
						menu_window.setEffect(null);
						stage_window.close();
					} 
				});	
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));				
				stage_window.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						menu_window.setEffect(new BoxBlur(3,3,3));
						stage_window.initModality(Modality.APPLICATION_MODAL);
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {
						menu_window.setEffect(null);
					}
				});		
				stage_window.show();
			} catch (IOException director_account_error) {
				director_account_error.printStackTrace();
				System.exit(0);
			
			}
		});
		
		removeClient_button.setOnAction((ActionEvent) -> {
			try {
				RemoveClientController.setValues(SQL_manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveClientView.fxml"));
				Parent root = (Parent) loader.load();
				RemoveClientController client_controller = new RemoveClientController();
				client_controller = loader.getController();
				client_controller.getDeleteAccountButton().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						menu_window.setEffect(null);
						stage_window.close();
					}
				});	
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));		
				stage_window.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						menu_window.setEffect(new BoxBlur(3,3,3));
						stage_window.initModality(Modality.APPLICATION_MODAL);
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {	
						if(finantial_status_controller != null) {
							finantial_status_controller.refreshTransactionListView();
						}
						menu_window.setEffect(null);
						if(list_all_clients_controller != null) {
							list_all_clients_controller.refreshClientListView();
						}
					}
				});		
				stage_window.show();
			} catch(IOException delete_client_error) {
				delete_client_error.printStackTrace();
				System.exit(0);
			}
		});
		
		removeWorker_button.setOnAction((ActionEvent) -> {
			try {
				RemoveWorkerController.setValues(SQL_manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveWorkerView.fxml"));
				Parent root = (Parent) loader.load();
				RemoveWorkerController worker_controller = new RemoveWorkerController();
				worker_controller = loader.getController();
				worker_controller.getDeleteAccountButton().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						menu_window.setEffect(null);
						stage_window.close();
					}
				});	
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));
				stage_window.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						menu_window.setEffect(new BoxBlur(3,3,3));
						stage_window.initModality(Modality.APPLICATION_MODAL);
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {
						menu_window.setEffect(null);
						if(list_all_workers_controller != null) {
							list_all_workers_controller.refreshWorkerListView();
						}
					}
				});		
				stage_window.show();
			} catch(IOException delete_client_error) {
				delete_client_error.printStackTrace();
				System.exit(0);
			}
		});
		
		try {
			setAllButtonsOn();
			finantialStatus_button.setDisable(true);
			DirectorFinantialStatusController.setValues(SQL_manager_object);    
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DirectorFinantialStatusView.fxml"));
			Pane finantial_status_pane;
			finantial_status_pane = loader.load();
			finantial_status_controller = (DirectorFinantialStatusController) loader.getController();
			main_pane.getChildren().removeAll();
			main_pane.getChildren().setAll(finantial_status_pane);
		} catch (IOException finantial_status_error) {
			finantial_status_error.printStackTrace();
		}
	}
	
	// -----> BUTTON METHODS <-----
	
	@FXML
	private void list_all_clients_button(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List all clients");
		setAllButtonsOn();
		listAllClients_button.setDisable(true);
		ListAllClientsController.setValues(SQL_manager_object);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllClientsView.fxml"));
		Pane list_all_clients_pane = loader.load();
		list_all_clients_controller = (ListAllClientsController)loader.getController();
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(list_all_clients_pane);
	}
	
	@FXML
	private void list_all_workers_button(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List all workers");
		setAllButtonsOn();
		listAllWorkers_button.setDisable(true);
		ListAllWorkersController.setValues(SQL_manager_object);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllWorkersView.fxml"));
		Pane list_all_workers_pane = loader.load();
		list_all_workers_controller = (ListAllWorkersController)loader.getController();
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(list_all_workers_pane);
	}
	
	@FXML
	private void add_category_button(MouseEvent event) throws IOException {
		if(SQL_manager_object.List_all_categories().size() == 0) {
			NoCategoryController.setValues(JPA_manager_object);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NoCategoryView.fxml"));
			Parent root = (Parent) loader.load();
			stage_window = new Stage();
			stage_window.initStyle(StageStyle.UNDECORATED);
			stage_window.setScene(new Scene(root));
			stage_window.setOnShowing(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					menu_window.setEffect(new BoxBlur(3,3,3));
					stage_window.initModality(Modality.APPLICATION_MODAL);
				}
			});
			stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
				@Override
				public void handle(WindowEvent event) {
					menu_window.setEffect(null);
				}
			});
			stage_window.show();
		} else {
			current_pane_option_label.setText("Add category");
			setAllButtonsOn();
			addCategory_button.setDisable(true);
			AddCategoryController.setValues(SQL_manager_object, JPA_manager_object);
			Pane add_category_pane = FXMLLoader.load(getClass().getResource("AddCategoryView.fxml"));
			main_pane.getChildren().removeAll();
			main_pane.getChildren().setAll(add_category_pane);
		}
	}
	
	@FXML
	private void finantial_status_button(MouseEvent event) throws IOException { 
		current_pane_option_label.setText("Finantial Status");
		setAllButtonsOn();
		finantialStatus_button.setDisable(true);
		DirectorFinantialStatusController.setValues(SQL_manager_object);    
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DirectorFinantialStatusView.fxml"));
		Pane finantial_status_pane = loader.load();
		finantial_status_controller = (DirectorFinantialStatusController) loader.getController();
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(finantial_status_pane);
	}

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void log_out(MouseEvent event) {
		SQL_manager_object.Close_connection();
		JPA_manager_object.Close_connection();
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
	
	public void setDirectorName(String name) {
		this.director_name.setText("Director's name: " + name);
	}

	public void setDirectorEmail(String email) {
		if (email != null) {
			this.email.setText("Email: " + email);
		} else {
			this.email.setText("Email: No email associated");
		}
	}

	public void setDirectorTelephone(Integer telephone) {
		if (telephone == null) {
			this.telephone.setText("Telephone: No telephone associated");
		} else {
			if (telephone != 0) {
				this.telephone.setText("Telephone: " + telephone);
			} else {
				this.telephone.setText("Telephone: No telephone associated");
			}
		}
	}

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
	
	// -----> ANABLE/DISABLE BUTTONS <-----
	
	public void setAllButtonsOff() {
	    myAccount_button.setDisable(true);
	    listAllClients_button.setDisable(true);
	    addCategory_button.setDisable(true);
	    listAllWorkers_button.setDisable(true);
	    removeClient_button.setDisable(true);
	    removeWorker_button.setDisable(true);
	    finantialStatus_button.setDisable(true);
	    logOut_button.setDisable(true);
	    minButton.setDisable(true);
	    exitButton.setDisable(true);
	}
	
	public void setAllButtonsOn() {
		myAccount_button.setDisable(false);
	    listAllClients_button.setDisable(false);
	    addCategory_button.setDisable(false);
	    listAllWorkers_button.setDisable(false);
	    removeClient_button.setDisable(false);
	    removeWorker_button.setDisable(false);
	    finantialStatus_button.setDisable(false);
	    logOut_button.setDisable(false);
	    minButton.setDisable(false);
	    exitButton.setDisable(false);
	}
	
	// -----> UPDATE ACCOUNT METHOD <-----
		
	public void update_director_account() {
	    director_account = SQL_manager_object.Search_director_by_id(director_account.getDirector_id());
	   	setDirectorEmail(director_account.getEmail());
	   	setDirectorName(director_account.getDirector_name());
    	setDirectorTelephone(director_account.getTelephone());
	}
}



















