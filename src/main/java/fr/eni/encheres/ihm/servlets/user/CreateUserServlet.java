package fr.eni.encheres.ihm.servlets.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/user/create-account")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		} catch (BLLException e) {
			// TODO: handle exception
		}
		
	}

}
