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
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/user/create-account")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Nav.loginIfCookieFound(request);
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("logged") != null) {
			
			User editUser = null;
			int userId = Integer.valueOf(session.getAttribute("userId").toString());
			UserManager userManager = UserManager.getInstance();
			
				try {
					
					editUser = userManager.getUser(userId);
					
				} catch (BLLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("editUser", editUser);
				
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/createProfile.jsp");
		rd.forward(request, response);
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String phone = request.getParameter("phone");
		int postcode = Integer.parseInt(request.getParameter("postcode"));
		String password = request.getParameter("password");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		
		User newUser = new User(username, firstname, lastname, email, phone, street, postcode, city, password, 100);
		newUser.setAdmin(false);
		
		try {
			UserManager userManager = UserManager.getInstance();
			userManager.createUser(newUser);
			
			if (userManager.login(username, password) != null) {
			    HttpSession session = request.getSession();
			    session.setAttribute("username", username);
			    session.setAttribute("logged", true);
			    session.setAttribute("userId", newUser.getUserId());

			    RequestDispatcher rd = request.getRequestDispatcher("/Home");
			    rd.forward(request, response);
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
