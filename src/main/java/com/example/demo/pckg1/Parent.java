package com.example.demo.pckg1;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Parent {
	@Id
	private String id;
	@Field
	private String fullName;
	@Field
	private String phoneNumber;
	@Field
	private String email; 
	@Field
	private String password;
	@Field
	private String paymentMethod; // ?? 
	@Field
	private String paymentDetails; 
	@Field
	private Status active;  
	@Field
	private List <String> kids;
	//@Field
	//private List <Bills> bill;
	@Field
	private LocalDate activeDate; //first time login 
	
	public Parent() {
		super();
		activeDate = java.time.LocalDate.now(); 
	}
	
	public Parent(String fullName, String phoneNumber, String email, String password) {
		super();
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		active = Status.Active; 
		this.kids = new ArrayList<>();
		activeDate = java.time.LocalDate.now(); 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}


	public List<String> getKids() {
		return kids;
	}

	public void setKids(List<String> kidsIds) {
		this.kids = kidsIds;
	}
	
	public void removeKid (String kidId){
		kids.remove(kidId); 
		
	}
	public void addKid (String kidId) {
		kids.add(kidId);
	}
/*	public List<Bills> getBill() {
		return bill;
	}

	public void setBill(List<Bills> bill) {
		this.bill = bill;
	}*/

	public LocalDate getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(LocalDate activeDate) {
		this.activeDate = activeDate;
	}
	public Status getActive () {
		return active; 
	}
	
	public void setActive (Status s) {
		active = s; 
	}

	@Override
	public String toString() {
		return "Parent [fullName=" + fullName + ", phoneNumnber=" + phoneNumber + ", email=" + email + "]";
	}
	

}