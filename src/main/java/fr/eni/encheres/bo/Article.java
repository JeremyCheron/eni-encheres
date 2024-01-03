package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Article {

	private int articleId;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private double startPrice;
	private double finalPrice;
	private int userId;
	private int categoryId;
	
	public Article() {
		
	}

	public Article(int articleId, String name, String description, LocalDate startDate, LocalDate endDate,
			double startPrice, double finalPrice, int userId, int categoryId) {
		this.articleId = articleId;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startPrice = startPrice;
		this.finalPrice = finalPrice;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	
	
	
	
}
