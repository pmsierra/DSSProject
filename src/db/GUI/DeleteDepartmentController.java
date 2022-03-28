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

public class DeleteDepartmentController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	private static Hospital hospital_account;


	// -----> FXML ATRIBUTES <-----

	@FXML
	private Pane menu_main_pane;
	@FXML
	private Label exist_label;
	@FXML
	private TextField department_textField;
	@FXML
	private JFXButton deleteDepartment_button;

	// -----> ESSENTIAL METHODS <-----

	public DeleteDepartmentController() {
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLiteManager manager, Hospital hospital) {
		manager_object = manager;
		hospital_account = hospital;
	}
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(hospital_account.toString());
		exist_label.setVisible(false);
	}
	public void setParentController(ManagementMenuController parentController) {
		
	}
	
	@FXML
	private void delete_department_button(MouseEvent event) throws IOException {

		String dep_name = department_textField.getText();
		if(manager_object.getMethods().Search_department_by_name(dep_name)!=null) {
			
			manager_object.getMethods().Delete_department(manager_object.getMethods().Search_department_by_name(dep_name));
			exist_label.setText("The department "+dep_name+" has been deleted");
			exist_label.setVisible(true);
		}
		else {
			exist_label.setText("The department "+dep_name+" does not exist!");
			exist_label.setVisible(true);
		}
		manager_object.getMethods().Update_hospital(hospital_account);
		
	}

	
	// -----> ESSENTIAL METHODS <-----
	
		// -----> BUTTON METHODS <-----

	
}







