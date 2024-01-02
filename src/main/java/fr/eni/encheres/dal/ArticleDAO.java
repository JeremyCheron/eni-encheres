package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.encheres.bo.Article;

public class ArticleDAO implements DAO<Article>{
	
	private Connection cnx;
	
	private static final String SELECT_BY_ID = "SELECT * FROM dbo.ARTICLES WHERE no_article=?";
	private static final String SELECT_ALL = "SELECT * FROM dbo.ARTICLES";
	private static final String DELETE = "DELETE FROM dbo.ARTICLES WHERE no_article=?";
	private static final String INSERT = "INSERT INTO dbo.ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE dbo.ARTICLES SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? WHERE no_article=?";
	
	public ArticleDAO(Connection _cnx) {
		this.cnx = _cnx;
	}
	

	@Override
	public Article selectByID(int id) {
		Article article = null;
		try(PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				article = new Article();
				
			}
		}
		return article;
	}

	@Override
	public List<Article> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Article Object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Article Object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Article Object) {
		// TODO Auto-generated method stub
		
	}

}
