package Modules;

public class Notification {
	private Integer notificationId;
	private Notification notification;
	private Integer userId; 
	private Integer houseId;
	
	public Integer getId() {
		return notificationId;
	}
	public void setId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public Integer getUserID() {
		return userId;
	}
	public void setUserID(Integer userID) {
		this.userId = userID;
	}
	public Integer getHouseID() {
		return houseId;
	}
	public void setHouseID(Integer houseID) {
		this.houseId = houseID;
	}
}
