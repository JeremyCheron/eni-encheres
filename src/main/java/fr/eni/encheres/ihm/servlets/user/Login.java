package fr.eni.encheres.ihm.servlets.user;

import java.io.IOException;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.utils.Nav;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/user/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/loginForm.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");	
		String password = request.getParameter("password");
		
		UserManager userManager = UserManager.getInstance();
		
		try {
			User loggedUser = userManager.login(username, password);
			System.out.println(loggedUser);
			if ( loggedUser != null) {
			    HttpSession session = request.getSession();
			    session.setAttribute("userId", loggedUser.getUserId());
			    session.setAttribute("username", username);
			    session.setAttribute("logged", true);

			    response.sendRedirect(request.getContextPath() + "/");

			} else {
			    request.setAttribute("loginError", "Invalid username or password. Please try again.");
				Nav.forwardToHome(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}

}
