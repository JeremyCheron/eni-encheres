package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bo.ImageFile;
import fr.eni.encheres.dal.helpers.DAOHelper;
import fr.eni.encheres.dal.helpers.ImageFileRowMapper;

public class ImageFileDAO implements DAO<ImageFile> {
	private Connection cnx;
	private final DAOHelper<ImageFile> daoHelper;

	private static final String SELECT_BY_ID = "SELECT * FROM images WHERE image_id=?";
	private static final String SELECT_ALL = "SELECT * FROM images";

	public ImageFileDAO(Connection cnx) {
		this.cnx = cnx;
		this.daoHelper = new DAOHelper<>(new ImageFileRowMapper());
	}

	@Override
	public ImageFile selectByID(int id) {
		ImageFile image = null;
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)) 
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			image = daoHelper.mapSingleResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
			new Exception("the selected  doesn't exist");
		}
		return image;
	}

	@Override
	public List<ImageFile> selectAll() {
		List<ImageFile> images = new ArrayList<>();
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL))
		{
			ResultSet rs = stmt.executeQuery();
			images = daoHelper.mapResults(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return images;
	}

	@Override
	public void insert(ImageFile image) throws DALException {
		try (PreparedStatement stmt = daoHelper.createInsertStatement(image, cnx))
		{
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows == 0) {
				throw new DALException("Image Insertion Failed, no rows affected.");
			}
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
			if(generatedKeys.next()) {
				image.setImageId(generatedKeys.getInt(1));
			} else {
				throw new DALException("Image Insertion Failed, no ID obtained.");
			}
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error during insertion.");
		}
	}

	@Override
	public void update(ImageFile image) throws DALException {
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(image, cnx))
		{ 
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Image Update Fail");
		}
	}

	@Override
	public void delete(ImageFile image) throws DALException {
		try(PreparedStatement stmt = daoHelper.deleteStatement(image, cnx))
		{
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error during deleting.");
		}
	}

	@Override
	public List<ImageFile> selectByCriteria(Map<String, Object> criteria) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
