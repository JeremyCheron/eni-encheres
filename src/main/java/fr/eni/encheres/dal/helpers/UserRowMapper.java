package fr.eni.encheres.dal.helpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.eni.encheres.bo.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User map(ResultSet rs) throws SQLException {
		int id = rs.getInt("user_id");
		String name = rs.getString("user_name");
		String lastName = rs.getString("user_lastName");
		String firstName = rs.getString("user_firstName");
		String email = rs.getString("user_email");
		String city = rs.getString("user_city");
		String street = rs.getString("user_street");
		String password = rs.getString("user_password");
		String phone = rs.getString("user_phone");
		int postCode = rs.getInt("user_postCode");
		int credit = rs.getInt("user_credit");
		boolean isAdmin = rs.getBoolean("user_isAdmin");
		
		return new User(id, name, firstName, lastName, email, phone, street, postCode, city, password, credit, isAdmin);
	}

	@Override
	public PreparedStatement createInsertStatement(User user, Connection cnx) throws SQLException {
		
		String INSERT = "INSERT INTO USERS(username, last_name, first_name,phone, email, street, postal_code, city, password, credits, is_admin) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		
		PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getLastname());
		stmt.setString(3, user.getFirstname());
		stmt.setString(4, user.getPhone());
		stmt.setString(5, user.getMail());
		stmt.setString(6, user.getStreet());
		stmt.setInt(7, user.getPostCode());
		stmt.setString(8, user.getCity());
		stmt.setString(9, user.getPassword());
		stmt.setInt(10, user.getPoints());
		stmt.setBoolean(11, user.isAdmin());
		
		return stmt;	
	}	

	@Override
	public PreparedStatement createUpdateStatement(User user, Connection cnx) throws SQLException {
		
		String UPDATE = "UPDATE dbo.USERS SET user_name=?, user_lastName=?, user_firstName=?, user_email=?, user_city=?, user_street=?, user_password=?, user_phone=? user_postCode=?, user_credit=?, user_isAdmin=?, WHERE user_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(UPDATE);
	
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getLastname());
		stmt.setString(3, user.getFirstname());
		stmt.setString(4, user.getMail());
		stmt.setString(5, user.getCity());
		stmt.setString(6, user.getStreet());
		stmt.setString(7, user.getPassword());
		stmt.setString(8, user.getPhone());
		stmt.setInt(9, user.getPostCode());
		stmt.setInt(10, user.getPoints());
		stmt.setBoolean(11, user.isAdmin());
		stmt.setInt(12, user.getUserId());
		return stmt;
	}

	@Override
	public PreparedStatement deleteStatement(User user, Connection cnx) throws SQLException {
		
		String DELETE = "DELETE FROM dbo.USERS WHERE user_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(DELETE);
		
		stmt.setInt(1, user.getUserId());
		return null;
	}
}
