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
import Controller.AdvertisementController;
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
		if( request.getParameter("newComment") != null )
		{
			String com = request.getParameter("newComment").toString();
			String userID=request.getSession().getAttribute("userID").toString();
			userID=request.getSession().getAttribute("userID").toString();
			Integer usId = Integer.parseInt(userID);
			Integer AdId = Integer.parseInt(request.getParameter("adId")) ;
			try {
				mycon.setcomment(AdId, usId, com);
				mycon.setNotifecation(myAd.getHouseId(), usId, myAd.getUserName()+" commented on your post called "+myAd.getName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//System.out.println(11);
		System.out.println("Name: " + myAd.getName());
		System.out.println("rate "+myAd.getRate());
		String [] stars = myAd.getRate().split("#");
		String [] radios = {"","","","",""};
		String finalRate = "";
		if( request.getParameter("star1") != null)
		{
			radios[0] = request.getParameter("star1");
			radios[1] = request.getParameter("star2");
			radios[2] = request.getParameter("star3");
			radios[3] = request.getParameter("star4");
			radios[4] = request.getParameter("star5");
		}
		if(radios[0]!="" && radios[1]!="" && radios[2]!="" && radios[3]!="" && radios[4]!="")
		{
			int starInc= this.getStarInc(radios);
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
				Integer [] starsWight = null;
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
		
		request.setAttribute("House", myAd.getHouse());
		request.setAttribute("Comments", ADComments);
		request.setAttribute("CommentUserNames", usernames);
		request.setAttribute("UserName", myAd.getUserName());
		request.setAttribute("Rate", myAd.getRate());
		request.setAttribute("Advertisement", myAd);
		request.getRequestDispatcher("HouseDetails.jsp").forward(request, response);
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public Integer getStarInc(String [] stars)
	{
		int i = 0 ;
		if(stars[4].equals("5")){i = 5 ;}
		else if(stars[4].equals("4")){i = 4 ;}
		else if(stars[4].equals("3")){i = 3 ;}
		else if(stars[4].equals("2")){i = 2 ;}
		else if(stars[4].equals("1")){i = 1 ;}
		return i ;
	}
}
