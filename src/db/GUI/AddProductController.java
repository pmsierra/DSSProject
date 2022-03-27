package db.GUI;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import droolsexample.priority.Department;
import droolsexample.priority.Hospital;
import droolsexample.priority.Resource;
import droolsexample.priority.SQLiteManager;
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
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;

public class AddProductController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	private static Resource resource;


	// -----> FXML ATRIBUTES <-----

	@FXML
	private Pane menu_main_pane;
	@FXML
	private TextField priceField;
	@FXML
	private TextField priorityField;
	@FXML
	private TextField nameField;
	@FXML
	private JFXButton addToCartButton;

	// -----> ESSENTIAL METHODS <-----

	public AddProductController() {
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLiteManager manager, Resource recurso) {
		manager_object = manager;
		resource = recurso;
	}
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(resource.toString());
	}
	
	@FXML
	private void addToCartButton(MouseEvent event) throws IOException {

		String name = nameField.getText();
		String temp = priceField.getText();
		Float price = Float.parseFloat(temp);
		String priority = priorityField.getText();
		resource.setName(name);
		resource.setPrice(price);
		resource.setPriority(priority);
		manager_object.getMethods().Update_resource(resource);
		
	}

	
	// -----> ESSENTIAL METHODS <-----
	
		// -----> BUTTON METHODS <-----

	
}