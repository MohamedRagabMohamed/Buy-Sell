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
	 
	 private static String user = "";
	 private static String pass = "";
	 private static String database = "";
	 public static Connection getConnetion() throws SQLException, ClassNotFoundException {
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 Connection conn = null;
		 String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName="
		 +database+";integratedSecurity=true;user="+user+";password="+pass; 
		 conn = DriverManager.getConnection(connectionUrl);
         return conn;
	 }

}


    

