package Modules;

import java.util.ArrayList;

public class User {
	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String profilePicture;
	private String address;
	private String phoneNumber;
	private ArrayList<Advertisement> ads ;
	public static final String [] COLUMNS_NAMES  = {"id","userName" ,"email", "password" , "role" ,"firstName" , "lastName"
	                                              ,"profilePicture","address","phoneNumber"};
	
	public User() {
		ads = new ArrayList<Advertisement>();
	}
	
	public  ArrayList<Advertisement> getAds() {
		return ads;
	}

	public void addAds( ArrayList<Advertisement> ad) {
		ads = ad;
	}
	
	public User(Integer userId, String userName, String email, String password, String role, String firstName,
			String lastName, String profilePicture, String address, String phoneNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
