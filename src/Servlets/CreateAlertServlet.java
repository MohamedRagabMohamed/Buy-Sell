package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.AlertController;

/**
 * Servlet implementation class CreateAlertServlet
 */
@WebServlet("/CreateAlertServlet")
public class CreateAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AlertController controller;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAlertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String status=request.getParameter("status");
		String type=request.getParameter("type");
		String size=request.getParameter("size");
		System.out.println("type   : "+type);
		try {
			AlertController.addAlert(status, type, size,request);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		response.sendRedirect("Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
