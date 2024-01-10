package fr.eni.encheres.ihm.servlets.sales;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.utils.Nav;

@WebServlet("/sales/bid")
public class SaleBid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Nav.loginIfCookieFound(request);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		if (session.getAttribute("logged") != null) {
			
			String articleIdParam = request.getParameter("articleId");
			String bidAdmountParam = request.getParameter("bidAmount");
			
			if (articleIdParam != null && !articleIdParam.isEmpty()) {
				int userId = Integer.valueOf(session.getAttribute("userId").toString());
				int articleId = Integer.parseInt(articleIdParam);
				int bidamount = Integer.parseInt(bidAdmountParam);
				bidOnArticle(request, response, userId, articleId, bidamount);
			}
			
		} else {
			Nav.forwardToHome(request, response);
		}
	
	}

	private void bidOnArticle(HttpServletRequest request, HttpServletResponse response, int userId, int articleId, int bidAdmount) throws ServletException, IOException{

		try {
			ArticleManager articleManager = ArticleManager.getInstance();
			
			Article article = articleManager.getArticle(articleId);
			articleManager.placeBid(userId, articleId, bidAdmount);
			
			
		} catch (BLLException e) {
			// TODO: handle exception
		}
		
	}

}
