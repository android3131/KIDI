package com.example.demo.pckg1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingRepository {
	@Autowired
	IMeetingRepository meetingRepo;
	
	
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
		for (Meeting m : meetings) {
			if(m.getCourseId().equals(courseId)) toReturn.add(m);
		}
		return meetings;
	}
}
