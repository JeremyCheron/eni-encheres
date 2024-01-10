package fr.eni.encheres.bll;

import java.util.List;
import java.util.Map;

import fr.eni.encheres.bll.error.ErrorManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.Withdrawal;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.BidDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;
import fr.eni.encheres.dal.WithdrawalDAO;

public class ArticleManager {

	private static ArticleManager instance;
	private DAO<Article> articleDAO;
	private ErrorManager errorManager;

	private ArticleManager() {
		this.articleDAO = new DAOFactory().getArticleDAO();
        this.errorManager = new ErrorManager();
	}

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}

	public void setArticleDAO(DAO<Article> articleDAO) {
		this.articleDAO = articleDAO;
	}

	public void createArticle(Article article, Withdrawal withdrawal) throws BLLException {
//		validateArticleData(article);

		try {
			articleDAO.insert(article);
			withdrawal.setWithdrawalId(article.getArticleId());
			WithdrawalDAO withdrawalDAO = (WithdrawalDAO) new DAOFactory().getWithdrawalDAO();
			withdrawalDAO.insert(withdrawal);
			
			
		} catch (DALException e) {
			throw new BLLException(errorManager.getErrorMessage("20102"), "20102");
		}
	}

	public List<Article> getArticles() throws BLLException {
		try {
	    	return articleDAO.selectAll();
	    } catch (DALException e) {
			throw new BLLException(errorManager.getErrorMessage("20100"), "20100");
		}

	}
	
	public List<Article> getUserArticles(int userID) throws BLLException {
		try {
			return articleDAO.selectAll().stream().filter(a -> a.getUserId()==userID).toList();
		} catch (DALException e) {
			throw new BLLException(errorManager.getErrorMessage("20101"), "20101");
		}
	}
	
	public void placeBid(int userId, int articleId, int bidAmount) throws BLLException {
		
		try {
			BidDAO bidDAO = (BidDAO) new DAOFactory().getBidDAO();
			Article article = this.getArticle(articleId);
			int finalPrice = article.getFinalPrice();
			//			Bid currentHighestBid = bidDAO.getHighestBid(articleId);
			
			//verif si nouvelle enchere > meilleur enchere
			if (bidAmount > finalPrice) {
				
				//ajouter enchere
				Bid newBid = new Bid(bidAmount, articleId, userId);
				bidDAO.insert(newBid);
				
				// ajuster crédits de l'encherisseur actuel
				UserManager userManager = UserManager.getInstance();
				int currentUserCredits = userManager.getUserCredits(userId);
				userManager.adjustUserCredits(userId, currentUserCredits - bidAmount);
				
				//Ajuster le prix de l'article
				article.setFinalPrice(bidAmount);
				this.updateArticle(article);
				
				//si une enchere existait, ajuster crédits de l'ancien meilleur encherisseur
//				if (currentHighestBid != null) {
//					int previousBidderId = currentHighestBid.getBider();
//					int previousBidAmount = currentHighestBid.getAmount();
//					
//					int previousBidderCredits = userManager.getUserCredits(previousBidderId);
//					userManager.adjustUserCredits(previousBidderId, previousBidderCredits + previousBidAmount);
//				}
				
			} else {
				throw new BLLException(errorManager.getErrorMessage("20300"), "20300");
			}
			
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(errorManager.getErrorMessage("20301"), "20301");
		}
		
	}
	

	private void updateArticle(Article article) throws DALException {
		articleDAO.update(article);
	}

	private void validateArticleData(Article article) throws BLLException {
		throw new BLLException(errorManager.getErrorMessage("20103"), "20103");
	}

	public List<Article> searchArticles(Map<String, Object> criteria) throws BLLException {
        try {
            return articleDAO.selectByCriteria(criteria);
        } catch (DALException e) {
    		throw new BLLException(errorManager.getErrorMessage("20104"), "20104");
        }
    }
	
	public String getArticleSellerName(Article article) throws BLLException {
		String sellerName = "";
		
		try {
			UserManager userManager = UserManager.getInstance();
			User seller = userManager.getUser(article.getUserId());
			
			if(seller != null) {
				sellerName = seller.getUsername();
			} else {
				throw new BLLException(sellerName, sellerName);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		return sellerName;
	}

	public Article getArticle(int articleId) throws BLLException{

		Article article = null;
		
		try {
			
			article = articleDAO.selectByID(articleId);
			
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return article;
	}

	public List<Article> getArticleByCategory(int categoryId) throws DALException {
			ArticleDAO articleByCategoryDAO = (ArticleDAO) new DAOFactory().getArticleDAO();
			return articleByCategoryDAO.selectByCategory(categoryId);
	}

}
