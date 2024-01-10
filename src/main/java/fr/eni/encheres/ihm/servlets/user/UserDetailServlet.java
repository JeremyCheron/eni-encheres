package fr.eni.encheres.ihm.servlets.user;

import java.io.IOException;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.utils.Nav;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/detail")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On récupère le paramètre en requete GET (dans l'url)
		HttpSession session = request.getSession();
		
		if (session.getAttribute("logged") != null) {
			String userIdParam = request.getParameter("userId");

			//Test de si il existe et n'est pas vide 
			if(userIdParam != null && !userIdParam.isEmpty()) {
			
				int userId = Integer.parseInt(userIdParam);
				handleUserDetailRequest(request, response, userId);
			
			} else {
				Nav.forwardToHome(request, response);
			}
			
		} else {
			Nav.forwardToHome(request, response);
		}
		
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private void handleUserDetailRequest(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
		
		try {
			UserManager userManager = UserManager.getInstance();
			
			User searchedUser = userManager.getUser(userId);
			
			request.setAttribute("searchedUser", searchedUser);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/userDetail.jsp");
			rd.forward(request, response);
			
		} catch (BLLException e) {
			e.printStackTrace();
			Nav.forwardToHome(request, response);
		}
		
	}

}
