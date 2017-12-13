package database_related;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 
	 private static String user = "superno1_nour";
	 private static String pass = "supernoor";
	 private static String database = "superno1_buysell";
	 public static Connection getConnetion() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection conn = null;
		 String connectionUrl = "jdbc:mysql://superno1.heliohost.org:3306/"+database;
		 conn = DriverManager.getConnection(connectionUrl,user,pass);
         return conn;
	 }
	 //Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", “UserName",“Password");
}


    

