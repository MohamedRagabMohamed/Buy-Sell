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

		HttpSession s = request.getSession();
		ArrayList<Pair>values=new ArrayList<Pair>();
		values.add(new Pair("userID",String.valueOf(s.getAttribute("userID"))));
		values.add(new Pair("status",status));
		values.add(new Pair("type",type));
		values.add(new Pair("size",size));
		Crud.insertRecord("AlertTable", values);
	
	}

	public static void setNotoficationForAlert(String status,String type,String size,String houseID) throws ClassNotFoundException, SQLException {
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("status",status));
		values.add(new Pair("type",type));
		values.add(new Pair("size",size));
		ResultSet rs = Crud.select("AlertTable", values);
		String userID="";
		String advID=getAdvID(houseID);
		String not="There is a new advertisement matches your alert";
		while(rs.next()) {
			userID=rs.getString("userID");
			values.clear();
			values.add(new Pair("notification",not));
			values.add(new Pair("userID",userID));
			values.add(new Pair("advID",advID));
			Crud.insertRecord("NotificationTable", values);
		}
	}
	private static String getAdvID(String houseID) throws ClassNotFoundException, SQLException {
		String advID="-1";
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("houseID",houseID));
		ResultSet rs=Crud.select("AdvertisementTable", values);
		while(rs.next()) {
			advID=rs.getString("id");
		}
		return advID;
	}

}
