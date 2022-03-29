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
import droolsexample.priority.Resource;
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

public class ShowWishlistController implements Initializable{

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	private static Department department_account;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Pane main_pane;
	@FXML
	private JFXTreeTableView<Resources> resource_tree_view;
	@FXML
	private final ObservableList<Resources> resource_objects = FXCollections.observableArrayList();
	@FXML
	private Label exist_label;
	
	public ShowWishlistController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLiteManager manager, Department department) {
		manager_object = manager;
		department_account = department;
	}
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		exist_label.setVisible(false);
		JFXTreeTableColumn<Resources, String> resource_name = new JFXTreeTableColumn<>("Item name");
		resource_name.setPrefWidth(180);
		resource_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Resources,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Resources, String> param) {
				return param.getValue().getValue().resource_name;
			}
		});
		
		resource_name.setResizable(false);
		
	
		JFXTreeTableColumn<Resources, String> resource_priority = new JFXTreeTableColumn<>("Item priority");
		resource_priority.setPrefWidth(180);
		resource_priority.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Resources,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Resources, String> param) {
				return param.getValue().getValue().resource_priority;
			}
		});
		resource_priority.setResizable(false);
		
		JFXTreeTableColumn<Resources, String> resource_price = new JFXTreeTableColumn<>("Item price");
		resource_price.setPrefWidth(180);
		resource_price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Resources,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Resources, String> param) {
				return param.getValue().getValue().resource_price;
			}
		});
		resource_price.setResizable(false);
		if(department_account.getWishlistshopping().size()!=0) {
			for (Resource resource: department_account.getWishlistshopping()) {
				resource_objects.add(new Resources(resource.getName(), resource.getPriority(), resource.getPrice().toString()));
				}
			TreeItem<Resources> root = new RecursiveTreeItem<Resources>(resource_objects, RecursiveTreeObject::getChildren);
			resource_tree_view.setPlaceholder(new Label("No Resources found"));
			resource_tree_view.getColumns().setAll(resource_name, resource_priority, resource_price);
			resource_tree_view.setRoot(root);
			resource_tree_view.setShowRoot(false);
		}else {
			exist_label.setVisible(true);
			exist_label.setText("Your cart is empty!");
		}
			
	}
	

		
		// Worker list columns creation
		
	

// -----> REFRESH DEPARTMENT LIST VIEW <-----

	public void refreshResourceListView() {
		//List<Department> departmentList = manager_object.getMethods().List_all_departments();
		//for (Department department: department_account) {
		resource_objects.add(new Resources(department_account.getWishlistshopping().get(0).getName(), department_account.getWishlistshopping().get(0).getPriority(), department_account.getWishlistshopping().get(0).getPrice().toString()));
		//}
		TreeItem<Resources> root = new RecursiveTreeItem<Resources>(resource_objects, RecursiveTreeObject::getChildren);
		resource_tree_view.refresh();
	}
}

//To insert columns into the list of workers with all the information
class Resources extends RecursiveTreeObject<Resources> {
	
	StringProperty resource_name;
	StringProperty resource_priority;
	StringProperty resource_price;

	
	public Resources(String resource_name, String resource_priority, String resource_price) {
		this.resource_name = new SimpleStringProperty(resource_name);
		this.resource_priority = new SimpleStringProperty(resource_priority);
		this.resource_price = new SimpleStringProperty(resource_price);
	}
}