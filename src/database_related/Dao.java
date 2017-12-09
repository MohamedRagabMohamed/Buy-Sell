package database_related;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import Modules.Pair;

public class Dao {
	//private String encryptionKey="ECM_AdminPanel17";
	public static Crud crud = new Crud();
	//private static final String ALGO = "AES";
	// private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e',
	// 's', 't', 'S', 'e', 'c', 'r', 'e', 't',
	// 'K', 'e', 'y' };

	public Dao() {
	}
	protected static ArrayList<Pair> parseRequest(HttpServletRequest request) {

		ArrayList<Pair> requestData = new ArrayList<>();

		Map<String, String[]> map = request.getParameterMap();
		ArrayList<String> keys = new ArrayList<>();

		keys.addAll(map.keySet());
		System.out.println("keys: " + keys);
		for (int i = 0; i < map.size(); i++) {
			if (isUnwantedKey(keys.get(i)))
				continue;
			if(keys.get(i).equals("password"))
				try {
					requestData.add(new Pair(keys.get(i), encrypt(request.getParameter(keys.get(i)))));
				} catch (Exception e) {
					e.printStackTrace();
				}
			else	
				requestData.add(new Pair(keys.get(i), request.getParameter(keys.get(i))));
		}
		return requestData;
	}

	private static boolean isUnwantedKey(String key) {
		// list of unwanted keys to ignore
		if (key.equalsIgnoreCase("confirmemail"))
			return true;
		else if (key.equalsIgnoreCase("confirmpassword"))
			return true;
		else
			return false;
	}

//	protected static ResultSet getAll(String tableName) throws ClassNotFoundException, SQLException {
//		ResultSet rs = crud.select(tableName, DBConnection.getActiveConnection());
//		return rs;
//	}

	// if return == -1 , nothing done
	// if return == 0 , nothing deleted (not found the record
	// else return= the deleted rows count , not sure
//	protected int DeleteOne(String tableName, String Left, String Right) throws ClassNotFoundException, SQLException {
//		int value = crud.delete(tableName, Left, Right);
//		return value;
//
//	}

	public static String encrypt(String Data)
	{
		String encryptedValue = Base64.getEncoder().encodeToString(Data.getBytes());
		return encryptedValue;
	}


	protected static String decrypt(String encryptedData) throws Exception {
		byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
		String decryptedValue = new String(decodedBytes);
		return decryptedValue;
	}
}
