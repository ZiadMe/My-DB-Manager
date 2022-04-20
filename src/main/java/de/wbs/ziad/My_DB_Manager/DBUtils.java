package de.wbs.ziad.My_DB_Manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * This class is responsible for the vital operations which will be applied to the database, such as showing 
 * the tables and their contents, in addition to executing SQL statements 
 * @author M Zyad Sawas
 *
 */
public class DBUtils {

	/**
	 * This method extracts the names of the tables which are located in the database and returns them
	 * in a form of a list of strings
	 * @return
	 * @throws IOException
	 */
	public static List<String> showInCombo() throws IOException {

		List<String> tables = new ArrayList<>();

		try (Connection conn = ConnectionManager.createConnection()) {
			String[] types = { "TABLE" };
			ResultSet rs = conn.getMetaData().getTables(conn.getCatalog(), conn.getSchema(), "%", types);

			while (rs.next()) {
				tables.add(rs.getString("TABLE_NAME"));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return tables;
	}

	/**
	 * This method deals with a table which is a part of the database, it takes the table as structure and 
	 * data and represents it, so that it gets ready to be shown in a TableView element
	 * 
	 * @param table Data structure which is compatible with the element called TableView in JavaFX
	 * @param rs A result set which holds data from the database
	 * @throws IOException
	 */
	public static void showInView(TableView<List<String>> table, ResultSet rs) throws IOException {

		try {
			ObservableList<List<String>> oblist = FXCollections.observableArrayList();

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn<List<String>, String> column = new TableColumn<>(rs.getMetaData().getColumnName(i + 1));
				column.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().get(j)));
				table.getColumns().add(column);

			}

			while (rs.next()) {
				List<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				oblist.add(row);

			}
			table.getItems().addAll(oblist);

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}
