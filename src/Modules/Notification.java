package Modules;

public class Notification {
	private Integer notificationId;
	private String notification;
	private String userId; 
	private String advId;
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
	public void setUserID(String userID) {
		this.userId = userID;
	}
	public String getAdvID() {
		return advId;
	}
	public void setAdvID(String advId) {
		this.advId = advId;
	}
}
