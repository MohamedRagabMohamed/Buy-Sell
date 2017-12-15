package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.AdvertisementController;
import Controller.HouseController;
import Modules.Advertisement;
import Modules.House;

/**
 * Servlet implementation class CreateAdvertisementServlet
 */
@WebServlet("/CreateAdvertisementServlet")
public class CreateAdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdvertisementController advController = new AdvertisementController();
	public HouseController houseController = new HouseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAdvertisementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String advName = request.getParameter("advertisementName");
		String advType = request.getParameter("advertisementType");
		String size = request.getParameter("size");
		String floor = request.getParameter("floor");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String houseType = request.getParameter("houseType");
		String images = request.getParameter("images");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		House house = new House();
		house.setSize(size);
		house.setFloor(floor);
		house.setDescription(description);
		house.setStatus(status);
		house.setType(houseType);
		house.setImages(images);
		house.setLatitude(latitude);
		house.setLongitude(longitude);		
		
		Advertisement advertisement = new Advertisement();
		advertisement.setName(advName);
		advertisement.setType(advType);
		advertisement.setRate("0");
		advertisement.setActive(true);
		try {
			houseController.addHouse(house);
			advController.addAdvertiesement(advertisement,longitude,latitude,request);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DATA-BASE ERROR");
			e.printStackTrace();
		}
		HttpSession s = request.getSession();
		response.sendRedirect("/IA-Project/Profile?id="+s.getAttribute("userID"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
