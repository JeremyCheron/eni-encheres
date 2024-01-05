package fr.eni.encheres.ihm.servlets.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/user/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");	
		String password = request.getParameter("password");
		
		UserManager userManager = UserManager.getInstance();
		
		try {
			
			if (userManager.login(username, password)) {
			    HttpSession session = request.getSession();
			    session.setAttribute("username", username);
			    session.setAttribute("logged", true);

			    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/testLogin.jsp");
			    rd.forward(request, response);
			} else {
			    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			    request.setAttribute("loginError", "Invalid username or password. Please try again.");
			    rd.forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}

}
