package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import fr.eni.encheres.bll.error.ErrorManager;

import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.dal.helpers.BidRowMapper;
import fr.eni.encheres.dal.helpers.DAOHelper;

public class BidDAO implements DAO<Bid> {
	private Connection cnx;
	private final DAOHelper<Bid> daoHelper;
	private ErrorManager errorManager;

	private static final String SELECT_BY_ID = "SELECT * FROM BIDS WHERE article_id=?";
	private static final String SELECT_ALL = "SELECT * FROM BIDS";
	private static final String SELECT_HIGHEST_BID = "SELECT * FROM BIDS WHERE article_id=? ORDER BY bid_amount DESC LIMIT 1";

	public BidDAO(Connection cnx) {
		this.cnx = cnx;
		this.daoHelper = new DAOHelper<>(new BidRowMapper());
        this.errorManager = new ErrorManager();
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
				throw new DALException(errorManager.getErrorMessage("10300"), "10300");
			}
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
			if(generatedKeys.next()) {
				bid.setBidId(generatedKeys.getInt(1));
			} else {
				throw new DALException(errorManager.getErrorMessage("10301"), "10301");
			}
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10302"), "10302");
		}
	}

	@Override
	public void update(Bid bid) throws DALException {
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(bid, cnx))
		{ 
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10303"), "10303");
		}
	}

	@Override
	public void delete(Bid bid) throws DALException {
		try(PreparedStatement stmt = daoHelper.deleteStatement(bid, cnx))
		{
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(errorManager.getErrorMessage("10304"), "10304");
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
	
	public Bid getHighestBid(int articleId) throws DALException {
		Bid highestBid = null;
		try (PreparedStatement stmt = cnx.prepareStatement(SELECT_HIGHEST_BID)) {
			stmt.setInt(1, articleId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				BidRowMapper bidRowMapper = new BidRowMapper();
				highestBid = bidRowMapper.map(rs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return highestBid;
	}

}
