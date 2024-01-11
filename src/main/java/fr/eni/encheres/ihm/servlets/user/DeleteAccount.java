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
import fr.eni.encheres.bo.User;

@WebServlet("/user/delete-account")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("logged") != null) {
			
			User deleteUser = null;
			int userId = Integer.valueOf(session.getAttribute("userId").toString());
			UserManager userManager = UserManager.getInstance();
			
			try {
				deleteUser = userManager.getUser(userId);
				
				userManager.removeUser(deleteUser);
				
				RequestDispatcher rd = request.getRequestDispatcher("/user/logout");
				rd.forward(request, response);
				
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
				
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
