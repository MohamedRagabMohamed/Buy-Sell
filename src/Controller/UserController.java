package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modules.Pair;
import database_related.Crud;

public class UserController {
	
	public static void insertUser(String uName, String fName, String lName,
			String userEmail, String userPassword, String phone, String uAddress) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> values = new ArrayList<Pair>();
		values.add(new Pair("userName", uName));
		values.add(new Pair("firstName", fName));
		values.add(new Pair("lastName", lName));
		values.add(new Pair("email", userEmail));
		values.add(new Pair("password", userPassword));
		values.add(new Pair("phoneNumber", phone));
		values.add(new Pair("address", uAddress));
		
		Crud.insertRecord("UserTable", values);
		
	}
	
	public static Boolean userNameExist(String uName) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> values = new ArrayList<Pair>();
		values.add(new Pair("userName", uName));
		ResultSet rs = Crud.select("UserTable", values);
		Boolean resultSetEmpty = true;
		while(rs.next())
		{
			resultSetEmpty = false;
		}
		
		if(resultSetEmpty)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
