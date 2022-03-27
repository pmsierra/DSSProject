package db.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import droolsexample.priority.SQLiteManager;
import droolsexample.priority.SQLiteMethods;
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

public class DecisionAnalysisController implements Initializable{

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	private static Hospital hospital_account;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Pane main_pane;
	@FXML
	private static Label budget_label;
	@FXML
	private JFXButton compute_decision_button;
	@FXML
	private JFXTreeTableView<BoughtItems> bought_tree_view;
	@FXML
	private final ObservableList<BoughtItems> bought_objects = FXCollections.observableArrayList();
	
	public DecisionAnalysisController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLiteManager manager, SQLiteManager sQL_manager_object, Hospital hospital, Label budget) {
		manager_object = manager;
		hospital_account = hospital;
		budget_label=budget;
	}
	
	@FXML
	private void decision_button(MouseEvent event) throws IOException {
		   KieServices ks = KieServices.Factory.get();
	       KieContainer kc = ks.getKieClasspathContainer();
	       System.out.println("kk");

	        execute(ks, kc);
	        refreshItemsListView();
	}
    public static void execute(KieServices ks, KieContainer kc) {
	
		KieSession ksession = kc.newKieSession("PriorityKS");
	    System.out.println("kk2");
		
		
        // Once the session is created, the application can interact with it
	    List<String> emptyItems = new LinkedList<String>();
	    //emptyItems.add("");
	    List<String> emptyDepartments = new LinkedList<String>();
	    //emptyDepartments.add("");
	    hospital_account.setBougthItems(emptyItems);
	    hospital_account.setDepartmentOrder(emptyDepartments);
	    manager_object.getMethods().Update_hospital(hospital_account);
        ksession.insert(hospital_account);
        ksession.insert(manager_object);
        
        
        // and fire the rules
        ksession.fireAllRules();


        // and then dispose the session
        ksession.dispose();
        budget_label.setText("Current Budget: " + hospital_account.getBudget());

    }

    private static void sort(LinkedList<Resource> list) {
    	Collections.sort(list, new Comparator<Resource>() {
    	@Override
    		public int compare(Resource o1, Resource o2) {
                if (o1.getPriority() == o2.getPriority()) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return o1.getPriority().compareTo(o2.getPriority());
                }
            }
    		});
    }

	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
;
		// Worker list columns creation
		JFXTreeTableColumn<BoughtItems, String> department_name = new JFXTreeTableColumn<>("Department name");
		department_name.setPrefWidth(160);
		department_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BoughtItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BoughtItems, String> param) {
				return param.getValue().getValue().department_name;
			}
		});
		department_name.setResizable(false);
		
		JFXTreeTableColumn<BoughtItems, String> item_name = new JFXTreeTableColumn<>("Item Name");
		item_name.setPrefWidth(110);
		item_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BoughtItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BoughtItems, String> param) {
				return param.getValue().getValue().item_name;
			}
		});
		item_name.setResizable(false);
		
		JFXTreeTableColumn<BoughtItems, String> price = new JFXTreeTableColumn<>("Price");
		price.setPrefWidth(110);
		price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BoughtItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BoughtItems, String> param) {
				return param.getValue().getValue().price;
			}
		});
		price.setResizable(false);
		
		JFXTreeTableColumn<BoughtItems, String> priority_item = new JFXTreeTableColumn<>("Priority of Item");
		priority_item.setPrefWidth(110);
		priority_item.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BoughtItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BoughtItems, String> param) {
				return param.getValue().getValue().priority_item;
			}
		});
		priority_item.setResizable(false);
		
		
		/*
		List<String> boughtItems = hospital_account.getBougthItems();
		List<String> departmentOrder = hospital_account.getDepartmentOrder();
		
		for (String department: departmentOrder) {
			int position = departmentOrder.indexOf(department);
			String thisItem = boughtItems.get(position);
			Resource desiredItem = manager_object.getMethods().Search_resource_by_name(thisItem);
			bought_objects.add(new BoughtItems(department, thisItem, desiredItem.getPrice().toString(), desiredItem.getPriority()));
		}*/
		TreeItem<BoughtItems> root = new RecursiveTreeItem<BoughtItems>(bought_objects, RecursiveTreeObject::getChildren);
		bought_tree_view.setPlaceholder(new Label("No Departments found"));
		bought_tree_view.getColumns().setAll(department_name, item_name, price, priority_item);
		bought_tree_view.setRoot(root);
		bought_tree_view.setShowRoot(false);
		budget_label.setText("Current Budget: " + hospital_account.getBudget());
	

		
	}
    

   
// -----> REFRESH ITEMS LIST VIEW <-----

	public void refreshItemsListView() {
		System.out.println("My budget: " +hospital_account.getBudget());
		List<String> boughtItems = hospital_account.getBougthItems();
	    System.out.println("Bought Items: " + boughtItems);
		List<String> departmentOrder = hospital_account.getDepartmentOrder();
		System.out.println("Department Order: " + departmentOrder);
		
		for (int i = 0; i<boughtItems.size();i++) {
			String department = departmentOrder.get(i);
			String thisItem = boughtItems.get(i);
			Resource desiredItem = manager_object.getMethods().Search_resource_by_name(thisItem);
			bought_objects.add(new BoughtItems(department, thisItem, desiredItem.getPrice().toString(), desiredItem.getPriority()));
		}
		TreeItem<BoughtItems> root = new RecursiveTreeItem<BoughtItems>(bought_objects, RecursiveTreeObject::getChildren);
		bought_tree_view.refresh();
	}
}

//To insert columns into the list of workers with all the information
class BoughtItems extends RecursiveTreeObject<BoughtItems> {
	
	StringProperty department_name;
	StringProperty item_name;
	StringProperty price;
	StringProperty priority_item;
	
	public BoughtItems(String department_name, String item_name, String price, String priority_item) {
		this.department_name = new SimpleStringProperty(department_name);
		this.item_name = new SimpleStringProperty(item_name);
		this.price = new SimpleStringProperty(price);
		this.priority_item = new SimpleStringProperty(priority_item);
	}
}




















