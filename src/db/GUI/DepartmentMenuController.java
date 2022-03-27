package db.GUI;

import java.io.IOException;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import droolsexample.priority.Department;
import droolsexample.priority.Hospital;
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
	private AddProductController add_product_controller;
	private ShowWishlistController show_wish_list_controller;

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
    private Label department_name;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private JFXButton logOut_button;
    @FXML
    private JFXButton modifyDepartmentButton;
    @FXML
    private JFXButton showWishListButton;
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
		SQLmanager = new SQLiteManager();

		
		SQLmanager.Connect();
		LinkedList<Department> departmentList = (LinkedList<Department>) SQLmanager.getMethods().List_all_departments();
		department = departmentList.getFirst();
		System.out.println(department.toString());
		
		try {
			setAllButtonsOn();
			department_name.setText("Department name: " + department.getName());
			
			showWishListButton.setDisable(true);
			ShowWishlistController.setValues(SQLmanager, department);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowWishlistView.fxml"));
			Pane list_all_wishlist_pane = loader.load();
			show_wish_list_controller = (ShowWishlistController)loader.getController();
			main_pane.getChildren().removeAll();
			main_pane.getChildren().setAll(list_all_wishlist_pane);
			
			
		} catch (IOException list_department_error) {
			list_department_error.printStackTrace();
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

	/*@FXML
	private void log_out(MouseEvent event) {
		LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
		SQLmanager.Close_connection();
	}*/

	
	@FXML
	public void addProduct(MouseEvent event) throws IOException {
		setAllButtonsOn();
		addResourceButton.setDisable(true);
		AddProductController.setValues(SQLmanager);
		Pane option_pane = FXMLLoader.load(getClass().getResource("AddProductView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(option_pane);
	}
	
	/*@FXML
	public void showBoughtItems(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List of all resources bought");
		setAllButtonsOn();
		showBoughtItems.setDisable(true);
		ShowWishlistController.setValues(SQLmanager);
		Pane menu_panel = FXMLLoader.load(getClass().getResource("ShowItemsView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(menu_panel);
	}*/
	
	@FXML
	public void showWishList(MouseEvent event) throws IOException {
		setAllButtonsOn();
		showWishListButton.setDisable(true);
		ShowWishlistController.setValues(SQLmanager,department);
		Pane menu_panel = FXMLLoader.load(getClass().getResource("ShowWishlistView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(menu_panel);	
	}

	/*@FXML
	private void updateDepartmentAccount(MouseEvent event) throws IOException {
		current_pane_option_label.setText("Update information of the current department");
		setAllButtonsOn();
		update_button.setDisable(true);
		DepartmentAccountController.setValue(SQLmanager);
		Pane update_pane = FXMLLoader.load(getClass().getResource("DepartmentAccountView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(update_pane);
	}*/
		
	public void setAllButtonsOn() {
		this.addResourceButton.setDisable(false);
		//this.listClients_button.setDisable(false);
		this.showWishListButton.setDisable(false);
		//this.showBoughtItems.setDisable(false);
		//this.modifyDepartmentButton.setDisable(false);
		//this.deleteResourcesButton.setDisable(false);
		//this.update_button.setDisable(false);
		//this.createFeatures_button.setDisable(false);
	}
	
	public void setAllButtonsOff() {
		this.addResourceButton.setDisable(true);
		//this.listClients_button.setDisable(true);
		this.showWishListButton.setDisable(true);
		//this.showBoughtItems.setDisable(true);
		//this.modifyDepartmentButton.setDisable(true);
		//this.deleteResourcesButton.setDisable(true);
		//this.update_button.setDisable(true);
		//this.createFeatures_button.setDisable(true);
	}
}