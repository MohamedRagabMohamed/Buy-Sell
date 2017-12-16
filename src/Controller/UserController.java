package Controller;
import database_related.*;
import Modules.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Modules.Pair;
import database_related.Crud;
import database_related.Dao;

public class UserController extends Dao{
	
	public UserController() {
		super();
	}
	
	private static final String tableName = "UserTable";
	
	public static void addUser(HttpServletRequest request) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> values = parseRequest(request);
		Crud.insertRecord(tableName, values);
	}
	
	public static Boolean userNameExist(String uName) throws ClassNotFoundException, SQLException
	{
		ArrayList<Pair> values = new ArrayList<Pair>();
		values.add(new Pair("userName", uName));
		ResultSet rs = Crud.select(tableName, values);
		Boolean resultSetEmpty = true;
		while(rs.next())
		{
			resultSetEmpty = false;
		}
		
		if(resultSetEmpty)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public User getUser(Integer id ) {
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("id",id.toString()));
		try {
			ResultSet user = Crud.select(tableName,values);
			user.first();
			User returnUser = new User(user.getInt("id"),
									user.getString("userName"),
									user.getString("email"),
									user.getString("password"),
									user.getString("role"),
									user.getString("firstName"),
									user.getString("lastName"),
									user.getString("profilePicture"),			
									user.getString("address"),	
									user.getString("phoneNumber")
					);
			ArrayList<Pair>values2 = new ArrayList<Pair>();
			values.add(new Pair("userID",id.toString()));
			ResultSet ads = Crud.select("AdvertisementTable", values2);
			ArrayList<Advertisement> adsList = new ArrayList<Advertisement> ();
			
			ads.first();
			Advertisement ad= new Advertisement();
			ad.setName(ads.getString("name"));
			ad.setAdvertisementId(ads.getInt("id") );
			ad.setUserId(ads.getInt("userID"));
			ad.setHouseId(ads.getInt("houseID"));
			ad.setRate(ads.getString("rate"));
			ad.setType(ads.getString("type"));
			adsList.add(ad);
			while (ads.next() == true) {
				System.out.println("HEEY");
				ad= new Advertisement();
				ad.setName(ads.getString("name"));
				ad.setAdvertisementId(ads.getInt("id") );
				ad.setUserId(ads.getInt("userID"));
				ad.setHouseId(ads.getInt("houseID"));
				ad.setRate(ads.getString("rate"));
				ad.setType(ads.getString("type"));
				adsList.add(ad);
			}
			returnUser.addAds(adsList);
			return returnUser;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void UpdateUser(User user) throws ClassNotFoundException, SQLException {
		ArrayList<Pair> parm = new ArrayList<Pair>();
		parm.add(new Pair("userName",user.getUserName() ));
		parm.add(new Pair("email",user.getEmail() ));
		parm.add(new Pair("firstName",user.getFirstName() ));
		parm.add(new Pair("profilePicture",user.getProfilePicture() ));
		parm.add(new Pair("lastName",user.getLastName() ));
		parm.add(new Pair("address",user.getAddress() ));
		parm.add(new Pair("phoneNumber",user.getPhoneNumber() ));
		Crud.updateRecord(tableName, parm, "UserTable.id",user.getUserId().toString() );
	}
	
}
