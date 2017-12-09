package database_related;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 
	 private static String user = "sql11209300";
	 private static String pass = "YiEnlbC7FG";
	 private static String database = "sql11209300";
	 public static Connection getConnetion() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection conn = null;
		 String connectionUrl = "jdbc:mysql://sql11.freemysqlhosting.net:3306/"+database;
		 conn = DriverManager.getConnection(connectionUrl,user,pass);
         return conn;
	 }
	 //Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", “UserName",“Password");
}


    

