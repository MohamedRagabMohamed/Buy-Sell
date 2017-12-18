package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Modules.Alert;
import Modules.House;
import Modules.Pair;
import database_related.Crud;

public class AlertController {
	
	public static void addAlert(String status,String type,String size,HttpServletRequest request) throws ClassNotFoundException, SQLException {
//		Cookie[] cookies = request.getCookies();
//		String userID="1";
//		for( Cookie c : cookies) {
//			if(c.getName()=="userID") {
//				userID=c.getValue();
//			}
//		}
//		System.out.println(userID+" helloooooo");
		
		HttpSession s = request.getSession();
		ArrayList<Pair>values=new ArrayList<Pair>();
		values.add(new Pair("userID",String.valueOf(s.getAttribute("userID"))));
		values.add(new Pair("status",status));
		values.add(new Pair("type",type));
		values.add(new Pair("size",size));
		Crud.insertRecord("AlertTable", values);
	}
	public static void setNotoficationForAlert(String status,String type,String size) throws ClassNotFoundException, SQLException {
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("status",status));
		values.add(new Pair("type",type));
		values.add(new Pair("size",size));
		ResultSet rs = Crud.select("AlertTable", values);
		String userID="";
		while(rs.next()) {
			userID=rs.getString("userID");
			values.clear();
		}
	}
}
