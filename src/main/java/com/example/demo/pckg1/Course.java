package com.example.demo.pckg1;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Course {
	
	@Id
	private String ID;
	
	@Field
	private String name;
	
	@Field
	private int price;

    @Field
	private Date startDateTime;
	
	@Field 
	private Date finishDateTime;
	
	@Field 
	private Category category; 
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

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Course() {
		super();
	}

	public Course(String name, int price, Date startDateTime,Date finishDateTime, Category category, String zoomMeetingLink) {
		super();
		this.name = name;
		this.price = price;
		this.startDateTime = startDateTime;
		this.finishDateTime = finishDateTime;
		this.category = category;
		this.leadersIDs = new ArrayList<String>();
		this.zoomMeetingLink = zoomMeetingLink;
		this.kidsIDs = new ArrayList<String>();
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


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	
}