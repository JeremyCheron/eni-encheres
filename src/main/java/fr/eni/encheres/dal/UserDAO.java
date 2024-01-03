package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.helpers.DAOHelper;
import fr.eni.encheres.dal.helpers.UserRowMapper;

public class UserDAO implements DAO<User> {
	
	private Connection cnx;
	private final DAOHelper<User> daoHelper;
	
	private static final String SELECT_BY_ID = "SELECT * FROM dbo.USER WHERE user_id=?";
	private static final String SELECT_ALL = "SELECT * FROM dbo.USERS";
	
	public UserDAO(Connection _cnx) {
		this.cnx = _cnx;
		this.daoHelper = new DAOHelper<User>(new UserRowMapper());
	}

	@Override
	public User selectByID(int id) {
		User user = null;
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			user = daoHelper.mapSingleResult(rs);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> selectAll() {
		List<User> users = new ArrayList<User>();
		try(PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();
			users = daoHelper.mapResults(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void insert(User user) throws Exception {
		
		try (PreparedStatement stmt = daoHelper.createInsertStatement(user, cnx)) 
		{
			int affectedRows = stmt.executeUpdate();
			 
			if(affectedRows ==0) {
				throw new Exception("User : Insertion Failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					user.setUserId(generatedKeys.getInt(1));
				} else {
					throw new Exception("Insertion Failed, no ID obtained.");
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("User : Error during insertion.");
		}
	  
	}
	

	@Override
	public void update(User user) throws Exception {
		
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(user, cnx)) 
		{
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("User : Update Fail");
		}

	}

	@Override
	public void delete(User user) throws Exception {
		 
		try(PreparedStatement stmt = daoHelper.deleteStatement(user , cnx))
		{
			stmt.executeUpdate();
		} catch ( SQLException e) {
			e.printStackTrace();
			throw new Exception("User : Error during deleting.");
		}
	}
	


}
