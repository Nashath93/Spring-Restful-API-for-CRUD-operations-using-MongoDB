package com.example.springmongo.model;

public class Traveller {

	private int id;
	private String travellerName;
	private String travellerCountry;
	private String travellerPassportNumber;

	public Traveller() {
	}

	public Traveller(int id, String travellerName, String travellerCountry, String travellerPassportNumber) {
		this.id = id;
		this.travellerName = travellerName;
		this.travellerCountry = travellerCountry;
		this.travellerPassportNumber = travellerPassportNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTravellerName() {
		return travellerName;
	}

	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}

	public String getTravellerCountry() {
		return travellerCountry;
	}

	public void setTravellerCountry(String travellerCountry) {
		this.travellerCountry = travellerCountry;
	}

	public String getTravellerPassportNumber() {
		return travellerPassportNumber;
	}

	public void setTravellerPassportNumber(String travellerPassportNumber) {
		this.travellerPassportNumber = travellerPassportNumber;
	}
	
	@Override
	public String toString() {
		return "[" + this.id
				+ "," + this.travellerName
				+ "," + this.travellerCountry
				+ "," + this.travellerPassportNumber
				+ "]";
				
	}

}
