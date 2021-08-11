package com.example.demo.pckg1;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Address {
	
	@Id
	private String ID;
	
	@Field
	private String streetAddress;
	
	@Field 
	private String zipCode;
	
	@Field 
	private String city;
	
	@Field 
	private String state;
	
	@Field
	private String country;

	
	public Address() {
		super();
	}


	public Address(String streetAddress, String zipCode, String city, String state, String country) {
		super();
		this.streetAddress = streetAddress;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Address [ID=" + ID + ", streetAddress=" + streetAddress + ", zipCode=" + zipCode + ", city=" + city
				+ ", state=" + state + ", country=" + country + "]";
	}
}
