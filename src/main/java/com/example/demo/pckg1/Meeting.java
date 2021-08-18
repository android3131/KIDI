package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Meeting {
@Id
private String id;
@Field
private String courseId;
@Field
private ArrayList<String> participants;
@Field
private Date meetingDateTime;

public Meeting(String id, String courseId, Date meetingDateTime) {
	super();
	this.id = id;
	this.courseId = courseId;
	this.meetingDateTime = meetingDateTime;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCourseId() {
	return courseId;
}
public void setCourseId(String courseId) {
	this.courseId = courseId;
}
public ArrayList<String> getParticipants() {
	return participants;
}
public void setParticipants(ArrayList<String> participants) {
	this.participants = participants;
}
public Date getMeetingDateTime() {
	return meetingDateTime;
}
public void setMeetingDateTime(Date meetingDateTime) {
	this.meetingDateTime = meetingDateTime;
}

@Override
public String toString() {
	return "Meeting [id=" + id + ", courseId=" + courseId + ", meetingDateTime=" + meetingDateTime + "]";
}



}
