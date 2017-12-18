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
			String advID=rs.getString("advID");
			newNotification.setAdvID(advID);
			list.add(newNotification);
		}
		for(Notification no : list) {
			System.out.println(no.getAdvName());
			System.out.println(no.getAdvName());
			System.out.println(no.getNotification());
			System.out.println("----- --");
		}
		return list;
	}
}
