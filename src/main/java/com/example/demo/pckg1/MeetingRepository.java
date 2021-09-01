package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingRepository {
	@Autowired
	IMeetingRepository meetingRepo;
	
	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	/**
	 * 
	 * @param new meeting
	 * @return meeting object
	 */
	public Meeting addNewMeeting(Meeting meeting) {
		return meetingRepo.save(meeting);
	}
	
	/**
	 * 
	 * @return All Meetings
	 */
	public ArrayList<Meeting> getAllMeetings(){
		return (ArrayList<Meeting>) meetingRepo.findAll();
	}
	
	
	
	////////////////// CHANGE IT TO CRITERIA YA MAALIM
	/**
	 * 
	 * @param courseId of the course 
	 * @return getting all the meeting of course
	 */
	public ArrayList<Meeting> getAllCourseMeetings(String courseId) {
		ArrayList<Meeting> meetings = (ArrayList<Meeting>) meetingRepo.findAll();
		ArrayList<Meeting> toReturn = new ArrayList<Meeting>();
		//meetings.stream().map(null);
		if(!meetings.isEmpty()) {
			for (Meeting m : meetings) {
				System.out.println ("in getAllCourseMeetings:  " + m);
				if((m.getCourseId()).equals(courseId))
					toReturn.add(m);
			}
		}
		return toReturn;
	}
	
	public Meeting getMeetingById(String meetingId) {
		Optional<Meeting> optional = meetingRepo.findById(meetingId);
		if(optional.isPresent()) {
			System.out.println("KID IS PRESENT");
			return optional.get();
		}
		return null;
	}
	/**
	 * 
	 * @param period
	 * @return
	 */
	public HashMap<String, Double> getActivityTime(int period) {
		if(period != 1 && period !=2 && period !=3) {
			new ResponseEntity<>("Input: 1- For week 2- For month 3- For year.", HttpStatus.NOT_ACCEPTABLE);
			return null;
		}
		Date d;
		if(period == 1) {
			d = new Date((new Date()).getTime()- 7*DAY_IN_MS);
		}else if(period == 2) {
			d = new Date((new Date()).getTime()-35*DAY_IN_MS);
		}
		else {
			d = new Date((new Date()).getTime()- 365*DAY_IN_MS);
		}
		double totalTime = 0;
		double doneTime = 0;
		
		for(Meeting meeting : getAllMeetings()) {
			if(meeting.getMeetingDateTime().after(d)) {
				totalTime += meeting.getActualMeetingDuration();
				if(!meeting.isCancelled()) {
					doneTime += meeting.getActualMeetingDuration();
				}
			}
		}
		HashMap<String, Double> toReturn = new HashMap<String,Double>();
		toReturn.put("totalTime", totalTime-doneTime);
		toReturn.put("activityTime", doneTime);
		return toReturn;
	}
	/**	
 * Adds a new meeting, and returns all meetings	
 * 	
 * @param meeting	
 * @return list of all meetings	
 */	
public List<Meeting> addANewMeeting(Meeting meeting) {	
	meetingRepo.save(meeting);	
	return meetingRepo.findAll();	
}
}
