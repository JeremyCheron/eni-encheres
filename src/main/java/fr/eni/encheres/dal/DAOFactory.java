package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Auction;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.Withdrawal;

public class DAOFactory {
	
	private Connection cnx;
	
	public DAOFactory() {
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 public DAO<Article> getArticleDAO() {
		return new ArticleDAO(cnx);
	}
//	 public DAO<User> getUserDAO() {
//		 return new UserDAO();
//	 }
//	 public DAO<Auction> getAuctionsDAO() {
//		 return new AuctionsDAO();
//	 }
	 
	 public DAO<Category> getCategoryDao() {
		 return new CategoryDAO(cnx);
	 }
	 
	 public DAO<Withdrawal> getWithdrawalDao() {
		 return new WithdrawalDAO(cnx);
	 }
}
