package fr.eni.encheres.dal.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.bo.Category;

public class CategoryRowMapper implements RowMapper<Category> {

	@Override
	public Category map(ResultSet rs) throws SQLException {

		int id = rs.getInt("category_id");
		String name = rs.getString("category_name");
		
		return new Category(id, name);
	}

	@Override
	public PreparedStatement createInsertStatement(Category category, Connection cnx) throws SQLException {

		String INSERT = "INSERT INTO categories (category_name) VALUES (?)";
		
		PreparedStatement stmt = cnx.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, category.getName());
		
		return stmt;
	}

	@Override
	public PreparedStatement createUpdateStatement(Category category, Connection cnx) throws SQLException {

		String UPDATE = "UPDATE CATEGORIES SET category_name=?";
		
		PreparedStatement stmt = cnx.prepareStatement(UPDATE);
		
		stmt.setString(1, category.getName());
		
		return stmt;
	}

	@Override
	public PreparedStatement deleteStatement(Category category, Connection cnx) throws SQLException {

		String DELETE = "DELETE FROM CATEGORIES WHERE category_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(DELETE);
		
		stmt.setInt(1, category.getId());
		
		return stmt;
	}

}
