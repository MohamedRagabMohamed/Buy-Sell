package Servlets;

import java.awt.geom.CubicCurve2D;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.AdvertisementController;
import Controller.UserController;
import Modules.*;
import database_related.Crud;

/**
 * Servlet implementation class HouseDe
 */
@WebServlet("/HouseDe")
public class HouseDe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Integer my_id ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseDe() {
        super();
        my_id=-1;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdvertisementController mycon = new AdvertisementController();
		Advertisement myAd = new Advertisement();
		
		try {
			if(request.getParameter("adId") != null)
			{
				my_id = Integer.parseInt(request.getParameter("adId"));
			}
			System.out.println("my_id : "+my_id);
			myAd = mycon.getAdvertismentR(my_id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if( request.getParameter("newComment") != null )
		{
			String com = request.getParameter("newComment").toString();
			String userID=request.getSession().getAttribute("userID").toString();
			userID=request.getSession().getAttribute("userID").toString();
			Integer usId = Integer.parseInt(userID);
			Integer AdId = my_id;
			try {
				mycon.setcomment(AdId, usId, com);
				UserController cont=new UserController();
				User u=cont.getUser(usId);
				mycon.setNotifecation(myAd.getAdvertisementId(), myAd.getUserId(), u.getUserName()+" commented on your post called "+myAd.getName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(request.getParameter("adId") != null)
				{
					my_id = Integer.parseInt(request.getParameter("adId"));
				}
				System.out.println("my_id : "+my_id);
				myAd = mycon.getAdvertismentR(my_id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		ArrayList<String> usernames = new ArrayList<String>();
		ArrayList<String> ADComments = new ArrayList<String>();
		ArrayList<Comment> comments = new ArrayList<Comment>();
		ArrayList<Pair> MyId = new ArrayList<>();
		comments = myAd.getComments();
		System.out.println("comeents size =  " + myAd.getComments().size() );
		for(int i = 0 ; i < myAd.getComments().size() ; i++)
		{

			MyId.add( new Pair( "id" , comments.get(i).getUserId().toString()  ) );
			ADComments.add(comments.get(i).getComment());
			try {
				ResultSet ss =Crud.select("UserTable", MyId);
				String name="";
				while(ss.next()) {
					name = ss.getString("userName");
				}
				usernames.add(name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MyId.clear();
		}
		String [] stars = myAd.getRate().split("#");
		Integer [] radios = {0,0,0,0,0};
		String finalRate = "";
		String data = (String)request.getParameter("rating");
		if( data != null)
		{
			if(data.equals("1") ){ radios[0]= 1;}
			else if(data.equals("2") ){ radios[1]= 1;}
			else if(data.equals("3") ){ radios[2]= 1;}
			else if(data.equals("4") ){ radios[3]= 1;}
			else if(data.equals("5") ){ radios[4]= 1;}
		}
		if(radios[0]!= 1 || radios[1]!= 1 || radios[2]!=1 || radios[3]!=1 || radios[4]!=1)
		{
			int starInc = 0;
			for(int i = 0 ; i < 5 ; i++)
			{
				if(radios[i] == 1){
					starInc=i+1;
					break;
				}
			}
			if(stars.length == 0)
			{
				
				if(starInc == 1){ finalRate ="1#0#0#0#0";}
				else if(starInc == 2){ finalRate ="0#1#0#0#0";}
				else if(starInc == 3){ finalRate ="0#0#1#0#0";}
				else if(starInc == 4){ finalRate ="0#0#0#1#0";}
				else if(starInc == 5){ finalRate ="0#0#0#0#1";}
				
			}
			else
			{
				Integer [] starsWight = {0,0,0,0,0};
				for(int i = 0 ; i < 5 ; i++)
				{
					starsWight[i]=Integer.parseInt(stars[i]);
				}
				if(starInc == 1){ starsWight[0]++; }
				else if(starInc == 2){ starsWight[1]++;}
				else if(starInc == 3){ starsWight[2]++;}
				else if(starInc == 4){ starsWight[3]++;}
				else if(starInc == 5){ starsWight[4]++;}
				for(int i = 0 ; i < 5 ; i++)
				{
					finalRate += starsWight[i].toString()+"#";
				}
				finalRate = finalRate.substring(0, finalRate.length()-1);
				
			}
			try {
				mycon.updateRate(my_id, finalRate);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("House", myAd.getHouse());
		session.setAttribute("Comments", ADComments);
		session.setAttribute("CommentUserNames", usernames);
		session.setAttribute("UserName", myAd.getUserName());
		session.setAttribute("Rate", myAd.getRate());
		session.setAttribute("Advertisement", myAd);
		response.sendRedirect("HouseDetails.jsp");
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
