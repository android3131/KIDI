package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Meeting implements Comparable<Meeting>{
@Id
private String id;
@Field
private String courseId;
@Field
private ArrayList<String> participants;
@Field
private Date meetingDate;

@Field
private String startHour;

@Field
private String endHour;
@Field
private double actualMeetingDuration;
@Field
private boolean cancelled;

public Meeting() {
	this.cancelled = false;
}
public Meeting( String courseId, Date meetingDate) {
	super();
	this.courseId = courseId;
	this.meetingDate = meetingDate;
	this.cancelled = false;
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
	return meetingDate;
}
public void setMeetingDateTime(Date meetingDate) {
	this.meetingDate = meetingDate;
}


public double getActualMeetingDuration() {
	return actualMeetingDuration;
}
public void setActualMeetingDuration(double actualMeetingDuration) {
	this.actualMeetingDuration = actualMeetingDuration;
}
public boolean isCancelled() {
	return cancelled;
}
public void setCancelled(boolean cancelled) {
	this.cancelled = cancelled;
}


@Override
public int hashCode() {
	return Objects.hash(courseId, id);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Meeting other = (Meeting) obj;
	return Objects.equals(courseId, other.courseId) && Objects.equals(id, other.id);
}
@Override
public String toString() {
	return "Meeting [id=" + id + ", courseId=" + courseId + ", meetingDateTime=" + meetingDate + "]";
}
@Override	
public int compareTo(Meeting o) {	
return getMeetingDateTime().compareTo(o.getMeetingDateTime());	
}



}
