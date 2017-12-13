package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Modules.Advertisement;
import Modules.House;
import Modules.Pair;
import database_related.Crud;
import database_related.Dao;

public class AdvertisementController extends Dao{

	private static final String tableName = "AdvertisementTable";
	
	// Michael
	HouseController houseController;

	public AdvertisementController() {
		houseController = new HouseController();
	}
	
	public void addAdvertiesement(Advertisement advertisement,String longitude,String latitude,HttpServletRequest request) throws ClassNotFoundException, SQLException {

		//		Cookie[] cookies = request.getCookies();
		String userID="1";
		//		for( Cookie c : cookies) {
		//			if(c.getName()=="userID") {
		//				userID=c.getValue();
		//			}
		//		}

		String houseID = houseController.getHouseId(longitude, latitude);

		ArrayList<Pair>advertisementValues=new ArrayList<Pair>();
		advertisementValues.add(new Pair("userID",userID));
		advertisementValues.add(new Pair("houseID",houseID));
		advertisementValues.add(new Pair("rate",String.valueOf(advertisement.getRate())));
		advertisementValues.add(new Pair("type",advertisement.getType()));
		advertisementValues.add(new Pair("name",advertisement.getName()));
		advertisementValues.add(new Pair("active",String.valueOf(1)));
		Crud.insertRecord("AdvertisementTable", advertisementValues);
	}

	public void updateAdvertisement(Advertisement advertisement) throws ClassNotFoundException, SQLException {
		ArrayList<Pair>advertisementValues=new ArrayList<Pair>();
		advertisementValues.add(new Pair("type",advertisement.getType()));
		advertisementValues.add(new Pair("name",advertisement.getName()));
		Crud.updateRecord("AdvertisementTable",advertisementValues,"houseID",advertisement.getHouseID().toString());
	}

	public Advertisement getAdvetisement(Integer id) {
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("id",id.toString()));
		Advertisement advertisement = new Advertisement();
		try {
			ResultSet rs = Crud.select("AdvertisementTable", values);
			while(rs.next()) {
				advertisement.setAdvertisementId(rs.getInt("id"));
				advertisement.setUserID(rs.getInt("userID"));
				advertisement.setHouseID(rs.getInt("houseID"));
				advertisement.setRate(rs.getInt("rate"));
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
				"FROM superno1_buysell.AdvertisementTable\r\n" + 
				"inner JOIN superno1_buysell.HouseTable ON superno1_buysell.HouseTable.id= superno1_buysell.AdvertisementTable.houseID;";
		ResultSet rs = Crud.customQuery(sql);
		ArrayList<Advertisement> advertisements = new ArrayList<Advertisement>();
		while(rs.next()) {
			Advertisement ad = new Advertisement();
			House home = new House();
			ad.setName(rs.getString("name"));
			ad.setHouseID(rs.getInt("houseID"));
			ad.setRate((int) rs.getFloat("rate"));
			ad.setType(rs.getString("type"));
			home.setDescription(rs.getString("description"));
			home.setFloor(rs.getString("floor"));
			home.setSize(rs.getString("size"));
			home.setStatus(rs.getString("status"));
			ad.setHouse(home);
			advertisements.add(ad);
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
				ad.setHouseID(rs.getInt("houseID"));
				ad.setRate((int) rs.getFloat("rate"));
				ad.setType(rs.getString("type"));
				ad.setAdVisibility(rs.getBoolean("active"));
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
	
	
}
