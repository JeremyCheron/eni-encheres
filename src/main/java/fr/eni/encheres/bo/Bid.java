package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Bid {

	private int bidId;
	private LocalDate bidDate;
	private LocalTime bidTime;
	private int amount;

	private int soldArticle;
	private int bider;
	

	public Bid() {
	}
	

	public Bid(int amount, int soldArticle, int bider) {
		this.amount = amount;
		this.soldArticle = soldArticle;
		this.bider = bider;
	}


	public Bid(LocalDate _bidDate, LocalTime _bidTime, int _amount,
			int _soldArticle,  int _bider) {
		this.bidDate = _bidDate;
		this.bidTime = _bidTime;
		this.amount = _amount;
		this.soldArticle = _soldArticle;
		this.bider = _bider;
		
	}
	
	public Bid(int _bidId, LocalDate _bidDate, LocalTime _bidTime, int _amount, int _soldArticle, int _bider) {
		this.bidId = _bidId;
		this.bidDate = _bidDate;
		this.bidTime = _bidTime;
		this.amount = _amount;
		this.soldArticle = _soldArticle;
		this.bider = _bider;
		
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public LocalDate getBidDate() {
		return bidDate;
	}

	public void setBidDate(LocalDate bidDate) {
		this.bidDate = bidDate;
	}

	public LocalTime getBidTime() {
		return bidTime;
	}

	public void setBidTime(LocalTime bidTime) {
		this.bidTime = bidTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getBider() {
		return bider;
	}

	public void setBider(int bider) {
		this.bider = bider;
	}

	public int getSoldArticle() {
		return soldArticle;
	}

	public void setSoldArticle(int soldArticle) {
		this.soldArticle = soldArticle;
	}

}
