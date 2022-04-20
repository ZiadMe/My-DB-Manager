package de.wbs.ziad.My_DB_Manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {


	@FXML
	TextField hostadd;

	@FXML
	TextField dbname;

	@FXML
	TextField portnumber;

	@FXML
	TextField username;

	@FXML
	TextField password;

	@FXML
	Label lblstatus;

	@FXML
	Button showbtn;

	
	/**
	 * This method is attached to a button (connect button) and initiates a connection to a database depending on the information 
	 * entered by the user
	 * @throws IOException
	 */
	@FXML
	private void connect() throws IOException {
		if(!hostadd.getText().isBlank() & !portnumber.getText().isBlank() & !dbname.getText().isBlank() & !username.getText().isBlank()) {
		try {

			Connection connection = ConnectionManager.createConnection("jdbc:mysql://" + hostadd.getText().trim(), portnumber.getText(),
					dbname.getText().trim(), username.getText(), password.getText());
			if(!connection.isClosed()) {
			lblstatus.setText("Connected");
			}
		} catch (SQLException e) {

			e.printStackTrace();
			lblstatus.setText("Problem with the connection !");
		}
		}
	}

	
	/**
	 * This method is attached to a button (disconnect button) and ends the connection to the database and reset the text fields
	 * @throws IOException
	 */
	@FXML
	private void disconnect() throws IOException {
		if(lblstatus.getText().equals("Connected")) {
		try {
			ConnectionManager.disconnect();
			hostadd.setText("");
			dbname.setText("");
			portnumber.setText("");
			username.setText("");
			password.setText("");
			lblstatus.setText(" Free !");
		
		} catch (Exception e) {
			e.printStackTrace();
			lblstatus.setText(" Unknown mistake !");
		}
		}
	}

	/**
	 * This method is attached to a button (show content) and it takes the user to the other window, where the user can browse the tables 
	 * of the database, show their contents and execute SQL statements
	 * @throws IOException
	 */
	@FXML
	private void showContent() throws IOException {
		if(lblstatus.getText().equals("Connected")) {
		App.setRoot("Show");
		}
	}
}
