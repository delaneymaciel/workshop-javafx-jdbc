package gui;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;
	private static Method columnToFitMethod;
	
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;	
	
	@FXML
	private Button btNew;
	
	
	private ObservableList<Department> obsList;
	
	
	@FXML
	public void onBtNewAcction() {
		System.out.println("onBtNewAcction");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}


	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		//tableViewDepartment.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
		//tableViewDepartment.setMaxWidth(1f * Integer.MAX_VALUE * 80);
		

	    /*for (TableColumn<?, ?> tempCol : tableViewDepartment.getColumns()) {
	    	System.out.println(tempCol.getId());
	    	
	    }*/
		
	    

	}
	

	
	public void updateTableView() {
		if (this.service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableList(list);
		tableViewDepartment.setItems(obsList);
	}

}
