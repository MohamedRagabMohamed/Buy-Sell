package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.LoginController;
import Modules.Pair;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginController loginController = new LoginController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("ma7moud");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(userName+" "+password);
		try {
			Pair loginData = loginController.checkUser(userName,password);
			System.out.println("uID"+loginData.first);
			if(!loginData.first.equals("-1")) {
				HttpSession S = request.getSession(true);
				S.setAttribute("userID", loginData.first);
				if(loginData.second != null && loginData.second.equals("admin")) {
					S.setAttribute("role", loginData.second);
					response.sendRedirect("http://localhost:8080/IA-Project/adminAdsPanel.jsp");
				}else {
					response.sendRedirect("http://localhost:8080/IA-Project/Home");
				}
				
			}else {
				response.sendRedirect("http://localhost:8080/IA-Project/wrong.html");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB ERROR");
			e.printStackTrace();
		}
	}

}
