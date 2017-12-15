package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controller.*;
import Modules.*;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserController userController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		userController = new UserController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println("ID = " + id);
		User user = userController.getUser(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("Profile/ViewProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("firstName"));
		User returnUser = new User(Integer.parseInt(request.getParameter("id")),
									request.getParameter("userName"),
									request.getParameter("email"),
									request.getParameter("password"),
									request.getParameter("role"),
									request.getParameter("firstName"),
									request.getParameter("lastName"),
									request.getParameter("profilePicture"),
									request.getParameter("address"),
									request.getParameter("phoneNumber"));
	
		try {
			userController.UpdateUser(returnUser);
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		response.sendRedirect("/IA-Project/Profile?id=" + request.getParameter("id"));
	}

}
