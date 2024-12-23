package fr.eni.encheres.ihm.servlets.sales;

import java.io.IOException;
import java.time.LocalDate;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.Withdrawal;
import fr.eni.encheres.utils.DateConversionUtil;
import fr.eni.encheres.utils.Nav;
//import fr.eni.encheres.bo.ImageFile;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/newSale")
public class CreateSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Nav.loginIfCookieFound(request);
		
		try {
			CategoryManager categoryManager = CategoryManager.getInstance();
			request.setAttribute("categories", categoryManager.getCategories());
			
		} catch (BLLException e) {
			System.err.println("failed retrieve of categories list ");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sale/createNewSale.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		String name = request.getParameter("article");
		String description = request.getParameter("champ_description");
		String startDateSTR = request.getParameter("startDate");
		String endDateSTR = request.getParameter("endDate");
		
		
		LocalDate startDate = DateConversionUtil.convertInputDateToLocalDate(startDateSTR);
		LocalDate endDate = DateConversionUtil.convertInputDateToLocalDate(endDateSTR);
		int startPrice = Integer.parseInt(request.getParameter("price"));
		int finalPrice = Integer.parseInt(request.getParameter("price"));
		int userId = Integer.valueOf(session.getAttribute("userId").toString());
		int categoryId = Integer.parseInt(request.getParameter("category"));

		Article newArticle = new Article(name, description, startDate, endDate, startPrice, finalPrice, userId,
				categoryId);
		
		// Part photoPart = request.getPart("photo");
		//System.out.println(photoPart.getContentType()); //.getBytes();
//		byte[] data = null; // TODO 
		
		      String newAdress = request.getParameter("defaultAddress");
		      Withdrawal withdrawal = null;
		      
		      if (newAdress != null && newAdress.equals("newAdress")) {
		    	  String street = request.getParameter("street");
		    	  int postalCode = Integer.parseInt(request.getParameter("postalCode"));
		    	  String city = request.getParameter("city");
		    	  
		    	  withdrawal = new Withdrawal(street, postalCode, city);
		      
		      } else {
		    	
		    	  UserManager userManager = UserManager.getInstance();
		    	  try {
					User user = userManager.getUser(userId);
					String street = user.getStreet();
					int postalCode = user.getPostCode();
					String city = user.getCity();
					
					withdrawal = new Withdrawal(street, postalCode, city);
					
				} catch (BLLException e) {
					e.printStackTrace();
				}
		    	  
		      }
		
		try {
			ArticleManager articleManager = ArticleManager.getInstance();
			articleManager.createArticle(newArticle, withdrawal);
			
			
//			ImageFile newImage = new ImageFile(data, "img_"+newArticle.getName(), newArticle.getArticleId());
//			ImageFileManager imageFileManager = ImageFileManager.getInstance();
//			imageFileManager.createImageFile(newImage);
//			
		} catch (BLLException e) {
			// TODO: handle exception
		}
		
	    request.setAttribute("newSale", "Your sale has been created successfully");


		RequestDispatcher validSale = request.getRequestDispatcher("/sales/detail?articleId=" + newArticle.getArticleId());
		validSale.forward(request, response);
	}
}