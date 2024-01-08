package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Category;
import fr.eni.encheres.dal.helpers.CategoryRowMapper;
import fr.eni.encheres.dal.helpers.DAOHelper;

public class CategoryDAO implements DAO<Category>{

	private static final String SELECT_BY_ID = "SELECT * FROM categories WHERE category_id=?";
	private static final String SELECT_ALL = "SELECT * FROM categories";

	private Connection cnx;
	private final DAOHelper<Category> daoHelper;
	
	public CategoryDAO(Connection cnx) {
		this.cnx = cnx;
		this.daoHelper = new DAOHelper<>(new CategoryRowMapper());
	}
	
	@Override
	public Category selectByID(int id) throws DALException{
		Category category = null;
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)) 
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			category = daoHelper.mapSingleResult(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	@Override
	public List<Category> selectAll() throws DALException{
		List<Category> categories = new ArrayList<>();
		try(PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL))
		{
			
			ResultSet rs = stmt.executeQuery();
			categories = daoHelper.mapResults(rs);
		} catch (Exception e) {
			System.err.println("failed to retrieve categories List");		
			throw new DALException("failed to retrieve categories List");
		}
		return categories;
	}
//	@Override
//	public void insert(Category category) throws DALException {
//
//		try (PreparedStatement stmt = daoHelper.createInsertStatement(category, cnx)) 
//		{
//			int affectedRows = stmt.executeUpdate();
//			
//			if(affectedRows == 0) {
//				throw new DALException("Category : Insertion Failed, no rows affected.");
//			}
//			
//			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
//				if(generatedKeys.next()) {
//					category.setId(affectedRows);
//				} else {
//					throw new DALException("Category : Insertion Failed, no ID obtained.");
//				}
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DALException("Category : Error during insertion.");
//		}
//	}
	
	@Override
	public void insert(Category category) throws DALException {
		
		throw new DALException("You should not insert categories !"); 
		
	}

	@Override
	public void update(Category category) throws DALException {
		
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(category, cnx))
		{ 
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Category : Update Fail");
		}
		
	}

	@Override
	public void delete(Category category) throws DALException {
		
		try(PreparedStatement stmt = daoHelper.deleteStatement(category, cnx))
		{
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Category : Error during deleting.");
		}
	
		
	}
	
}
