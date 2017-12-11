package database_related;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modules.Pair;

public class Crud {
	public static void insertRecord(String tableName,ArrayList<Pair>values) throws ClassNotFoundException, SQLException {
		Connection con=DBConnection.getConnetion();
		String query ="INSERT INTO "+tableName+" (";
		for(int i=0 ; i<values.size() ; i++) {
			query+=values.get(i).first;
			if(i+1<values.size())
				query+=",";
		}
		query+=" ) values (";
		for(int i=0 ; i<values.size() ; i++) {
			query+="'";
			query+=values.get(i).second;
			if(i+1<values.size())
				query+="' ,";
		}
		query+="' ";
		query+=" ) ;";
		//System.out.println(query);		
		Statement stmt=con.createStatement();
		stmt.executeUpdate(query);
	}
	public static ResultSet select(String tableName,ArrayList<Pair>values) throws ClassNotFoundException, SQLException {
		Connection con=DBConnection.getConnetion();
		String query ="SELECT * FROM "+tableName;
		if(values.size()!=0) {
			query+=" WHERE ";
			for(int i=0 ; i<values.size() ; i++) {
				query+=values.get(i).first;
				query+="= '";
				query+=values.get(i).second;
				query+="'";
				if(i+1<values.size())
					query+=" and ";
			}
		}
		query+=" ;";
		//System.out.println(query);		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		return rs;
	}
	public static boolean  updateRecord (String tableName , ArrayList<Pair> values , String Left,String Right ) throws SQLException, ClassNotFoundException
	{
		String sqlStatment= "UPDATE "+tableName+" SET ";
		for (int i = 0; i < values.size(); i++) {
			sqlStatment+=values.get(i).first+" = \'"+values.get(i).second+"\'";
			if (i+1!=values.size())
				sqlStatment+=" , ";
		}
		sqlStatment+=" WHERE "+Left+" = \'"+Right+"\' ; ";
		System.out.println(sqlStatment);
		Connection con=  DBConnection.getConnetion();
		PreparedStatement st=  con.prepareStatement(sqlStatment);
		st.executeUpdate(); 
		//DBConnection.closeConnection();
		return true ;
	}
	
	
	
	public int delete(String tableName,String colmName,String colmValue) throws SQLException, ClassNotFoundException {
	    Connection conn=DBConnection.getConnetion();
	    int status=1;
		String sql="delete  from "+tableName+" where "+colmName+" = \'"+colmValue+"\' ;";
		

		PreparedStatement stmt=conn.prepareStatement(sql);
		status=stmt.executeUpdate();
		//DBConnection.closeConnection();
		return status;
	}
	
	
	public static ResultSet custumQuery(String sql) throws ClassNotFoundException, SQLException {
		Connection con=DBConnection.getConnetion();
		//System.out.println(query);		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		 ArrayList<Pair>values=new ArrayList<Pair>();
		 values.add(new Pair("userID","1"));
		 values.add(new Pair("advertisementID","1"));
		 values.add(new Pair("comment","hello from michael"));
		 insertRecord("CommentTable", values);
	 }
}
