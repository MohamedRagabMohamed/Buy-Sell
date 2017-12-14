package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.AdvertisementController;
import Controller.HouseController;
import Modules.Advertisement;
import Modules.House;

/**
 * Servlet implementation class UpdateAdvertisementServlet
 */
@WebServlet("/UpdateAdvertisementServlet")
public class UpdateAdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdvertisementController advController = new AdvertisementController();
	public HouseController houseController = new HouseController();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdvertisementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String advName = request.getParameter("advertisementName");
		String advType = request.getParameter("advertisementType");
		String size = request.getParameter("size");
		String floor = request.getParameter("floor");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String houseType = request.getParameter("houseType");
		String images = request.getParameter("images");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");

		House returnHouse = new House();
		returnHouse.setSize(size);
		returnHouse.setFloor(floor);
		returnHouse.setDescription(description);
		returnHouse.setStatus(status);
		returnHouse.setType(houseType);
		returnHouse.setImages(images);
		returnHouse.setLatitude(latitude);
		returnHouse.setLongitude(longitude);
		
		try {
			houseController.updateHouse(returnHouse);
		} catch (ClassNotFoundException | SQLException e2) {
		}
		
		
		// law 8ayrt el long w el lang bedrb
		String houseID = null;
		try {
			houseID = houseController.getHouseId(longitude, latitude);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(houseID);
		Advertisement advertisement = new Advertisement();
		advertisement.setName(advName);
		advertisement.setType(advType);
		advertisement.setHouseId(Integer.parseInt(houseID));

		try {
			advController.updateAdvertisement(advertisement);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		response.sendRedirect("/Profile?id="+userId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
