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

	private static final String SELECT_BY_ID = "SELECT * FROM dbo.USERS WHERE user_id=?";
	private static final String SELECT_ALL = "SELECT * FROM dbo.USERS";
	private static final String COMPARE_USER_PASS = "SELECT * FROM USERS WHERE username=? AND password=?";

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
				throw new DALException("User : Insertion Failed, no rows affected.");
			}

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setUserId(generatedKeys.getInt(1));
				} else {
					throw new DALException("Insertion Failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("User : Error during insertion.");
		}
	}

	@Override
	public void update(User user) throws DALException {

		try (PreparedStatement stmt = daoHelper.createUpdateStatement(user, cnx)) {
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("User : Update Fail");
		}
	}

	@Override
	public void delete(User user) throws DALException {

		try (PreparedStatement stmt = daoHelper.deleteStatement(user, cnx)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("User : Error during deleting.");
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
}
