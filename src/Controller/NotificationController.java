package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modules.Notification;
import Modules.Pair;
import database_related.Crud;

public class NotificationController {
	public ArrayList<Notification> getNotifications(String userID) throws ClassNotFoundException, SQLException{
		ArrayList<Notification>list=new ArrayList<Notification>();
		ArrayList<Pair>values=new ArrayList<Pair>();
		values.add(new Pair("userID",userID));
		ResultSet rs=Crud.select("NotificationTable",values);
		while (rs.next()) {
			Notification newNotification=new Notification();
			newNotification.setNotification(rs.getString("notification"));
			String id=rs.getString("advID");
			newNotification.setAdvID(id);
			newNotification.setAdvName(getAdvName(id));
			list.add(newNotification);
		}
		for(Notification no : list) {
			System.out.println(no.getAdvID());
			System.out.println(no.getAdvName());
			System.out.println(no.getNotification());
			System.out.println("----- --");
		}
		return list;
	}
	private String getAdvName(String advId) throws ClassNotFoundException, SQLException {
		ArrayList<Pair>values=new ArrayList<Pair>();
		values.add(new Pair("id",advId));
		ResultSet rs=Crud.select("AdvertisementTable",values);
		String name="";
		while (rs.next()) {
			name=rs.getString("name");
		}
		return name;
	}

}
