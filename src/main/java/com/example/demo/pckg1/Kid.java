package com.example.demo.pckg1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document
public class Kid {
	@Id
	private String id;
	@Field
	private String fullName;
	@Field
	private Date dateOfBirth;
	@Field
	private Gender gender;
	@Field
	private ArrayList<String> activeCourses;
	@Field
	private ArrayList<String> completedCourses;
	@Field
	private String parentId;
	@Field
	private Status status;
	@Field
	private LocalDate activeDate;
	@Field
	private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Kid() {
		
	}
	public Kid(String fullName, Date dateOfBirth, Gender gender, String parentId) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.parentId = parentId;
		activeCourses = new ArrayList<String>();
		completedCourses = new ArrayList<String>();
		status = Status.Active;
		activeDate = LocalDate.now();
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public ArrayList<String> addCourse(String courseID){
		if(activeCourses.contains(courseID)) {
			System.out.println("Couldn't add, the course already enrolled");
			return null;
		}
		activeCourses.add(courseID);
		return activeCourses;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<String> getActiveCourses() {
		return activeCourses;
	}
	public void setActiveCourses(ArrayList<String> activeCourses) {
		this.activeCourses = activeCourses;
	}
	public ArrayList<String> getCompletedCourses() {
		return completedCourses;
	}
	public void setCompletedCourses(ArrayList<String> completedCourses) {
		this.completedCourses = completedCourses;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDate getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(LocalDate activeDate) {
		this.activeDate = activeDate;
	}
	@Override
	public String toString() {
		return "Kid [fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", parentId=" + parentId + "]";
	}
	

}
