package fr.eni.encheres.ihm.servlets.sales;

import java.io.IOException;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategoryManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/mySales")
public class MySalesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryManager categoryManager = CategoryManager.getInstance();
			request.setAttribute("categories", categoryManager.getCategories());
			
			ArticleManager articleManager = ArticleManager.getInstance();
			request.setAttribute("searchResults", articleManager.getUserArticles(5));
			
		} catch (BLLException e) {
			System.err.println("failed retrieve of categories list ");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sale/mySalesList.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}