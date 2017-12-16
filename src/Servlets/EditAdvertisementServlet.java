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
@WebServlet("/EditAdvertisementServlet")
public class EditAdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdvertisementController advController = new AdvertisementController();
	public HouseController houseController = new HouseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditAdvertisementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.getWriter().append("Served at: ").append(request.getContextPath());

		Integer advId = Integer.parseInt(request.getParameter("id"));
		Advertisement advertisement = advController.getAdvetisement(advId);
		House house = houseController.getHouse(advertisement.getHouseId());
		
		request.setAttribute("advertisement", advertisement);
		request.setAttribute("house", house);
		request.getRequestDispatcher("EditAdvertisement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
