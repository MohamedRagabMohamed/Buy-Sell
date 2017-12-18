package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Modules.*;
import database_related.*;




public class AdvertisementController extends Dao{

	private static final String tableName = "AdvertisementTable";
	
	// Michael
	HouseController houseController;

	public AdvertisementController() {
		houseController = new HouseController();
	}
	
	public void addAdvertiesement(Advertisement advertisement,String longitude,String latitude,HttpServletRequest request) throws ClassNotFoundException, SQLException {

//				Cookie[] cookies = request.getCookies();
//				String userID="1";
//				for( Cookie c : cookies) {
//					if(c.getName()=="userID") {
//						userID=c.getValue();
//					}
//				}
		
		String houseID = houseController.getHouseId(longitude, latitude);
		System.out.println("HouseID: " + houseID);

		HttpSession s = request.getSession();
		ArrayList<Pair>advertisementValues=new ArrayList<Pair>();
		advertisementValues.add(new Pair("userID",String.valueOf(s.getAttribute("userID"))));
		advertisementValues.add(new Pair("houseID",houseID));
		advertisementValues.add(new Pair("rate",advertisement.getRate()));
		advertisementValues.add(new Pair("type",advertisement.getType()));
		advertisementValues.add(new Pair("name",advertisement.getName()));
		advertisementValues.add(new Pair("active",String.valueOf(1)));
		Crud.insertRecord("AdvertisementTable", advertisementValues);
	}

	
	public void updateAdvertisement(Advertisement advertisement) throws ClassNotFoundException, SQLException {
		ArrayList<Pair>advertisementValues=new ArrayList<Pair>();
		advertisementValues.add(new Pair("type",advertisement.getType()));
		advertisementValues.add(new Pair("name",advertisement.getName()));
		Crud.updateRecord("AdvertisementTable",advertisementValues,"houseID",advertisement.getHouseId().toString());
	}

	public Advertisement getAdvetisement(Integer id) {
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("id",id.toString()));
		Advertisement advertisement = new Advertisement();
		try {
			ResultSet rs = Crud.select("AdvertisementTable", values);
			while(rs.next()) {
				advertisement.setAdvertisementId(rs.getInt("id"));
				advertisement.setUserId(rs.getInt("userID"));
				advertisement.setHouseId(rs.getInt("houseID"));
				advertisement.setRate(rs.getString("rate"));
				advertisement.setType(rs.getString("type"));
				advertisement.setName(rs.getString("name"));
			}
			return advertisement;
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Advertisement> getAllAds() throws ClassNotFoundException, SQLException{
		String sql = "SELECT * \r\n" + 
				"FROM AdvertisementTable\r\n" + 
				"inner JOIN HouseTable ON HouseTable.id= AdvertisementTable.houseID;";
		ResultSet rs = Crud.customQuery(sql);
		ArrayList<Advertisement> advertisements = new ArrayList<Advertisement>();
		while(rs.next()) {			
			if(rs.getBoolean("active") == true)
			{
				Advertisement ad = new Advertisement();
				House home = new House();
				ad.setAdvertisementId(rs.getInt("id"));
				ad.setName(rs.getString("name"));
				ad.setHouseId(rs.getInt("houseID"));
				ad.setRate((rs.getString("rate")));
				ad.setType(rs.getString("type"));
				home.setDescription(rs.getString("description"));
				home.setFloor(rs.getString("floor"));
				home.setSize(rs.getString("size"));
				home.setStatus(rs.getString("status"));
				ad.setHouse(home);
				advertisements.add(ad);
			}
			else
			{
				continue;
			}
		}
		return advertisements;
	}
	
	
	public ArrayList<Advertisement> getAciveAds() throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * \r\n" + 
				"FROM AdvertisementTable\r\n" + ";";
		ResultSet rs = Crud.customQuery(sql);
		ArrayList<Advertisement> activeAds = new ArrayList<Advertisement>();
		while(rs.next()) {
			if(rs.getBoolean("active") == true)
			{
				Advertisement ad = new Advertisement();
				ad.setAdvertisementId(rs.getInt("id"));
				ad.setName(rs.getString("name"));
				ad.setHouseId(rs.getInt("houseID"));
				ad.setRate(rs.getString("rate"));
				ad.setType(rs.getString("type"));
				ad.setActive(rs.getBoolean("active"));
				activeAds.add(ad);
			}
			else
			{
				continue;
			}
		}
		return activeAds;
	}
	
	public boolean deleteAd(String adID) throws ClassNotFoundException, SQLException
	{
		int rowsAffected = Crud.delete(tableName, "id", adID);
		if(rowsAffected == 0)
			return false;
		else
			return true;
	}
	
	public boolean updateAdVisibility(String adID) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> values = new ArrayList<Pair>();
		values.add(new Pair("active", "0"));
		boolean state = Crud.updateRecord(tableName, values, "id", adID);
		return state;			
	}
	
	///////////////////Ragab
	
	public Advertisement getAdvertismentR(Integer ID) throws ClassNotFoundException, SQLException
	{
		Advertisement myAdvertisementData = new Advertisement();
		ArrayList<Pair> MyId = new ArrayList<>();
		MyId.add( new Pair( "id" , ID.toString() ) );
		ResultSet Adv = Crud.select("AdvertisementTable", MyId);
		while(Adv.next()) {
			myAdvertisementData = new Advertisement(Adv.getInt("id"),
					Adv.getString("name"),
					Adv.getInt("userID"),
					Adv.getInt("houseID"),
					Adv.getString("rate"),
					Adv.getString("type")
				);
		}
		MyId.clear();
		MyId.add( new Pair( "advertisementID" , ID.toString() ) );
		ResultSet AdvComments = Crud.select("CommentTable", MyId);
		ArrayList<Comment> commentss = new ArrayList<Comment>();
		while(AdvComments.next())
		{
			Comment comment = new Comment();
			comment.setComment(AdvComments.getString("comment"));
			comment.setUserId(AdvComments.getInt("userID"));
			commentss.add(comment);
		}
		System.out.println("HOUSE1: " + myAdvertisementData.getHouseId());
		myAdvertisementData.setComments(commentss);
		System.out.println("HOUSE2: " + myAdvertisementData.getHouseId());
		MyId.clear();
		MyId.add( new Pair( "id" , myAdvertisementData.getHouseId().toString() ) );
		System.out.println("NAME222 ");
		ResultSet house =  Crud.select("HouseTable", MyId);
		House newHouse =null;
		while(house.next()) {
			newHouse = new House(house.getInt("id"),
									 house.getString("size"),
									 house.getString("description"),
									 house.getString("floor"),
									 house.getString("status"),
									 house.getString("type"),
									 house.getString("images"),
									 house.getString("longitude"),
									 house.getString("latitude")
					);
		}
		System.out.println("NAME222 " + myAdvertisementData.getName());
		//this.UserID = myAdvertisementData.getUserId();
		MyId.clear();
		MyId.add( new Pair( "id" , myAdvertisementData.getUserId().toString() ) );
		ResultSet rs = Crud.select("UserTable", MyId);
		String uName="";
		while(rs.next()) {
			uName =  rs.getString("userName");
			System.out.println(myAdvertisementData.getRate());
		}
		myAdvertisementData.setHouse(newHouse);
		myAdvertisementData.setUserName(uName); 
		myAdvertisementData.setComments(commentss);
		return myAdvertisementData;
	}
	

	public void setcomment(Integer AdId,Integer uId, String comment) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> MyId = new ArrayList<>();
		MyId.add( new Pair( "userID" , uId.toString() ) );
		MyId.add( new Pair( "advertisementId" , AdId.toString() ) );
		MyId.add( new Pair( "comment" , comment ) );
		Crud.insertRecord("CommentTable", MyId);
	}
	
	public void setNotifecation(Integer advID,Integer uId, String notification ) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> MyId = new ArrayList<>();
		MyId.add( new Pair( "advID" , advID.toString() ) );
		MyId.add( new Pair( "userID" , uId.toString() ) );
		MyId.add( new Pair( "notification " , notification  ) );
		Crud.insertRecord("NotificationTable", MyId);
	}
	
	public void updateRate(Integer adID, String myRate ) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> MyId = new ArrayList<>();
		MyId.add( new Pair( "rate" , myRate ) );
		Crud.updateRecord("AdvertisementTable", MyId, "id", adID.toString());
	}
	
	public boolean addImg(Integer ID,String URL) throws ClassNotFoundException, SQLException
	{
		House myHouseData = new House();
		ArrayList<Pair> MyId = new ArrayList<>();
		MyId.add( new Pair( "id" , ID.toString() ) );
		myHouseData = (House) Crud.select("HouseTable", MyId);
		String tmp = myHouseData.getImages();
		tmp = tmp+"##"+URL;
		MyId.clear();
		MyId.add(new Pair("images",tmp));
		boolean Done = Crud.updateRecord("HouseTable",MyId,"ID",ID.toString());
		return Done;
	}
	
	
}
