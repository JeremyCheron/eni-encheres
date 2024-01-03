package fr.eni.encheres.dal.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.bo.Withdrawal;

public class WithdrawalRowMapper implements RowMapper<Withdrawal>{

	@Override
	public Withdrawal map(ResultSet rs) throws SQLException {

		int id = rs.getInt("article_id");
		String street = rs.getString("street");
		int postCode = rs.getInt("postal_code");
		String city = rs.getString("city");
		
		return new Withdrawal(id, street, postCode, city);
	}

	@Override
	public PreparedStatement createInsertStatement(Withdrawal withdrawal, Connection cnx) throws SQLException {

		String INSERT = "INSERT INTO WITHDRAWALS (street, postal_code, city) VALUES (?,?,?)";
		
		PreparedStatement stmt = cnx.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, withdrawal.getStreet());
		stmt.setInt(2, withdrawal.getPostCode());
		stmt.setString(3, withdrawal.getCity());
		
		return stmt;
	}

	@Override
	public PreparedStatement createUpdateStatement(Withdrawal withdrawal, Connection cnx) throws SQLException {

		String UPDATE = "UPDATE WITHDRAWALAS SET street=?, postal_code=?, city=? WHERE article_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(UPDATE);
		
		stmt.setString(1, withdrawal.getStreet());
		stmt.setInt(2, withdrawal.getPostCode());
		stmt.setString(3, withdrawal.getCity());
		stmt.setInt(4, withdrawal.getId());
		
		return stmt;
	}

	@Override
	public PreparedStatement deleteStatement(Withdrawal withdrawal, Connection cnx) throws SQLException {

		String DELETE = "DELETE FROM WITHDRAWALS WHERE article_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(DELETE);
		
		stmt.setInt(1, withdrawal.getId());
		
		return stmt;
	}

}
