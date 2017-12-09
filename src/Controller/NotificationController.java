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
			newNotification.setId(rs.getInt("id"));
			newNotification.setNotification(rs.getString("branchName"));
			newNotification.setUserID(rs.getString("lat"));
			newNotification.setHouseID(rs.getString("lon"));
			list.add(newNotification);
		}
		return list;
	}

}
