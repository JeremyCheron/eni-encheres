package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;

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
	 public DAO<Bid> getAuctionsDAO() {
		 return new BidDAO(cnx);
	 }
}
