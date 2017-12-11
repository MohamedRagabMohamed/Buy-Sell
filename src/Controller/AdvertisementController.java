package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modules.Advertisement;
import Modules.House;
import database_related.Crud;
import database_related.Dao;

public class AdvertisementController extends Dao{

	private static final String tableName = "AdvertisementTable";
	
	public ArrayList<Advertisement> getAllAds() throws ClassNotFoundException, SQLException{
		String sql = "SELECT * \r\n" + 
				"FROM AdvertisementTable\r\n" + 
				"inner JOIN HouseTable ON HouseTable.id= AdvertisementTable.houseID;";
		ResultSet rs = Crud.custumQuery(sql);
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
	
	
	
}
