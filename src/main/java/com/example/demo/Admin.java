package com.example.demo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
	public class Admin {
		
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
		private Status status;
		@Field
		private Date activeDate; //first time login
		public Admin() {
			super();
			activeDate = new Date();
		}
		
		public Admin(String fullName, String email, String password) {
			super();
			this.fullName = fullName;
			this.email = email;
			this.password = password;
			status = Status.Active;
			activeDate = new Date();
		}
		
		public Admin(String fullName, String phoneNumber, String email, String password) {
			super();
			this.fullName = fullName;
			this.phoneNumber = phoneNumber;
			this.email = email;
			this.password = password;
			this.status = Status.Active;
			this.activeDate = new Date();
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
		public Date getActiveDate() {
			return activeDate;
		}
		public void setActiveDate(Date activeDate) {
			this.activeDate = activeDate;
		}
		public Status getStatus() {
			return status;
		}
		
		public void setStatus (Status s) {
			status = s;
		}
		
}
