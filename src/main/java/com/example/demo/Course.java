package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Course {
	
	@Id
	private String id;
	
	@Field
	private String name;
	
	@Field 
	private String description;
	
	@Field
	private double price;

    @Field
	private Date startDateTime;
	
	@Field 
	private Date finishDateTime;
	
	@Field 
	private String categoryId; 
	
	@Field 
	private String categoryName; 
	
	@Field 
	private Status status; 
	
	@Field
	private ArrayList<String> leadersIDs= new ArrayList<String>();;
	
	@Field 
	private String zoomMeetingLink;
	
	@Field 
	private String urlLink;
	
	@Field
	private ArrayList<String> kidsIDs= new ArrayList<String>();;
	
	@Field
	private String day;
	
	@Field
	private double meetingDuration;
	
	@Field
	private String startHour;
	@Field
	private String endHour;
	

	@Field ArrayList<String> meetings;
	public Course() {
		super();
	}
	


	public Course(String name, Date startDateTime, Date finishDateTime, String categoryId, String zoomMeetingLink,
			String day, String startHour,String urlLink, String endHour) {
		super();
		this.name = name;
		
		this.urlLink = urlLink;
		this.startDateTime = startDateTime;
		this.finishDateTime = finishDateTime;
		this.categoryId = categoryId;
		this.zoomMeetingLink = zoomMeetingLink;
		this.day = day;
		this.startHour = startHour;
		this.status = Status.Active;
		//this.meetingDuration calculate the duration of the meeting
		this.endHour = endHour;
	}



	public ArrayList<String> getMeetings() {
		return meetings;
	}

	public void setMeetings(ArrayList<String> meetings) {
		this.meetings = meetings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}


	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getFinishDateTime() {
		return finishDateTime;
	}

	public void setFinishDateTime(Date finishDateTime) {
		this.finishDateTime = finishDateTime;
	}
	
	public double getMeetingDuration() {
		return meetingDuration;
	}

	public void setMeetingDuration(double meetingDuration) {
		this.meetingDuration = meetingDuration;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public ArrayList<String> getLeadersIDs() {
		return leadersIDs;
	}

	public void setLeadersIDs(ArrayList<String> leadersIDs) {
		this.leadersIDs = leadersIDs;
	}

	
	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getZoomMeetingLink() {
		return zoomMeetingLink;
	}

	public void setZoomMeetingLink(String zoomMeetingLink) {
		this.zoomMeetingLink = zoomMeetingLink;
	}

	public ArrayList<String> getKidsIDs() {
		return kidsIDs;
	}

	public void setKidsIDs(ArrayList<String> kidsIDs) {
		this.kidsIDs = kidsIDs;
	}

	public String getID() {
		return id;
	}

	public void setID(String iD) {
		this.id = iD;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUrlLink() {
		return urlLink;
	}



	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String
				day) {
		this.day = day;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, categoryId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(id, other.id) && Objects.equals(categoryId, other.categoryId)
				&& Objects.equals(name, other.name);
	}
	
	
	
	
}