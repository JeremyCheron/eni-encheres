package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bll.error.ErrorManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.helpers.DAOHelper;
import fr.eni.encheres.dal.helpers.UserRowMapper;

public class UserDAO implements DAO<User> {

	private Connection cnx;
	private final DAOHelper<User> daoHelper;
	private ErrorManager errorManager;

	private static final String SELECT_BY_ID = "SELECT * FROM USERS WHERE user_id=?";
	private static final String SELECT_ALL = "SELECT * FROM USERS";
	private static final String COMPARE_USER_PASS = "SELECT * FROM USERS WHERE username=? AND password=?";
	private static final String SELECT_BY_NAME = "SELECT * FROM USERS WHERE username=?";

	public UserDAO(Connection _cnx) {
		this.cnx = _cnx;
		this.daoHelper = new DAOHelper<User>(new UserRowMapper());
        this.errorManager = new ErrorManager();
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
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();
			users = daoHelper.mapResults(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void insert(User user) throws DALException {

		try (PreparedStatement stmt = daoHelper.createInsertStatement(user, cnx)) {
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new DALException(errorManager.getErrorMessage("10000"), "10000");
			}

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setUserId(generatedKeys.getInt(1));
				} else {
					throw new DALException(errorManager.getErrorMessage("10001"), "10001");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10002"), "10002");
		}
	}

	@Override
	public void update(User user) throws DALException {

		try (PreparedStatement stmt = daoHelper.createUpdateStatement(user, cnx)) {
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10003"), "10003");
		}
	}

	@Override
	public void delete(User user) throws DALException {

		try (PreparedStatement stmt = daoHelper.deleteStatement(user, cnx)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10004"), "10004");
		}
	}

	public User validateLogin(String username, String password) throws DALException {

		try (PreparedStatement stmt = cnx.prepareStatement(COMPARE_USER_PASS)) {
			stmt.setString(1, username);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {

				return daoHelper.mapSingleResult(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getLogin(String username) {
		User user = null;
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NAME)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			user = daoHelper.mapSingleResult(rs);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	
	@Override
	public List<User> selectByCriteria(Map<String, Object> criteria) throws DALException {
		
		List<User> users = new ArrayList<>();

		String query = "SELECT * FROM users WHERE";
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
					users = daoHelper.mapResults(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
		
	}

}
