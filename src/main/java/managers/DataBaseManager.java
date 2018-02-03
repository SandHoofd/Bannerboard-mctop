package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

	private static DataBaseManager instance;
	private Connection connection;
	
	public static DataBaseManager getManager() {
		if (instance == null) {
			instance = new DataBaseManager();
		}
		return instance;
	}
	
	private DataBaseManager() {
		connectDataBase();
	}
	
	private void connectDataBase() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=minty&password=greatsqldb");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
