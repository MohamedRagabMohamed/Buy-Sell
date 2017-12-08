package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Modules.Pair;
import database_related.Crud;
import database_related.Dao;

public class UserController extends Dao{
	
	public UserController() {
		super();
	}
	
	private static final String tableName = "UserTable";
	
	public static void addUser(HttpServletRequest request) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> values = parseRequest(request);
		Crud.insertRecord(tableName, values);
	}
	
	public static Boolean userNameExist(String uName) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> values = new ArrayList<Pair>();
		values.add(new Pair("userName", uName));
		ResultSet rs = Crud.select(tableName, values);
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
