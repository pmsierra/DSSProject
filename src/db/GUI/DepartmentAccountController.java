package db.GUI;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import droolsexample.priority.Department;
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

public class DepartmentAccountController implements Initializable {
	

	private static SQLiteManager manager_object;
	private static Department department_account;

	@FXML
	private Pane main_pane;
	@FXML
	private TextField npatients_textField;
	@FXML
	private TextField avghours_textField;
	@FXML
	private TextField ratio_textField;
	@FXML
	private TextField nemployees_textField;
	@FXML
	private JFXButton update_department_button;
    @FXML
    private static Label number_of_patients_label;
    @FXML
    private static Label death_ratio_label;
    @FXML
    private static Label average_hours_label;
    @FXML
    private static Label number_of_employees;
    
	public DepartmentAccountController () {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLiteManager manager, Department department, Label npatients, Label ratio, Label avgdeaths, Label nemployees ) {
		manager_object = manager;
		department_account = department;
		number_of_patients_label =npatients;
		death_ratio_label = ratio;
		average_hours_label = avgdeaths;
		number_of_employees = nemployees;
	}

	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(department_account.toString());
		}
	
	@FXML
	private void update_info_button(MouseEvent event) throws IOException {
		
		if(npatients_textField.getText()!= "") {
		Integer npatients = Integer.parseInt(npatients_textField.getText());
		department_account.setNpatients(npatients);
		number_of_patients_label.setText("Number of patients: " + npatients.toString());
		manager_object.getMethods().Update_department(department_account);
		}
		if(npatients_textField.getText()!= "") {
		Integer avghours = Integer.parseInt(avghours_textField.getText());
		department_account.setAvghours(avghours);
		average_hours_label.setText("Average hours: " + avghours.toString());
		manager_object.getMethods().Update_department(department_account);
		}
		if(npatients_textField.getText()!= "") {
		Float ratio = Float.parseFloat(ratio_textField.getText());
		department_account.setRatio(ratio);
		death_ratio_label.setText("Death ratio: " + ratio.toString());
		manager_object.getMethods().Update_department(department_account);
		}
		if(npatients_textField.getText()!= "") {
		Integer nemployees = Integer.parseInt(nemployees_textField.getText());
		department_account.setNemployees(nemployees);
		number_of_employees.setText("Number of employees: " + nemployees.toString());
		manager_object.getMethods().Update_department(department_account);
		}


		
	
		
	}
}