package com.example.labcustomadapter;

public class City {
	private String cityName;
	private String cityCountry;
	private int cityID;
	
	public City(String cityName, String cityCountry, int cityID) {
		super();
		this.cityName = cityName;
		this.cityCountry = cityCountry;
		this.cityID = cityID;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getCityCountry() {
		return cityCountry;
	}
	
	public void setCityCountry(String cityCountry) {
		this.cityCountry = cityCountry;
	}
	
	public int getCityID() {
		return cityID;
	}
	
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	
}
