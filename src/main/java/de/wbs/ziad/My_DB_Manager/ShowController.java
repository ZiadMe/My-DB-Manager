package de.wbs.ziad.My_DB_Manager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ShowController implements Initializable {

	@FXML
	private ComboBox<String> combo;

	@FXML
	private TableView<List<String>> tableV;

	@FXML
	private TableView<List<String>> tableVSQL;

	@FXML
	private Button sqlbtn;
	
	@FXML
	private Label lblsql;

	@FXML
	private TextArea txtarea;

	
	/**
	 * This method is attached to a button (back button) which takes the user back to the connection controller
	 * @throws IOException
	 */
	@FXML
	private void backTo() throws IOException {
		App.setRoot("Primary");
	}

	/**
	 * This method lists the tables of the database in a combobox element 
	 * @throws IOException
	 */
	@FXML
	private void updateCombo() throws IOException {
		if (combo.getItems().isEmpty()) {
			combo.getItems().addAll(DBUtils.showInCombo());
		}
	}

	/**
	 * This method is attached to a button which shows the contents of a selected table in the TableView element,
	 * the table which will be shown is the one which is selected in the combobox element
	 * @throws IOException
	 */
	@FXML
	private void showInfo() throws IOException {

		String tableName = combo.getValue();
		String sql = "SELECT * FROM " + tableName;

		tableV.getItems().clear();
		tableV.getColumns().clear();

		try (Connection conn = ConnectionManager.createConnection(); Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(sql);
			DBUtils.showInView(tableV, rs);
		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	/**
	 * This method is attached to a button (execute statement), when this button is clicked, it shows the results of executing the SQL statement
	 * which was inserted by the user in the text area, if this statement is a SELECT statement, otherwise it will just execute
	 * the statement 
	 * @throws IOException
	 */
	@FXML
	private void showSQLResult() throws IOException {
		
		String sql ;
		if(!txtarea.getText().isEmpty()) {
		 sql = txtarea.getText();
		}else {
		 sql = "" ;
		 lblsql.setText("Insert a valid SQL statement !");
		}
		tableVSQL.getItems().clear();
		tableVSQL.getColumns().clear();

		try (Connection conn = ConnectionManager.createConnection(); Statement stmt = conn.createStatement()) {
			if(sql.startsWith("SELECT")) {
			ResultSet rs = stmt.executeQuery(sql);
			DBUtils.showInView(tableVSQL, rs);
			lblsql.setText(" your SQL statement was executed !");
			}else {
				stmt.executeUpdate(sql) ;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			lblsql.setText("Insert a valid SQL statement !");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			updateCombo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
