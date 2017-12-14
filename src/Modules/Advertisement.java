package Modules;

import java.util.ArrayList;

public class Advertisement {
	private Integer advertisementId;
	private Integer userId;
	private Integer houseId;	
	private String rate;
	private String type; 	// sell or rent
	private String name;
	private Boolean active;
	private String UserName;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	private House house = new House();
		
	public Advertisement() {
		
	}
	
	public Advertisement(Integer advertisementId, String Name, Integer userId, Integer houseId, String rate, String type ){//, ArrayList<Comment> comments) {
		this.advertisementId = advertisementId;
		this.userId = userId;
		this.houseId = houseId;
		this.name = Name;
		this.rate = rate;
		this.type = type;
		this.active = true;
		//this.comments = comments;
	}

	public Integer getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	
}
