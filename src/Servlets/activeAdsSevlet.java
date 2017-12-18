package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Controller.AdvertisementController;
import Modules.Advertisement;
 




/**
 * Servlet implementation class activeAdsSevlet
 */
@WebServlet("/activeAdsSevlet")
public class activeAdsSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public activeAdsSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdvertisementController adControl = new AdvertisementController();
		ArrayList<Advertisement> activeAds = new ArrayList<Advertisement>();
		
		try {
			activeAds = adControl.getAciveAds();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0 ; i < activeAds.size() ; i++)
		{
			Advertisement currAd = activeAds.get(i);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("Name :: " + currAd.getName());
			System.out.println("Status :: " + currAd.getActive());
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		}
		System.out.println("activeSize :: " + activeAds.size());
		
//		PrintWriter out = response.getWriter();
//		out.print(activeAds);
//        out.close();
		
		response.setContentType("application/json;charset=UTF-8");
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//String json = gson.toJson(activeAds);
		
		
		

        PrintWriter out = response.getWriter();
        //out.print(json);
            

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
