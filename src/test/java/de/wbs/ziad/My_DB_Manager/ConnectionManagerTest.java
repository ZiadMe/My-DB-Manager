package de.wbs.ziad.My_DB_Manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

class ConnectionManagerTest {

	@Test
	void testcreateConnection() {
		
		ConnectionManager.setHost("jdbc:mysql://localhost");
		ConnectionManager.setDb_PORT("3306"); 
		ConnectionManager.setDb_NAME("test123");
		ConnectionManager.setDb_USER("root");;
		ConnectionManager.setDb_PASS("");
		
		try {
			
			Connection con = ConnectionManager.createConnection() ;
			assertTrue(con instanceof Connection) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testcreateConnectionMitP() {
		
		try {
			
			Connection con = ConnectionManager.createConnection("jdbc:mysql://localhost", "3306", "test123", "root", "") ;
			assertTrue(con instanceof Connection) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDisconnect() {
		
		
		try {
			Connection con = ConnectionManager.createConnection() ;
			con.close() ;
			assertTrue(con.isClosed()) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
