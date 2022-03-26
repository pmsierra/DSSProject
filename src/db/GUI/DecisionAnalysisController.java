package db.GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class DecisionAnalysisController implements Initializable{

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Pane main_pane;
	@FXML
	private JFXTreeTableView<BougthItems> bougth_tree_view;
	@FXML
	private final ObservableList<BougthItems> bougth_objects = FXCollections.observableArrayList();
	
	public DecisionAnalysisController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLiteManager manager) {
		manager_object = manager;
	}
	
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// Worker list columns creation
		
		JFXTreeTableColumn<BougthItems, String> worker_name = new JFXTreeTableColumn<>("Worker name");
		worker_name.setPrefWidth(180);
		worker_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BougthItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BougthItems, String> param) {
				return param.getValue().getValue().worker_name;
			}
		});
		worker_name.setResizable(false);
		
		JFXTreeTableColumn<BougthItems, String> user_name = new JFXTreeTableColumn<>("User name");
		user_name.setPrefWidth(180);
		user_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BougthItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BougthItems, String> param) {
				return param.getValue().getValue().user_name;
			}
		});
		user_name.setResizable(false);
		
		JFXTreeTableColumn<BougthItems, String> worker_telephone = new JFXTreeTableColumn<>("Telephone");
		worker_telephone.setPrefWidth(180);
		worker_telephone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BougthItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BougthItems, String> param) {
				return param.getValue().getValue().worker_telephone;
			}
		});
		worker_telephone.setResizable(false);
		
		JFXTreeTableColumn<BougthItems, String> worker_email = new JFXTreeTableColumn<>("Email");
		worker_email.setPrefWidth(180);
		worker_email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BougthItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BougthItems, String> param) {
				return param.getValue().getValue().worker_email;
			}
		});
		worker_email.setResizable(false);
		
		JFXTreeTableColumn<BougthItems, String> worker_password = new JFXTreeTableColumn<>("Password");
		worker_password.setPrefWidth(180);
		worker_password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BougthItems,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BougthItems, String> param) {
				return param.getValue().getValue().user_password;
			}
		});
		worker_password.setResizable(false);
			
		List<Worker> workers_list = manager_object.List_all_workers();
		for (Worker worker: workers_list) {
			bougth_objects.add(new BougthItems(worker.getEmail(), worker.getUser().getUserName(), worker.getWorker_name(), worker.getTelephone().toString(), worker.getUser().getPassword()));
		}
		TreeItem<BougthItems> root = new RecursiveTreeItem<BougthItems>(bougth_objects, RecursiveTreeObject::getChildren);
		worker_tree_view.setPlaceholder(new Label("No workers found"));
		worker_tree_view.getColumns().setAll(user_name, worker_name, worker_email, worker_telephone, worker_password);
		worker_tree_view.setRoot(root);
		worker_tree_view.setShowRoot(false);
	}

// -----> REFRESH WORKERS LIST VIEW <-----

	public void refreshWorkerListView() {
		bougth_objects.clear();
		List<Worker> workers_list = manager_object.List_all_workers();
		for (Worker worker: workers_list) {
			bougth_objects.add(new BougthItems(worker.getEmail(), worker.getUser().getUserName(), worker.getWorker_name(), worker.getTelephone().toString(), worker.getUser().getPassword()));
		}
		TreeItem<BougthItems> root = new RecursiveTreeItem<BougthItems>(bougth_objects, RecursiveTreeObject::getChildren);
		worker_tree_view.refresh();
	}
}

//To insert columns into the list of workers with all the information
class BougthItems extends RecursiveTreeObject<BougthItems> {
	
	StringProperty user_name;
	StringProperty worker_name;
	StringProperty worker_telephone;
	StringProperty worker_email;
	StringProperty user_password;
	
	public BougthItems(String worker_email, String user_name, String worker_name, String worker_telephone, String password) {
		this.worker_name = new SimpleStringProperty(worker_name);
		this.user_name = new SimpleStringProperty(user_name);
		this.worker_telephone = new SimpleStringProperty(worker_telephone);
		this.worker_email = new SimpleStringProperty(worker_email);
		this.user_password = new SimpleStringProperty(password);
	}
}




















