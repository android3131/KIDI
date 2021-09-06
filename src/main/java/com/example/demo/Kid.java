package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
	private Date activeDate;
	@Field
	private String image;
	@Field
	private ArrayList<String> meetings;
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
		this.activeDate = new Date();
		this.activeCourses = new ArrayList<String>();
		this.completedCourses = new ArrayList<String>();
		this.meetings = new ArrayList<String>();
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public ArrayList<String> getMeetings() {
		return meetings;
	}
	public void setMeetings(ArrayList<String> meetings) {
		this.meetings = meetings;
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
	public List<String> addCourse(String courseID){
		if(activeCourses.contains(courseID)) {
			System.out.println("Couldn’t add, the course already enrolled");
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
	public List<String> getActiveCourses() {
		return activeCourses;
	}
	public void setActiveCourses(ArrayList<String> activeCourses) {
		this.activeCourses = activeCourses;
	}
	public List<String> getCompletedCourses() {
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
		String newcourseId = courseId.replace(String.valueOf('"'),"");
		for(String listCourseId: activeCourses) {
			if(listCourseId.equals(newcourseId)) {
				if(activeCourses.remove(newcourseId)) {
					completedCourses.add(newcourseId);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public List<String> addMeeting(String meetingID){
		if(meetings.contains(meetingID)) {
			System.out.println("Couldn’t add, the meeting already enrolled");
			return null;
		}
		meetings.add(meetingID);
		return meetings;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, fullName, gender, id, parentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kid other = (Kid) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(fullName, other.fullName)
				&& gender == other.gender && Objects.equals(id, other.id) && Objects.equals(parentId, other.parentId);
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