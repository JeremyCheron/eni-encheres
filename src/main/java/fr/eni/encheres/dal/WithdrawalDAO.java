package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.Withdrawal;
import fr.eni.encheres.dal.helpers.DAOHelper;
import fr.eni.encheres.dal.helpers.WithdrawalRowMapper;

public class WithdrawalDAO implements DAO<Withdrawal>{
	
	private Connection cnx;
	private final DAOHelper<Withdrawal> daoHelper;
	
	private static final String SELECT_BY_ID = "SELECT * FROM WITHDRAWALS WHERE article_id=?";
	private static final String SELECT_ALL = "SELECT * FROM WITHDRAWALS";


	public WithdrawalDAO(Connection cnx) {
		this.cnx = cnx;
		this.daoHelper = new DAOHelper<>(new WithdrawalRowMapper());
	}

	@Override
	public Withdrawal selectByID(int withdrawalId) throws DALException{

		Withdrawal withdrawal = null;
		
		try(PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)){
			stmt.setInt(1, withdrawalId);
			ResultSet rs = stmt.executeQuery();
			withdrawal = daoHelper.mapSingleResult(rs);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return withdrawal;
	}

	@Override
	public List<Withdrawal> selectAll() throws DALException{
		List<Withdrawal> withdrawals = new ArrayList<>();
		try(PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();
			withdrawals = daoHelper.mapResults(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return withdrawals;
	}

	@Override
	public void insert(Withdrawal withdrawal) throws DALException {
		try (PreparedStatement stmt = daoHelper.createInsertStatement(withdrawal, cnx)) 
		{
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows == 0) {
				throw new DALException("Insertion Failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					withdrawal.setWithdrawalId(affectedRows);
				} else {
					throw new DALException("Insertion Failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error during insertion.");
		}
	}

	@Override
	public void update(Withdrawal withdrawal) throws DALException {
		
		try (PreparedStatement stmt = daoHelper.createUpdateStatement(withdrawal, cnx))
		{ 
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Update Fail");
		}
		
	}

	@Override
	public void delete(Withdrawal withdrawal) throws DALException {

		try(PreparedStatement stmt = daoHelper.deleteStatement(withdrawal, cnx))
		{
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error during deleting.");
		}
		
	}

	@Override
	public List<Withdrawal> selectByCriteria(Map<String, Object> criteria) throws DALException {
		
		List<Withdrawal> withdrawals = new ArrayList<>();

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
					withdrawals = daoHelper.mapResults(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return withdrawals;
		
	}
	
}
