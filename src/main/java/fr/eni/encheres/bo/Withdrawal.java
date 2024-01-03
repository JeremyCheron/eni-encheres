package fr.eni.encheres.bo;

public class Withdrawal {

	private int id;
	private String street;
	private int postCode;
	private String city;
	
	public Withdrawal() {

	}

	public Withdrawal(String street, int postCode, String city) {
		this.street = street;
		this.postCode = postCode;
		this.city = city;
	}

	public Withdrawal(int id, String street, int postCode, String city) {
		this.id = id;
		this.street = street;
		this.postCode = postCode;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
