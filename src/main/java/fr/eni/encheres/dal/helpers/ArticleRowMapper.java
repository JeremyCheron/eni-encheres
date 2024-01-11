package fr.eni.encheres.dal.helpers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import fr.eni.encheres.bo.Article;

public class ArticleRowMapper implements RowMapper<Article> {

	@Override
	public Article map(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("article_id");
		String name = rs.getString("article_name");
		String description = rs.getString("description");
		LocalDate startDate = rs.getDate("start_auction_date").toLocalDate();
		LocalDate endDate = rs.getDate("end_auction_date").toLocalDate();
		int startPrice = rs.getInt("initial_price");
		int finalPrice = rs.getInt("final_price");
		int userId = rs.getInt("user_id");
		int category = rs.getInt("category_id");
				
		return new Article(id, name, description, startDate, endDate, startPrice, finalPrice, userId, category);
	}

	@Override
	public PreparedStatement createInsertStatement(Article article, Connection cnx) throws SQLException {
		
		String INSERT = "INSERT INTO ARTICLES(article_name, description, start_auction_date, end_auction_date, initial_price, final_price, user_id, category_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = cnx.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, article.getName());
		stmt.setString(2, article.getDescription());
		stmt.setDate(3, Date.valueOf(article.getStartDate()));
		stmt.setDate(4, Date.valueOf(article.getEndDate()));
		stmt.setInt(5, article.getStartPrice());
		stmt.setInt(6, article.getFinalPrice());
		stmt.setInt(7, article.getUserId());
		stmt.setInt(8, article.getCategoryId());
		
		return stmt;
	}

	@Override
	public PreparedStatement createUpdateStatement(Article article, Connection cnx) throws SQLException {
		
		String UPDATE = "UPDATE ARTICLES SET article_name=?, description=?, start_auction_date=?, end_auction_date=?, initial_price=?, final_price=?, user_id=?, category_id=? WHERE article_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(UPDATE);
		stmt.setString(1, article.getName());
		stmt.setString(2, article.getDescription());
		stmt.setDate(3, Date.valueOf(article.getStartDate()));
		stmt.setDate(4, Date.valueOf(article.getEndDate()));
		stmt.setInt(5, article.getStartPrice());
		stmt.setInt(6, article.getFinalPrice());
		stmt.setInt(7, article.getUserId());
		stmt.setInt(8, article.getCategoryId());
		stmt.setInt(9, article.getArticleId());

		
		return stmt;
	}

	@Override
	public PreparedStatement deleteStatement(Article article, Connection cnx) throws SQLException {
		
		String DELETE = "DELETE FROM ARTICLES WHERE article_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(DELETE);
		
		stmt.setInt(1, article.getArticleId());

		return stmt;
	}
	
	
	
}