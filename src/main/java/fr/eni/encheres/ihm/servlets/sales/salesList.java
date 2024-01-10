package fr.eni.encheres.ihm.servlets.sales;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.DALException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/sales/list")
public class salesList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession(false);
			CategoryManager categoryManager = CategoryManager.getInstance();
			request.setAttribute("categories", categoryManager.getCategories());
			
			ArticleManager articleManager = ArticleManager.getInstance();
			
			String searchFilter = request.getParameter("filter");
			String searchCategory = request.getParameter("category");

			
			Map<String, Object> searchCriteria = null;
			List<Article> searchResults = null;
			List<Article> allSales = null;
			
			if (searchFilter != null && !searchFilter.trim().isEmpty()) {
			    searchCriteria = new HashMap<>();
				searchCriteria.put("article_name", "%" + searchFilter + "%");
				searchResults = articleManager.searchArticles(searchCriteria);
				for (Article article : searchResults) {
					String sellerName = articleManager.getArticleSellerName(article);
					article.setSellerName(sellerName);
				}
				request.setAttribute("searchResults", searchResults);

			} else if (searchCategory != null && !searchCategory.trim().isEmpty()){
				int categoryId = Integer.parseInt(searchCategory);
				try {
					searchResults = articleManager.getArticleByCategory(categoryId);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Article article : searchResults) {
					String sellerName = articleManager.getArticleSellerName(article);
					article.setSellerName(sellerName);
				}
				request.setAttribute("searchResults", searchResults);
			}
			
			else {
				allSales = articleManager.getArticles();
				for (Article article : allSales) {
					String sellerName = articleManager.getArticleSellerName(article);
					article.setSellerName(sellerName);
				}
				request.setAttribute("allSales", allSales);

			}
			
			
			
			
			
			
			if (session != null && session.getAttribute("username") != null) {
//	            String username = Objects.toString(session.getAttribute("username"), "");
	            request.setAttribute("mySales", articleManager.getUserArticles(Integer.valueOf(session.getAttribute("userId").toString())));
	        }
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sale/salesList.jsp");
			rd.forward(request, response);
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
