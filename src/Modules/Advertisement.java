package Modules;

public class Advertisement {
	private Integer advertisementId;
	private Integer userId;
	private Integer houseId;
	private House house;
	private Integer rate;
	private String type; 	// sell or rent
	private String name;
	private Boolean active;
	
	public Advertisement() {
		
	}
	public Advertisement(Integer advertisementId,String name, Integer userId, Integer houseId, Integer rate, String type) {
		super();
		this.advertisementId = advertisementId;
		this.name = name;	
		this.userId = userId;
		this.houseId = houseId;
		this.rate = rate;
		this.type = type;
		this.active = true;
	}



	public Integer getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
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
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
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
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public Boolean isActive()
	{
		return this.active;
	}
	public void setAdVisibility(Boolean state)
	{
		this.active = state;
	}
}
