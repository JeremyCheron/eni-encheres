package fr.eni.encheres.dal.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.encheres.bo.Article;

public class ArticleRowMapper implements RowMapper<Article> {

	@Override
	public Article map(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("no_article");
		String name = rs.getString("nom_article");
		String description = rs.getString("description");
		LocalDate startDate = rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate endDate = rs.getDate("date_fin_encheres").toLocalDate();
		double startPrice = rs.getDouble("");
		double finalPrice = rs.getDouble("");
		int status = rs.getInt("");
		int userId = rs.getInt("no_utilisateur");
		
		
		
		
		return new Article(id, name, description, startDate, endDate, startPrice, finalPrice, status, userId);
	}
	
	

}
