package db.GUI;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import droolsexample.priority.Department;
import droolsexample.priority.SQLiteManager;
import droolsexample.priority.SQLiteMethods;
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

	private static Department department;
	private static SQLiteManager SQLmanager;
	private static SQLiteMethods methods;
	private static String departmentName;

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
    private JFXButton modifyDepartmentButton;
    @FXML
    private JFXButton showWishlistButton;
    @FXML
    private JFXButton addResourceButton;
    @FXML
    private JFXButton deleteResourcesButton;
    @FXML
    private JFXButton showBoughtItems;
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

	public static void setValues(SQLiteManager manager, Department departamento) {
		SQLmanager = manager;
		methods = SQLmanager.getMethods();
		department = departamento;
	}

	// -----> GET AND SET METHODS <-----

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
	
	// -----> ESSENTIAL METHODS <-----
	
	public void initialize(URL location, ResourceBundle resources) {
		
		//listInventory_button.setDisable(true);
		modifyDepartmentButton.setOnAction((ActionEvent) -> {
			try {
				DepartmentMenuController.setValues(SQLmanager, resource);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("DepartmentMenuView.fxml"));
				Parent root = (Parent) loader.load();
				DepartmentMenuController department_controller = new DepartmentMenuController();
				department_controller = loader.getController();
				department_controller.getDoneButton().setOnMouseClicked(new EventHandler<Event>() {
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
		SQLmanager.Close_connection();
	}

	
	@FXML
	public void addProduct(MouseEvent event) throws IOException {
		setAllButtonsOn();
		addResourceButton.setDisable(true);
		AddProductController.setValues(SQLmanager);
		Pane option_pane = FXMLLoader.load(getClass().getResource("AddProductView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(option_pane);
	}
	
	@FXML
	public void showBoughtItems(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List of all resources bought");
		setAllButtonsOn();
		listTransactions_button.setDisable(true);
		ShowItemsController.setValues(SQLmanager);
		Pane menu_panel = FXMLLoader.load(getClass().getResource("ShowItemsView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(menu_panel);
	}
	
	@FXML
	public void showWishList(MouseEvent event) throws IOException {
		current_pane_option_label.setText("Current List of Resources");
		setAllButtonsOn();
		listClients_button.setDisable(true);
		ShowWishlistController.setValues(SQLmanager);
		Pane menu_panel = FXMLLoader.load(getClass().getResource("ShowWishlistView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(menu_panel);	
	}

	@FXML
	private void updateDepartmentAccount(MouseEvent event) throws IOException {
		current_pane_option_label.setText("Update information of the current department");
		setAllButtonsOn();
		update_button.setDisable(true);
		DepartmentAccountController.setValue(SQLmanager);
		Pane update_pane = FXMLLoader.load(getClass().getResource("DepartmentAccountView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(update_pane);
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