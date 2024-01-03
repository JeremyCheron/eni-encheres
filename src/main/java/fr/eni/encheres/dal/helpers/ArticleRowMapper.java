package fr.eni.encheres.dal.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	

}
