package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modules.Advertisement;
import Modules.House;
import Modules.Pair;
import database_related.Crud;
import database_related.Dao;

public class AdvertisementController extends Dao{

	private static final String tableName = "AdvertisementTable";
	
	public ArrayList<Advertisement> getAllAds() throws ClassNotFoundException, SQLException{
		String sql = "SELECT * \r\n" + 
				"FROM AdvertisementTable\r\n" + 
				"inner JOIN HouseTable ON HouseTable.id= AdvertisementTable.houseID;";
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
