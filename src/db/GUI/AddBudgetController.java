package db.GUI;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import droolsexample.priority.Hospital;
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

public class AddBudgetController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	private static Hospital hospital;


	// -----> FXML ATRIBUTES <-----

	@FXML
	private Pane menu_main_pane;
	@FXML
	private TextField budget_textField;
	@FXML
	private JFXButton addBudget_button;

	// -----> ESSENTIAL METHODS <-----

	public AddBudgetController() {
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLiteManager manager, Hospital hospital) {
		manager_object = manager;
		hospital = hospital;
	}

	@FXML
	private void add_Budget_button(MouseEvent event) throws IOException {

		String value = budget_textField.getText();
		Float budget = Float.parseFloat(value);
		hospital.setBudget(budget);
		this.manager_object.getMethods().Update_hospital(hospital);
	}

	
	// -----> ESSENTIAL METHODS <-----
	
		// -----> BUTTON METHODS <-----

	
}







