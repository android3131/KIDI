package com.example.demo.pckg1;

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
	private ArrayList<String> activeCourses = new ArrayList<String>();;
	@Field
	private ArrayList<String> completedCourses = new ArrayList<String>();;
	@Field
	private String parentId;
	@Field
	private Status status;
	@Field
	private Date activeDate;
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
	public Kid(String fullName, Date dateOfBirth, Gender gender) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		activeDate = new Date();
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
	public Date getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}
	
	public boolean deleteCourse(String courseId) {
		if(activeCourses.remove(id)) {
			completedCourses.add(courseId);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Kid [fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", parentId=" + parentId + "]";
	}
	
	
	public static void main(String args[]) {
		Kid kid = new Kid("SSS", new Date(1995, 6, 4),Gender.Boy);
		System.out.println("mutlaq"+ kid.getActiveDate().toString());
	}

}
