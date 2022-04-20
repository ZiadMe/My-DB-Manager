package de.wbs.ziad.My_DB_Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	/**
	 * This class is to initiate a connection to the database
	 * It was built based on the pattern design single tone, meanings that at a certain time, there will be only one
	 * connection to one of the available databases
	 * 
	 * @author M Zyad Sawas
	 */
	 

	private static String host; // = "jdbc:mysql://localhost"
	private static String db_NAME; // = "test123"
	private static String db_PORT; // = "3306"
	private static String db_USER; // = "root"
	private static String db_PASS; // = ""
	private static Connection conn;

	private ConnectionManager() {

	}
/**
 * This method creates a connection without taking any parameters
 * @return
 */
	public static Connection createConnection() throws SQLException {

			try {
				conn = DriverManager.getConnection(host + ":" + db_PORT + "/" + db_NAME + "?serverTimezone=UTC", db_USER,
					db_PASS);
					
			return conn;

		} catch (SQLException e) {

			e.printStackTrace();
			
			return null;
		}
	
	}
/**
 * This method creates a connection depending on five parameters :
 * @param host this the address or URL of database location
 * @param db_PORT this is the port number
 * @param db_NAME this is the name of the database
 * @param db_USER this is the name of the user
 * @param db_PASS this is the password of the user
 * @return This is the connection to the database
 * @throws SQLException
 */
	public static Connection createConnection(String host, String db_PORT, String db_NAME, String db_USER,
			String db_PASS) throws SQLException {

		// try {

		ConnectionManager.host = host;
		ConnectionManager.db_PORT = db_PORT;
		ConnectionManager.db_NAME = db_NAME;
		ConnectionManager.db_USER = db_USER;
		ConnectionManager.db_PASS = db_PASS;

	try {
		Connection	conn = DriverManager.getConnection(host + ":" + db_PORT + "/" + db_NAME + "?serverTimezone=UTC", db_USER,
				db_PASS);
	

		return conn;

		} catch (SQLException e) {
			
			
			e.printStackTrace();
			return null ;
		}
		
	}
/**
 * This method closes the connection and resets all the parameters
 */
	public static void disconnect() {

		ConnectionManager.host = "";
		ConnectionManager.db_PORT = "";
		ConnectionManager.db_NAME = "";
		ConnectionManager.db_USER = "";
		ConnectionManager.db_PASS = "";
		
		try {
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ConnectionManager.host = host;
	}

	public static String getDb_NAME() {
		return db_NAME;
	}

	public static void setDb_NAME(String db_NAME) {
		ConnectionManager.db_NAME = db_NAME;
	}

	public static String getDb_PORT() {
		return db_PORT;
	}

	public static void setDb_PORT(String db_PORT) {
		ConnectionManager.db_PORT = db_PORT;
	}

	public static String getDb_USER() {
		return db_USER;
	}

	public static void setDb_USER(String db_USER) {
		ConnectionManager.db_USER = db_USER;
	}

	public static String getDb_PASS() {
		return db_PASS;
	}

	public static void setDb_PASS(String db_PASS) {
		ConnectionManager.db_PASS = db_PASS;
	}

}
