package Modules;

public class Notification {
	private Integer notificationId;
	private String notification;
	private String userId; 
	private String advID;
	private String advName;
	
	public String getAdvName() {
		return advName;
	}
	public void setAdvName(String advName) {
		this.advName = advName;
	}
	public Integer getId() {
		return notificationId;
	}
	public void setId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getUserID() {
		return userId;
	}
	public String getAdvID() {
		return advID;
	}
	public void setAdvID(String advID) {
		this.advID = advID;
	}
	public void setUserID(String userID) {
		this.userId = userID;
	}

}
