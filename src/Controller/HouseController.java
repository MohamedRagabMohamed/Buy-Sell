package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modules.House;
import Modules.Pair;
import database_related.Crud;

public class HouseController {

	public HouseController() {

	}
	public void addHouse(House house) throws ClassNotFoundException, SQLException {	
		ArrayList<Pair>houseValues=new ArrayList<Pair>();
		houseValues.add(new Pair("size",house.getSize()));
		houseValues.add(new Pair("description",house.getDescription()));
		houseValues.add(new Pair("floor",house.getFloor()));
		houseValues.add(new Pair("status",house.getStatus()));
		houseValues.add(new Pair("type",house.getType()));
		houseValues.add(new Pair("images",house.getImages()));
		houseValues.add(new Pair("longitude",house.getLongitude()));
		houseValues.add(new Pair("latitude",house.getLatitude()));
		Crud.insertRecord("HouseTable", houseValues);
	}

	public String getHouseId(String longitude, String latitude) throws ClassNotFoundException, SQLException {
		String houseID = "";
		ArrayList<Pair>values=new ArrayList<Pair>();
		values.add(new Pair("longitude",longitude));
		values.add(new Pair("latitude",latitude));
		ResultSet rs=Crud.select("HouseTable", values);
		while(rs.next()) {
			houseID = rs.getString("id");
		}
		return houseID;
	}

	public void updateHouse(House house) throws ClassNotFoundException, SQLException {
		ArrayList<Pair>houseValues=new ArrayList<Pair>();
		houseValues.add(new Pair("size",house.getSize()));
		houseValues.add(new Pair("description",house.getDescription()));
		houseValues.add(new Pair("floor",house.getFloor()));
		houseValues.add(new Pair("status",house.getStatus()));
		houseValues.add(new Pair("type",house.getType()));
		houseValues.add(new Pair("images",house.getImages()));
		houseValues.add(new Pair("longitude",house.getLongitude()));
		houseValues.add(new Pair("latitude",house.getLatitude()));
		
		String houseId = getHouseId(house.getLongitude(), house.getLatitude());
		Crud.updateRecord("HouseTable", houseValues, "id",houseId);
	}

	public House getHouse(Integer id) {
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("id",id.toString()));
		House house = new House();
		try {
			ResultSet rs = Crud.select("HouseTable", values);
			while(rs.next()) {
				house.setHouseId(rs.getInt("id"));
				house.setSize(rs.getString("size"));
				house.setDescription(rs.getString("description"));
				house.setFloor(rs.getString("floor"));
				house.setStatus(rs.getString("status"));
				house.setType(rs.getString("type"));
				house.setImages(rs.getString("images"));
				house.setLongitude(rs.getString("longitude"));
				house.setLatitude(rs.getString("latitude"));
			}
			return house;

		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
