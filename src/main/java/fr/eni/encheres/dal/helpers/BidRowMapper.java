package fr.eni.encheres.dal.helpers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import fr.eni.encheres.bo.Bid;

public class BidRowMapper implements RowMapper<Bid>{

	@Override
	public Bid map(ResultSet rs) throws SQLException {
		int bidId = rs.getInt("bid_id");
		LocalDate bidDate = rs.getDate("bid_date").toLocalDate();
		LocalTime bidTime = rs.getTime("bid_time").toLocalTime();
		int amount = rs.getInt("bid_amount");
		int soldArticle = rs.getInt("article_id");
		int bider = rs.getInt("user_id");
		
		
		return new Bid(bidId, bidDate, bidTime, amount, soldArticle, bider);
	}

	@Override
	public PreparedStatement createInsertStatement(Bid bid, Connection cnx) throws SQLException {
		String INSERT = "INSERT INTO BIDS(bid_date, bid_time, bid_amount, article_id, user_id) VALUES (?????)";
		
		PreparedStatement stmt =cnx.prepareStatement(INSERT);
		stmt.setDate(1, Date.valueOf(bid.getBidDate()));
		stmt.setTime(2, Time.valueOf(bid.getBidTime()));
		stmt.setInt(3, bid.getAmount());
		stmt.setInt(4, bid.getSoldArticle());
		stmt.setInt(5, bid.getBider());
		
		return stmt;
		
	}

	@Override
	public PreparedStatement createUpdateStatement(Bid bid, Connection cnx) throws SQLException {
		String UPDATE = "UPDATE BIDS SET bid_date=?, bid_time=?, bid_amount=?, article_id=?, user_id=? WHERE bid_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(UPDATE);
		stmt.setDate(1, Date.valueOf(bid.getBidDate()));
		stmt.setTime(2, Time.valueOf(bid.getBidTime()));
		stmt.setInt(3, bid.getAmount());
		stmt.setInt(4, bid.getSoldArticle());
		stmt.setInt(5, bid.getBider());
		stmt.setInt(6, bid.getBidId());
		
		
		return stmt;
	}

	@Override
	public PreparedStatement deleteStatement(Bid bid, Connection cnx) throws SQLException {
		String DELETE = "DELETE FROM BIDS WHERE bid_id=?";
		
		PreparedStatement stmt = cnx.prepareStatement(DELETE);
		
		stmt.setInt(1, bid.getBidId());		
		
		return stmt;
	}

}
