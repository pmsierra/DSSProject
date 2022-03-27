package db.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import droolsexample.priority.SQLiteManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import droolsexample.priority.Department;
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

public class ListAllDepartmentsController implements Initializable{

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	private static Hospital hospital_account;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Pane main_pane;
	@FXML
	private JFXButton compute_decision_button;
	@FXML
	private JFXTreeTableView<Departments> department_tree_view;
	@FXML
	private final ObservableList<Departments> department_objects = FXCollections.observableArrayList();
	
	public ListAllDepartmentsController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLiteManager manager, Hospital hospital) {
		manager_object = manager;
		hospital_account = hospital;
	}
	@FXML
	private void decision_button(MouseEvent event) throws IOException {
		JFXTreeTableColumn<Departments, String> department_name = new JFXTreeTableColumn<>("Department name");
		department_name.setPrefWidth(180);
		department_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().department_name;
			}
		});
		department_name.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> number_of_patients = new JFXTreeTableColumn<>("Number of patients");
		number_of_patients.setPrefWidth(180);
		number_of_patients.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().number_of_patients;
			}
		});
		number_of_patients.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> ratio_of_deaths = new JFXTreeTableColumn<>("Ratio of deaths");
		ratio_of_deaths.setPrefWidth(180);
		ratio_of_deaths.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().ratio_of_deaths;
			}
		});
		ratio_of_deaths.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> average_hours = new JFXTreeTableColumn<>("Average Hours");
		average_hours.setPrefWidth(180);
		average_hours.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().average_hours;
			}
		});
		average_hours.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> number_of_employees = new JFXTreeTableColumn<>("Number of employees");
		number_of_employees.setPrefWidth(180);
		number_of_employees.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().number_of_employees;
			}
		});
		number_of_employees.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> cart_weight = new JFXTreeTableColumn<>("Cart Weight");
		cart_weight.setPrefWidth(180);
		cart_weight.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().cart_weight;
			}
		});
		cart_weight.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> priority_level = new JFXTreeTableColumn<>("Priority level");
		priority_level.setPrefWidth(180);
		priority_level.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().priority_level;
			}
		});
		priority_level.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> is_highest = new JFXTreeTableColumn<>("Is Highest");
		is_highest.setPrefWidth(180);
		is_highest.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().is_highest;
			}
		});
		is_highest.setResizable(false);
		
		JFXTreeTableColumn<Departments, String> expenses = new JFXTreeTableColumn<>("Expenses");
		expenses.setPrefWidth(180);
		expenses.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Departments,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Departments, String> param) {
				return param.getValue().getValue().expenses;
			}
		});
		expenses.setResizable(false);
		
		List<Department> departmentList = manager_object.getMethods().List_all_departments();
		for (Department department: departmentList) {
			department_objects.add(new Departments(department.getName(), department.getNpatients().toString(), department.getRatio().toString(), department.getAvghours().toString(), department.getNemployees().toString(), department.getcartWeight().toString(), department.getPriorityLevel().toString(), department.getIsHighest().toString(),department.getExpenses().toString()));
		}
		TreeItem<Departments> root = new RecursiveTreeItem<Departments>(department_objects, RecursiveTreeObject::getChildren);
		department_tree_view.setPlaceholder(new Label("No Departments found"));
		department_tree_view.getColumns().setAll(department_name, number_of_patients, ratio_of_deaths, average_hours, number_of_employees, cart_weight, priority_level, is_highest, expenses);
		department_tree_view.setRoot(root);
		department_tree_view.setShowRoot(false);
	}
	
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// Worker list columns creation
		
	}

// -----> REFRESH DEPARTMENT LIST VIEW <-----

	public void refreshDepartmentListView() {
		List<Department> departmentList = manager_object.getMethods().List_all_departments();
		for (Department department: departmentList) {
			department_objects.add(new Departments(department.getName(), department.getNpatients().toString(), department.getRatio().toString(), department.getAvghours().toString(), department.getNemployees().toString(), department.getcartWeight().toString(), department.getPriorityLevel().toString(), department.getIsHighest().toString(),department.getExpenses().toString()));
		}
		TreeItem<Departments> root = new RecursiveTreeItem<Departments>(department_objects, RecursiveTreeObject::getChildren);
		department_tree_view.refresh();
	}
}

//To insert columns into the list of workers with all the information
class Departments extends RecursiveTreeObject<Departments> {
	
	StringProperty department_name;
	StringProperty number_of_patients;
	StringProperty ratio_of_deaths;
	StringProperty average_hours;
	StringProperty number_of_employees;
	StringProperty cart_weight;
	StringProperty priority_level;
	StringProperty is_highest;
	StringProperty expenses;
	
	public Departments(String department_name, String number_of_patients, String ratio_of_deaths, String average_hours, String number_of_employees,
			String cart_weight, String priority_level, String is_highest, String expenses) {
		this.department_name = new SimpleStringProperty(department_name);
		this.number_of_patients = new SimpleStringProperty(number_of_patients);
		this.ratio_of_deaths = new SimpleStringProperty(ratio_of_deaths);
		this.average_hours = new SimpleStringProperty(average_hours);
		this.number_of_employees = new SimpleStringProperty(cart_weight);
		this.cart_weight = new SimpleStringProperty(cart_weight);
		this.priority_level = new SimpleStringProperty(priority_level);
		this.is_highest = new SimpleStringProperty(is_highest);
		this.expenses = new SimpleStringProperty(expenses);
	}
}




















