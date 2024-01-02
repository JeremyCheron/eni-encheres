package fr.eni.encheres.dal.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	
	T map(ResultSet rs) throws SQLException;

}
