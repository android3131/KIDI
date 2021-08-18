package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Course {
	
	@Id
	private String ID;
	
	@Field
	private String name;
	
	@Field 
	private String description;
	
	@Field
	private int price;

    @Field
	private Date startDateTime;
	
	@Field 
	private Date finishDateTime;
	
	@Field 
	private String categoryId; 
	
	@Field 
	private Status status; 
	
	@Field
	private ArrayList<String> leadersIDs;
	
	@Field 
	private String zoomMeetingLink;
	
	@Field
	private ArrayList<String> kidsIDs;
	
	@Field
	private Day day;
	
	@Field
	private double meetingDuration;
	
	public Course() {
		super();
	}
	
	public Course(String name, Date startDateTime,Date finishDateTime, Day day) {
		super();
		this.name = name;
		this.startDateTime = startDateTime;
		this.finishDateTime = finishDateTime;
		this.leadersIDs = new ArrayList<String>();
		this.kidsIDs = new ArrayList<String>();
		this.day = day;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
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
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
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
	
}