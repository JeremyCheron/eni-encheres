package fr.eni.encheres.dal.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOHelper<T> {
	
	private final RowMapper<T> rm;
	
	public DAOHelper(RowMapper<T> rm) {
		this.rm = rm;
	}

	public T mapSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return rm.map(rs);
		}
		return null;
	}
	
	public List<T> mapResults(ResultSet rs) throws SQLException {
		List<T> results =new ArrayList<>();
		while (rs.next()) {
			results.add(rm.map(rs));
		}
		return results;
	}
	
	public PreparedStatement createInsertStatement(T Object, Connection cnx) throws SQLException {
		return rm.createInsertStatement(Object, cnx);
	}
	
	public PreparedStatement createUpdateStatement(T Object, Connection cnx) throws SQLException {
		return rm.createUpdateStatement(Object, cnx);
	}
	
	public PreparedStatement deleteStatement (T Object, Connection cnx) throws SQLException{
		return rm.deleteStatement(Object, cnx);
	}
}


