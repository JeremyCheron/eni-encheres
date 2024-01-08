package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.dal.helpers.BidRowMapper;
import fr.eni.encheres.dal.helpers.DAOHelper;

public class BidDAO implements DAO<Bid> {
	private Connection cnx;
	private final DAOHelper<Bid> daoHelper;

	private static final String SELECT_BY_ID = "SELECT * FROM BIDS WHERE article_id=?";
	private static final String SELECT_ALL = "SELECT * FROM BIDS";

	public BidDAO(Connection cnx) {
		this.cnx = cnx;
		this.daoHelper = new DAOHelper<>(new BidRowMapper());
	}

	@Override
	public Bid selectByID(int id) {
		Bid bid = null;
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)) 
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			bid = daoHelper.mapSingleResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
			new Exception("the selected bid doesn't exist");
		}
		return bid;
	}

	@Override
	public List<Bid> selectAll() {
		List<Bid> bids = new ArrayList<>();
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL))
		{
			ResultSet rs = stmt.executeQuery();
			bids = daoHelper.mapResults(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bids;
	}

	@Override
	public void insert(Bid bid) throws DALException {
		try (PreparedStatement stmt = daoHelper.createInsertStatement(bid, cnx))
		{
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows == 0) {
				throw new DALException("Bid Insertion Failed, no rows affected.");
			}
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
			if(generatedKeys.next()) {
				bid.setBidId(generatedKeys.getInt(1));
			} else {
				throw new DALException("Bid Insertion Failed, no ID obtained.");
			}
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error during insertion.");
		}
	}

	@Override
	public void update(Bid bid) throws DALException {
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(bid, cnx))
		{ 
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Bid Update Fail");
		}
	}

	@Override
	public void delete(Bid bid) throws DALException {
		try(PreparedStatement stmt = daoHelper.deleteStatement(bid, cnx))
		{
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error during deleting.");
		}
	}
	
	@Override
	public List<Bid> selectByCriteria(Map<String, Object> criteria) throws DALException {
		
		List<Bid> bids = new ArrayList<>();

		String query = "SELECT * FROM articles WHERE";
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
					bids = daoHelper.mapResults(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bids;
		
	}

}
