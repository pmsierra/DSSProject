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

public class DecisionAnalysisController implements Initializable{

	// -----> CLASS ATRIBUTES <-----

	private static SQLiteManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Pane main_pane;
	@FXML
	private JFXButton compute_decision_button;
	@FXML
	private JFXTreeTableView<BougthItems> bought_tree_view;
	@FXML
	private final ObservableList<BougthItems> bought_objects = FXCollections.observableArrayList();
	
	public DecisionAnalysisController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLiteManager manager) {
		manager_object = manager;
	}
	
	@FXML
	private void decision_button(MouseEvent event) throws IOException {
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
		bougth_tree_view.setPlaceholder(new Label("No workers found"));
		bougth_tree_view.getColumns().setAll(user_name, worker_name, worker_email, worker_telephone, worker_password);
		bougth_tree_view.setRoot(root);
		bougth_tree_view.setShowRoot(false);
	}
	
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// Worker list columns creation
		
	}

// -----> REFRESH WORKERS LIST VIEW <-----

	public void refreshWorkerListView() {
		bougth_objects.clear();
		List<Worker> workers_list = manager_object.List_all_workers();
		for (Worker worker: workers_list) {
			bougth_objects.add(new BougthItems(worker.getEmail(), worker.getUser().getUserName(), worker.getWorker_name(), worker.getTelephone().toString(), worker.getUser().getPassword()));
		}
		TreeItem<BougthItems> root = new RecursiveTreeItem<BougthItems>(bougth_objects, RecursiveTreeObject::getChildren);
		bougth_tree_view.refresh();
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




















