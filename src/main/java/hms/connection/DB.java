package hms.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

	public static Connection getConnection() throws Exception {

		// load a driver class
		Class.forName("com.mysql.cj.jdbc.Driver");

		// established a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "gaurav18");
		return connection;
	}

}
