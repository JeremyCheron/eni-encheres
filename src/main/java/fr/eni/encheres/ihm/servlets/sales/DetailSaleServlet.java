package fr.eni.encheres.ihm.servlets.sales;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.utils.Nav;

@WebServlet("/sales/detail")
public class DetailSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Nav.loginIfCookieFound(request);
		HttpSession session = request.getSession();
		
		if (session.getAttribute("logged") != null) {
			String articleIdParam = request.getParameter("articleId");
			int userId = Integer.valueOf(session.getAttribute("userId").toString());
			if (articleIdParam != null && !articleIdParam.isEmpty()) {
				
				int articleId = Integer.parseInt(articleIdParam);
				request.setAttribute("myId", userId);
				handleArticleDetailRequest(request, response, articleId);
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
	
	private void handleArticleDetailRequest(HttpServletRequest request, HttpServletResponse response, int articleId) throws ServletException, IOException {
		
		try {
			ArticleManager articleManager = ArticleManager.getInstance();
			
			Article searchedArticle= articleManager.getArticle(articleId);
			searchedArticle.setSellerName(articleManager.getArticleSellerName(searchedArticle));
			
			request.setAttribute("searchedArticle", searchedArticle);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sale/saleDetail.jsp");
			rd.forward(request, response);
			
		} catch (BLLException e) {
			e.printStackTrace();
			Nav.forwardToHome(request, response);
		}
		
	}

}
