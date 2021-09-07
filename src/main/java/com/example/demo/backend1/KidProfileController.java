package com.example.demo.backend1;



	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Date;
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Course;
import com.example.demo.Kid;
import com.example.demo.KidRepository;
import com.example.demo.Meeting;
import com.example.demo.MeetingRepository;

	@RestController
	public class KidProfileController {
		@Autowired
		private KidRepository kidRepo;
		@Autowired
		private MeetingRepository meetingRepo;
		
		
		
//		public List<Kid> getAllKidsOfParent(String id)
//		{
//			List<Kid> allKids= parentRepository.GetAllKidsOfParent(id);
//			return allKids;
//		}
		

		//get kid by id
		@GetMapping("getkidbyid/{kidid}")
		public Kid getKid(@PathVariable String kidid)
		{
			return kidRepo.getKidWithId(kidid);
		}
		
		//works but not needed
//		@GetMapping("getkidbirthday/{kidid}")
//		public Date getKidsBirthday(@PathVariable String kidid)
//		{
//			return kidRepo.getKidWithId(kidid).getDateOfBirth();
//		}
		
		
		//get all active meeting of active courses of kids
		@GetMapping("getkidsactivecourses/{kidid}")
		public List<Meeting> getAllActiveCoursesOfKid(@PathVariable String kidid)
		{
			Kid kid= kidRepo.getKidWithId(kidid);
			List<String> courses= kid.getActiveCourses();
			List<Course> activeCourses= new ArrayList();
			List<Meeting> meetings= new ArrayList();
			for(String courseid: courses)
			{
				List<Meeting> tempMeeting= new ArrayList();
				tempMeeting=meetingRepo.getAllCourseMeetings(courseid);
				if(!tempMeeting.isEmpty())
				{
					for(Meeting meeting: tempMeeting){
						if(!meeting.isCancelled())
						{
							meetings.add(meeting);
						}
					}	
				}
			}

		//Collections.sort(meetings);
		return meetings;

	}
		
		//get number active courses
		@GetMapping("getnumberactivecourses/{kidid}")
		public int getNumberActiveCourses(@PathVariable String kidid)
		{
			return getAllActiveCoursesOfKid(kidid).size();
		}
		
		//get number completed courses
		@GetMapping("getnumbercompletedcourses/{kidid}")
		public int getNumberCompetedCourses(@PathVariable String kidid)
		{
			Kid kid= kidRepo.getKidWithId(kidid);
			return kid.getCompletedCourses().size();
		}
		
		
		//GET all Active courses SORTED
		@GetMapping("getlistofkidsactivecoursessorted/{id}")
		public List<Meeting> getAllKidsActiveCoursesSorted(@PathVariable String id){
			List<Meeting> meetings= new ArrayList();
			List<String> courses= new ArrayList();

			courses= kidRepo.getKidActiveCoursesIds(id);
			for(String courseid : courses)
			{
				List<Meeting> allCoursesMeetings=meetingRepo.getAllCourseMeetings(courseid);
				for(Meeting meeting: allCoursesMeetings)
				{
					meetings.add(meeting);
				}

			}
			//Collections.sort(meetings);
			return meetings;
		}
	}
