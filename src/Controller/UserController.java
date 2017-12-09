package Controller;
import database_related.*;
import Modules.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
	static Crud db ; 
	static final String tableName  = "UserTable";
	public UserController() {
		db = new Crud();
	}
	
	public User getUser(Integer id ) {
		ArrayList<Pair>values = new ArrayList<Pair>();
		values.add(new Pair("id",id.toString()));
		try {
			ResultSet user = db.select(tableName,values);
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
			ResultSet ads = db.select("AdvertisementTable", values2);
			ArrayList<Advertisement> adsList = new ArrayList<Advertisement> ();
			while (ads.next()) {
				Advertisement ad= new Advertisement();
				ad.setName(ads.getString("name"));
				ad.setAdvertisementId(ads.getInt("id") );
				ad.setUserID(ads.getInt("userID"));
				ad.setHouseID(ads.getInt("houseID"));
				ad.setRate(ads.getInt("rate"));
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
}
