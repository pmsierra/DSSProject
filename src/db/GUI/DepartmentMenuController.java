package db.GUI;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import db.jdbc.SQLManager;
import db.pojos.Worker;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;

public class DepartmentMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static Worker worker_account;
	private static SQLManager manager_object;
	private ListAllBiomaterialsController list_all_bimaterials_controller;
	private static Integer biomaterial_id;


	// -----> FXML ATRIBUTES <-----

	@FXML
    private AnchorPane menu_window;
    @FXML
    private Pane menu_main_pane;
    @FXML
    private Pane main_pane;
    @FXML
    private Label current_pane_option_label;
    @FXML
    private ImageView exitButton;
    @FXML
    private ImageView minButton;
    @FXML
    private Label worker_name;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private JFXButton logOut_button;
    @FXML
    private JFXButton myAccount_button;
    @FXML
    private JFXButton listInventory_button;
    @FXML
    private JFXButton addProduct_button;
    @FXML
    private JFXButton removeProduct_button;
    @FXML
    private JFXButton listTransactions_button;
    @FXML
    private JFXButton listClients_button;
    @FXML
    private JFXButton update_button;
    @FXML
    private JFXButton createFeatures_button;
    @FXML
	private static Stage stage_window;
	@FXML
	private static Stage stage_main;

	// -----> ESSENTIAL METHODS <-----

	public DepartmentMenuController() {
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLManager manager, Worker worker) {
		manager_object = manager;
		worker_account = worker;
	}

	public void update_worker_account() {
		worker_account = manager_object.Search_worker_by_id(worker_account.getWorker_id());
		setWorkerEmail(worker_account.getEmail());
		setWorkerName(worker_account.getWorker_name());
		setWorkerTelephone(worker_account.getTelephone());
	}

	// -----> GET AND SET METHODS <-----

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
	
	public static void setBioID(Integer id) {
		biomaterial_id = id;
	}

	public void setWorkerName(String name) {
		this.worker_name.setText("Worker's name: " + name);
	}
	
	public void setWorkerEmail(String email) {
		if (email != null) {
			this.email.setText("Email: " + email);
		} else {
			this.email.setText("Email: No email associated");
		}
	}
	public void setWorkerTelephone(Integer telephone) {
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
	

	
	// -----> ESSENTIAL METHODS <-----
	
	public void initialize(URL location, ResourceBundle resources) {
		
		listInventory_button.setDisable(true);
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				AccountWorkerController.setValues(manager_object, worker_account);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountWorkerView.fxml"));
				Parent root = (Parent) loader.load();
				AccountWorkerController account_controller = new AccountWorkerController();
				account_controller = loader.getController();
				account_controller.getDoneButton().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_worker_account();
						menu_window.setEffect(null);
						stage_window.close();
					} 
				});	
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));
				stage_window.setAlwaysOnTop(true);				
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
			} catch (IOException worker_account_error) {
				worker_account_error.printStackTrace();
				System.exit(0);
			}
		});
		
		
		
		removeProduct_button.setOnAction((ActionEvent) -> {
			try {
				current_pane_option_label.setText("Remove biomaterial");
				RemoveProductController.setValues(manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveProductView.fxml"));
				Parent root = (Parent) loader.load();
				RemoveProductController product_controller = new RemoveProductController();
				product_controller = loader.getController();
				product_controller.getDelete_button().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						menu_window.setEffect(null);
						stage_window.close();
					}
				});	
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));
				stage_window.setAlwaysOnTop(true);		
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
						if (list_all_bimaterials_controller != null) {
							list_all_bimaterials_controller.refreshBiomaterialListView();
						}
					}
				});		
				stage_window.show();
			} catch(IOException delete_product_error) {
				delete_product_error.printStackTrace();
				System.exit(0);
			}
			
		});
		
		try {
			setAllButtonsOn();
			listInventory_button.setDisable(true);
			ListAllBiomaterialsController.setValues(manager_object);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllBiomaterialsView.fxml"));
			Pane list_pane = loader.load();
			list_all_bimaterials_controller = (ListAllBiomaterialsController) loader.getController();
			main_pane.getChildren().removeAll();
			main_pane.getChildren().setAll(list_pane);
		} catch (IOException list_error) {
			list_error.printStackTrace();
		}
	}
	
	// -----> BUTTON METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}
	
	@FXML
	void min_window(MouseEvent event) {
		Stage stage = (Stage) this.menu_window.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void log_out(MouseEvent event) {
		LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
		manager_object.Close_connection();
	}
	
	@FXML
	public void open_option_panel(MouseEvent event) throws IOException {
		setAllButtonsOn();
		addProduct_button.setDisable(true);
		ProductOptionController.setValues(manager_object);
		Pane option_pane = FXMLLoader.load(getClass().getResource("ProductOptionPanel.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(option_pane);
	}
		
	
	@FXML
	private void list_inventory_button(MouseEvent event) throws IOException { 
		current_pane_option_label.setText("List inventory");
		setAllButtonsOn();
		listInventory_button.setDisable(true);
		ListAllBiomaterialsController.setValues(manager_object);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllBiomaterialsView.fxml"));
		Pane list_pane = loader.load();
		list_all_bimaterials_controller = (ListAllBiomaterialsController) loader.getController();
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(list_pane);
	}
	
	@FXML
	public void list_transactions(MouseEvent event) throws IOException {
		current_pane_option_label.setText("Finantial status");
		setAllButtonsOn();
		listTransactions_button.setDisable(true);
		DirectorFinantialStatusController.setValues(manager_object);
		Pane menu_panel = FXMLLoader.load(getClass().getResource("DirectorFinantialStatusView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(menu_panel);
	}
	
	@FXML
	public void list_clients(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List clients");
		setAllButtonsOn();
		listClients_button.setDisable(true);
		ListAllClientsController.setValues(manager_object);
		Pane menu_panel = FXMLLoader.load(getClass().getResource("ListAllClientsView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(menu_panel);	
	}

	@FXML
	private void update_product_button(MouseEvent event) throws IOException {
		current_pane_option_label.setText("Update features");
		setAllButtonsOn();
		update_button.setDisable(true);
		UpdateFeaturesController.setValue(manager_object, biomaterial_id);
		Pane update_pane = FXMLLoader.load(getClass().getResource("UpdateFeaturesView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(update_pane);
	}
	
	@FXML
	private void create_features_button(MouseEvent event) throws IOException {
		current_pane_option_label.setText("Features creation");
		setAllButtonsOn();
		createFeatures_button.setDisable(true);
		NewFeaturesController.setValue(manager_object);
		Pane creation_Pane = FXMLLoader.load(getClass().getResource("CreationFeaturesView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(creation_Pane);
	}
	
	
	public void setAllButtonsOn() {
		this.addProduct_button.setDisable(false);
		this.listClients_button.setDisable(false);
		this.listInventory_button.setDisable(false);
		this.listTransactions_button.setDisable(false);
		this.myAccount_button.setDisable(false);
		this.removeProduct_button.setDisable(false);
		this.update_button.setDisable(false);
		this.createFeatures_button.setDisable(false);
	}
	
	public void setAllButtonsOff() {
		this.addProduct_button.setDisable(true);
		this.listClients_button.setDisable(true);
		this.listInventory_button.setDisable(true);
		this.listTransactions_button.setDisable(true);
		this.myAccount_button.setDisable(true);
		this.removeProduct_button.setDisable(true);
		this.update_button.setDisable(true);
		this.createFeatures_button.setDisable(true);
	}
}







