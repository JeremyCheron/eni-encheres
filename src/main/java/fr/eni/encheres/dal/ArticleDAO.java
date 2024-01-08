package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bll.error.ErrorManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.helpers.ArticleRowMapper;
import fr.eni.encheres.dal.helpers.DAOHelper;

public class ArticleDAO implements DAO<Article>{
	
	private Connection cnx;
	private final DAOHelper<Article> daoHelper;
	private ErrorManager errorManager;
	
	private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES WHERE article_id=?";
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES";
	
	public ArticleDAO(Connection _cnx) {
		this.cnx = _cnx;
		this.daoHelper = new DAOHelper<>(new ArticleRowMapper());
        this.errorManager = new ErrorManager();

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
				throw new DALException(errorManager.getErrorMessage("10100"), "10100");
			}
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					article.setArticleId(generatedKeys.getInt(1));
				} else {
					throw new DALException(errorManager.getErrorMessage("10101"), "10101");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10102"), "10102");
		}
	}
	
		
	@Override
	public void update(Article article) throws DALException {
		
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(article, cnx))
		{ 
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10103"), "10103");
		}
		
	}

	@Override
	public void delete(Article article) throws DALException {
		
		try(PreparedStatement stmt = daoHelper.deleteStatement(article, cnx))
		{
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10104"), "10104");
		}
	
		
	}
	
	@Override
	public List<Article> selectByCriteria(Map<String, Object> criteria) throws DALException {
		
		List<Article> articles = new ArrayList<>();

		String query = "SELECT * FROM articles WHERE";
		for (String field : criteria.keySet()) {
			query += " " + field + " = ? AND";
		}
		query = query.substring(0, query.length() - 4);
		
		try(PreparedStatement stmt = cnx.prepareStatement(query)){
			int parameterIndex = 1;
			for (Object value : criteria.values()) {
				stmt.setObject(parameterIndex++, value);
			}
			
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					articles = daoHelper.mapResults(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articles;
		
	}

}
