package fr.eni.encheres.dal.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.bo.ImageFile;

public class ImageFileRowMapper implements RowMapper<ImageFile>{

	@Override
	public ImageFile map(ResultSet rs) throws SQLException {
		int imageId = rs.getInt("image_id");
		byte[] data= rs.getBytes("data");
		String filename= rs.getString("filename");
		int articleId = rs.getInt("article_id");
		
		
		return new ImageFile(imageId, data, filename, articleId);
	}

	@Override
	public PreparedStatement createInsertStatement(ImageFile image, Connection cnx) throws SQLException {
		String INSERT = "INSERT INTO images(image_id, data, filename, article_id) VALUES (?,?,?,?)";
		
		PreparedStatement stmt =cnx.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, image.getImageId());
		stmt.setBytes(2, image.getData());
		stmt.setString(3, image.getFileName());
		stmt.setInt(4, image.getArticleId());
	
		return stmt;
		
	}

	@Override
	public PreparedStatement createUpdateStatement(ImageFile image, Connection cnx) throws SQLException {
		String UPDATE = "UPDATE images SET data=?, filename=?, article_id=? WHERE image_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(UPDATE);
		stmt.setBytes(1, image.getData());
		stmt.setString(2, image.getFileName());
		stmt.setInt(3, image.getArticleId());
		stmt.setInt(4, image.getImageId());
		
		return stmt;
	}

	@Override
	public PreparedStatement deleteStatement(ImageFile image, Connection cnx) throws SQLException {
		String DELETE = "DELETE FROM images WHERE image_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(DELETE);
		
		stmt.setInt(1, image.getImageId());		
		
		return stmt;
	}

}
