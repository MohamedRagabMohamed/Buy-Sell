package database_related;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/*
public class DBConnection {

	 private static String user = "root";
	 private static String pass = "root";
	 private static String database = "buy-sell";
	 public static Connection getConnetion() throws SQLException, ClassNotFoundException {
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 Connection conn = null;
		 String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName="
		 +database+";integratedSecurity=true;user="+user+";password="+pass; 
		 conn = DriverManager.getConnection(connectionUrl);
        return conn;
	 }
}    
*/


public class DBConnection {


	private static String user = "root";
	private static String pass = "root";
	private static String database = "buy-sell";
	public static Connection getConnetion() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		String connectionUrl = "jdbc:mysql://localhost/" + database;
		conn = DriverManager.getConnection(connectionUrl, user, pass);
		return conn;
	}
	//Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", “UserName",“Password");
}


/*
public class DBConnection {
	 
	 //String dbURL = "jdbc:sqlserver://localhost\\sqlexpress";
	 //private static String dbURL = "jdbc:sqlserver://localhost:1433";
	 private static String user = "sa";
	 private static String pass = "0000";
	 private static String database = "buy-sell";
	 public static Connection getConnetion() throws SQLException, ClassNotFoundException {
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 Connection conn = null;
		 String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName="
		 +database+";integratedSecurity=true;user="+user+";password="+pass; 
		 conn = DriverManager.getConnection(connectionUrl);
        return conn;
	 }
}    
*/


