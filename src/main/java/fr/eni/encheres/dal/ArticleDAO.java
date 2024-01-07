package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.helpers.ArticleRowMapper;
import fr.eni.encheres.dal.helpers.DAOHelper;

public class ArticleDAO implements DAO<Article>{
	
	private Connection cnx;
	private final DAOHelper<Article> daoHelper;
	
	private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES WHERE article_id=?";
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES";
	
	public ArticleDAO(Connection _cnx) {
		this.cnx = _cnx;
		this.daoHelper = new DAOHelper<>(new ArticleRowMapper());
	}
	

	@Override
	public Article selectByID(int id) throws DALException {
		Article article = null;
		try(PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID))
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			article = daoHelper.mapSingleResult(rs);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public List<Article> selectAll() {
		List<Article> articles = new ArrayList<>();
		try(PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();
			articles = daoHelper.mapResults(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public void insert(Article article) throws DALException {

		try (PreparedStatement stmt = daoHelper.createInsertStatement(article, cnx)) 
		{
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows == 0) {
				throw new DALException("Article : Insertion Failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					article.setArticleId(generatedKeys.getInt(1));
				} else {
					throw new DALException("Article : Insertion Failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Article : Error during insertion.");
		}
	}
	
		
	@Override
	public void update(Article article) throws DALException {
		
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(article, cnx))
		{ 
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Article : Update Fail");
		}
		
	}

	@Override
	public void delete(Article article) throws DALException {
		
		try(PreparedStatement stmt = daoHelper.deleteStatement(article, cnx))
		{
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Article : Error during deleting.");
		}
	
		
	}

}
