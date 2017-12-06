package database_related;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import Modules.Pair;

public class DBConnection {
	 
	 private static String user = "sql11209300";
	 private static String pass = "YiEnlbC7FG";
	 private static String database = "sql11209300";
	 public static Connection getConnetion() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection conn = null;
		 String connectionUrl = "jdbc:mysql://sql11.freesqldatabase.com:3306/"+database;
		 conn = DriverManager.getConnection(connectionUrl,user,pass);
         return conn;
	 }
	 //Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", “UserName",“Password");
}


    

