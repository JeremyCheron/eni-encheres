package fr.eni.encheres.dal.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	
	T map(ResultSet rs) throws SQLException;
	PreparedStatement createInsertStatement(T Object, Connection cnx) throws SQLException;
	PreparedStatement createUpdateStatement(T Object, Connection cnx) throws SQLException;
	PreparedStatement deleteStatement(T object, Connection cnx)throws SQLException;

}
