package fr.eni.encheres.ihm.servlets.sales;

import java.io.IOException;
import java.time.LocalDate;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.DateConversionUtil;
import fr.eni.encheres.bo.Article;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/newSale")
public class NewSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher newSale = request.getRequestDispatcher("/WEB-INF/newSale.jsp");
		newSale.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("article");
		String description = request.getParameter("champ_description");
		LocalDate startDate = DateConversionUtil.convertInputDateToLocalDate(request.getParameter("startDate"));
		LocalDate endDate = DateConversionUtil.convertInputDateToLocalDate(request.getParameter("endDate"));
		int startPrice = Integer.parseInt(request.getParameter("price"));
		int finalPrice = Integer.parseInt(request.getParameter("price"));
		int userId = 1;
		int categoryId = 1;

		Article newArticle = new Article(name, description, startDate, endDate, startPrice, finalPrice, userId,
				categoryId);
		try {
			ArticleManager articleManager = ArticleManager.getInstance();
			articleManager.createArticle(newArticle);
		} catch (BLLException e) {
			// TODO: handle exception
		}

		RequestDispatcher validSale = request.getRequestDispatcher("/WEB-INF/saleCreatedSuccesfully.jsp");
		validSale.forward(request, response);
	}
}
