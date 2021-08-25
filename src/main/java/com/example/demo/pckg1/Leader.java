package com.example.demo.pckg1;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Leader {
	
	@Id
	private String ID;
	
	@Field 
	private String fullName;
	
	@Field 
	private String email;
	
	@Field
	private String phoneNumber;
	
	@Field 
	private Address address;
	
	@Field 
	private Date DateOfBirth;
	
	@Field 
	private String profilePic; 
	
	@Field 
	private ArrayList<String> categoryIDs;
	
	@Field 
	private Status activeStatus;
	
	@Field 
	private String activeDate;

	public Leader(ArrayList<Leader> leadersByUserName) {
		super();
	}
	public Leader() {
		super();
	}

	public Leader(String fullName, String email, String phoneNumber, Address address, Date dateOfBirth, String profilePic,
				  ArrayList<String> categoryIDs, Status activeStatus, String activeDate) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.DateOfBirth = dateOfBirth;
		this.profilePic = profilePic;
		this.categoryIDs = categoryIDs;
		this.activeStatus = activeStatus;
		this.activeDate = activeDate;
	}

	public Leader(String fullName, String email, String phoneNumber,  String profilePic,
				  ArrayList<String> categoryIDs, Status activeStatus, String activeDate) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.profilePic = profilePic;
		this.categoryIDs = categoryIDs;
		this.activeStatus = activeStatus;
		this.activeDate = activeDate;
	}


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}



	public String getProfilePic() {
		return profilePic;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public ArrayList<String> getCategoryIDs() {
		return categoryIDs;
	}

	public void setCategoryIDs(ArrayList<String> categoryIDs) {
		this.categoryIDs = categoryIDs;
	}

	public Status getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Status activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DateOfBirth, ID, address, email, fullName, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leader other = (Leader) obj;
		return Objects.equals(DateOfBirth, other.DateOfBirth) && Objects.equals(ID, other.ID)
				&& Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(fullName, other.fullName) && Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() {
		return "Leader [ID=" + ID + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", activeStatus=" + activeStatus + ", activeDate=" + activeDate + "]";
	}


	
}
