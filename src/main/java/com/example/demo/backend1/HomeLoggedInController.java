package com.example.demo.backend1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CategoryRepository;
import com.example.demo.Course;
import com.example.demo.CourseRepository;
import com.example.demo.Kid;
import com.example.demo.KidRepository;
import com.example.demo.Meeting;
import com.example.demo.MeetingRepository;
import com.example.demo.Parent;
import com.example.demo.Parent_repository;



@RestController
public class HomeLoggedInController {
		@Autowired
		private Parent_repository parentRepository;
		@Autowired
		private MeetingRepository meetingRepository;
		@Autowired
		private CourseRepository courseRepository;
		@Autowired
		private CategoryRepository categoryRepo;
		@Autowired
		private MeetingRepository meetingRepo;
		@Autowired 
		private KidRepository kidRepo;
		
		// DATABASE
		
//		@PostMapping("/initializeparent")
//		public List<Parent> initializeDB(){
//			long DAY_IN_MS = 1000 * 60 * 60 * 24;
//			String[] categories = {"space","art","music","science","poetry"};
//			Parent parent = parentRepository.addNewParent(new Parent("reem","","",""));
//			parentRepository.addNewKid(parent.getId(), new Kid("mutlaq",new Date(),Gender.Girl));
//			parentRepository.addNewKid(parent.getId(), new Kid("hijazi",new Date(),Gender.Girl));
//			List<Kid> parentKids = parentRepository.GetAllKidsOfParent(parent.getId());
//			for(String categ: categories) {
//				categoryRepo.addANewCategory(new Category(categ,"")); // create new category
//			}
//			List<Category> allCateg = categoryRepo.getAllCategories();
//			courseRepository.addANewCourse(new Course("123",new Date(),new Date(),Day.Monday,(allCateg.get(new Random().nextInt(allCateg.size())).getId())));
//			courseRepository.addANewCourse(new Course("124",new Date(),new Date(),Day.Monday,(allCateg.get(new Random().nextInt(allCateg.size())).getId())));
//			courseRepository.addANewCourse(new Course("125",new Date(),new Date(),Day.Monday,(allCateg.get(new Random().nextInt(allCateg.size())).getId())));
//			courseRepository.addANewCourse(new Course("125",new Date(),new Date(),Day.Monday,(allCateg.get(new Random().nextInt(allCateg.size())).getId())));
//			courseRepository.addANewCourse(new Course("126",new Date(),new Date(),Day.Monday,(allCateg.get(new Random().nextInt(allCateg.size())).getId())));
//			Date random_date = new Date();
//			List<Meeting> meetingList = new ArrayList<Meeting>();
//			for(Course course: courseRepository.getAllCourses()) {
//				random_date = new Date((new Date()).getTime() + ((int)Math.floor(Math.random()*(365)+1))*DAY_IN_MS);
//				random_date = new Date(random_date.getTime() - ((int)Math.floor(Math.random()*(35)+1))*DAY_IN_MS);
//				meetingList = meetingRepository.addANewMeeting(new Meeting(course.getID(),random_date));
//			}
//			for(Course course: courseRepository.getAllCourses()) {
//				random_date = new Date((new Date()).getTime() - ((int)Math.floor(Math.random()*(365)+1))*DAY_IN_MS);
//				random_date = new Date(random_date.getTime() - ((int)Math.floor(Math.random()*(35)+1))*DAY_IN_MS);
//				meetingList = meetingRepository.addANewMeeting(new Meeting(course.getID(),random_date));
//			}
//			
//			List<Course> allCourses = courseRepository.getAllCourses();
//
//			for(Kid kid: parentKids) {
//				kidRepo.addCourseToKid(kid.getId(),(allCourses.get(new Random().nextInt(allCourses.size()))).getID());
//				kidRepo.addCourseToKid(kid.getId(),(allCourses.get(new Random().nextInt(allCourses.size()))).getID());
//			}
//			
//			return parentRepository.getAllParents();
//		}
		


		
		@GetMapping("getallparentkids/{id}")
		public List<Kid> getallparentkid(@PathVariable String id){
			return parentRepository.GetAllKidsOfParent(id);
		}
		
		@GetMapping("getallkidmeetings/{id}")
		public List<String> getallkidmeetings(@PathVariable String id){
			return kidRepo.getMeetingsByKidId(id);
		}
		
		@GetMapping("getallcourses")
		public List<Course> getallcourses(){
			return courseRepository.getAllCourses();
		}

			
		//GET ALL kids ACTIVE courses
		@GetMapping("getkidscourses/{id}")
		public List<Course> getAllKidsActiveCourses(@PathVariable String id){
			List<Kid> kidsList= parentRepository.GetAllKidsOfParent(id);
			List<Course> courses= new ArrayList();
			for(Kid kid: kidsList)
			{
				
				List<String> temp= kid.getActiveCourses();
				if (!temp.isEmpty())
				{
					for(String string: temp)
					{
						courses.add(courseRepository.findCourseByID(string));
					}
				}
				else
				{
					System.out.println("NO ACTIVE COURSES FOR KID");
				}
				
			}
		
			return courses;
		}
		
		
		//GET ALL PARENT'S KIDS FINISHED COURSES
		@GetMapping("getkidsfinishedcourses/{id}")
		public List<Course> getAllKidsFinishedCourses(@PathVariable String id){
			List<Kid> kidsList= parentRepository.GetAllKidsOfParent(id);
			List<Course> courses= new ArrayList();
			for(Kid kid: kidsList)
			{
				List<String> temp= kid.getCompletedCourses();
				for(String string: temp)
				{
					courses.add(courseRepository.findCourseByID(string));
				}
			}
			return courses;
		}
		
		
		//GET ALL PARENT'S KIDS FINISHED COURSES SORTED
		// function returns two lists, first include all kids, second include all meetings. 
		// kids[index] is compatible to meetings[index]  such that we can get to every kid's meeting using an index
		
		@GetMapping("funwehadgetfinishedkidscoursessorted/{id}")
		public HashMap<List<Kid>,List<Meeting>> getAllKidsFinishedCoursesSorted(@PathVariable String id){
			HashMap<Kid,List<Meeting>> kidCompletedMeetingSorted = new HashMap<Kid,List<Meeting>>();
			List<Kid> kidsList  = parentRepository.GetAllKidsOfParent(id);
			for(Kid kid: kidsList) {
				List<Course> courses = new ArrayList();
				List<String> temp= kid.getActiveCourses();
				for(String string: temp)
				{
					courses.add(courseRepository.findCourseByID(string));
				}
				List<Meeting> meetings= new ArrayList();
				if(!courses.isEmpty())
				{

					for(Course course: courses)
					{
						List<Meeting> tempMeeting= new ArrayList();
						tempMeeting=meetingRepository.getAllCourseMeetings(course.getID());
						if(!tempMeeting.isEmpty())
						{
							for(Meeting meeting: tempMeeting)
							{
								if(meeting.getMeetingDateTime().before(new Date())) {
									meetings.add(meeting);
								}
							}
						}
					}
				}
				kidCompletedMeetingSorted.put(kid, meetings);
			}
			HashMap<String,Meeting> kidCompletedMeeting = new HashMap<String,Meeting>();
			for(Map.Entry<Kid,List<Meeting>> entry : kidCompletedMeetingSorted.entrySet()) {
				  Kid kid = entry.getKey();
				  List<Meeting> value = entry.getValue();
				  int i=0;
				  String kid_name = kid.getFullName();
				  for(Meeting meet: value) {
					  kidCompletedMeeting.put(kid_name+String.valueOf(i), meet);
					  i++;
				  }
			}
			
			List<Map.Entry<String, Meeting>> list = new LinkedList<Map.Entry<String, Meeting>>(kidCompletedMeeting.entrySet());
		 

		    Collections.sort(list, new Comparator<Map.Entry<String, Meeting>>() {
		    	public int compare(Map.Entry<String, Meeting> o1,
		                   		Map.Entry<String, Meeting> o2)
		        {
		    		return (o1.getValue()).compareTo(o2.getValue());
		        }
		   });
		         
		        
		    HashMap<String, Meeting> temp = new LinkedHashMap<String, Meeting>();
		    for (Map.Entry<String, Meeting> aa : list) {
		    	temp.put(aa.getKey(), aa.getValue());
		    }
		    
		    List<String> kidName = new ArrayList<String>();
		    List<Meeting> kidMeeting = new ArrayList<Meeting>();
		    Iterator hmIterator = temp.entrySet().iterator();
		    while (hmIterator.hasNext()) {
	            Map.Entry mapElement = (Map.Entry)hmIterator.next();
	            kidName.add(mapElement.getKey().toString());
	            kidMeeting.add((Meeting)mapElement.getValue());
	        }
		    
		    List<String> kidNameModified = new ArrayList<String>();
		    for(String name: kidName) {
		    	name = name.replaceAll("\\d","");
		    	kidNameModified.add(name);
		    }
		    
		    List<Kid> kids = new ArrayList<Kid>();
		    for(String name: kidNameModified) {
		    	for(Kid parentKid: kidsList) {
		    		if(parentKid.getFullName().equals(name)) {
		    			kids.add(parentKid);
		    		}
		    	}
		    }
		    HashMap<List<Kid>,List<Meeting>> kidsMeetingsLists = new HashMap<List<Kid>,List<Meeting>>();
		    kidsMeetingsLists.put(kids, kidMeeting);
			
			return kidsMeetingsLists;
		}
		
		
		//GET ALL PARENT'S KIDS UPCOMING COURSES SORTED
		// function returns two lists, first include all kids, second include all meetings. 
		// kids[index] is compatible to meetings[index]  such that we can get to every kid's meeting using an index
		
		@GetMapping("funweplangetkidscoursessorted/{id}")
		public HashMap<List<Kid>,List<Meeting>> getAllKidsNextCoursesSorted(@PathVariable String id){
			HashMap<Kid,List<Meeting>> kidCompletedMeetingSorted = new HashMap<Kid,List<Meeting>>();
			List<Kid> kidsList  = parentRepository.GetAllKidsOfParent(id);
			for(Kid kid: kidsList) {
				List<Course> courses = new ArrayList();
				List<String> temp= kid.getActiveCourses();
				for(String string: temp)
				{
					courses.add(courseRepository.findCourseByID(string));
				}
				List<Meeting> meetings= new ArrayList();
				if(!courses.isEmpty())
				{

					for(Course course: courses)
					{
						List<Meeting> tempMeeting= new ArrayList();
						tempMeeting=meetingRepository.getAllCourseMeetings(course.getID());
						if(!tempMeeting.isEmpty())
						{
							for(Meeting meeting: tempMeeting)
							{
								if(meeting.getMeetingDateTime().after(new Date())) {
									meetings.add(meeting);
								}
							}
						}
					}
				}
				kidCompletedMeetingSorted.put(kid, meetings);
			}
			HashMap<String,Meeting> kidCompletedMeeting = new HashMap<String,Meeting>();
			for(Map.Entry<Kid,List<Meeting>> entry : kidCompletedMeetingSorted.entrySet()) {
				  Kid kid = entry.getKey();
				  List<Meeting> value = entry.getValue();
				  int i=0;
				  String kid_name = kid.getFullName();
				  for(Meeting meet: value) {
					  kidCompletedMeeting.put(kid_name+String.valueOf(i), meet);
					  i++;
				  }
			}
			
			List<Map.Entry<String, Meeting>> list = new LinkedList<Map.Entry<String, Meeting>>(kidCompletedMeeting.entrySet());
		 

		    Collections.sort(list, new Comparator<Map.Entry<String, Meeting>>() {
		    	public int compare(Map.Entry<String, Meeting> o1,
		                   		Map.Entry<String, Meeting> o2)
		        {
		    		return (o1.getValue()).compareTo(o2.getValue());
		        }
		   });
		         
		        
		    HashMap<String, Meeting> temp = new LinkedHashMap<String, Meeting>();
		    for (Map.Entry<String, Meeting> aa : list) {
		    	temp.put(aa.getKey(), aa.getValue());
		    }
		    
		    List<String> kidName = new ArrayList<String>();
		    List<Meeting> kidMeeting = new ArrayList<Meeting>();
		    Iterator hmIterator = temp.entrySet().iterator();
		    while (hmIterator.hasNext()) {
	            Map.Entry mapElement = (Map.Entry)hmIterator.next();
	            kidName.add(mapElement.getKey().toString());
	            kidMeeting.add((Meeting)mapElement.getValue());
	        }
		    
		    List<String> kidNameModified = new ArrayList<String>();
		    for(String name: kidName) {
		    	name = name.replaceAll("\\d","");
		    	kidNameModified.add(name);
		    }
		    
		    List<Kid> kids = new ArrayList<Kid>();
		    for(String name: kidNameModified) {
		    	for(Kid parentKid: kidsList) {
		    		if(parentKid.getFullName().equals(name)) {
		    			kids.add(parentKid);
		    		}
		    	}
		    }
		    
		    HashMap<List<Kid>,List<Meeting>> kidsMeetingsLists = new HashMap<List<Kid>,List<Meeting>>();
		    kidsMeetingsLists.put(kids, kidMeeting);
			
			return kidsMeetingsLists;	
		}
		
		
		// DELETE ALL COURSE MEETINGS FROM KID
		
		@PutMapping("deletekidcourse/{id}")
		public List<Kid> deleteCourseFromkID(@PathVariable String id,@RequestBody  String courseId){
			List<String> kidslistcourseid = kidRepo.getKidActiveCoursesIds(id);
			kidRepo.removeCourseFromKid(id, courseId);
			kidslistcourseid = kidRepo.getKidActiveCoursesIds(id);
			
			String parentId = kidRepo.getParentId(id);
			
			return parentRepository.GetAllKidsOfParent(parentId);
		}
		
		// DELETE COURSE MEETING FROM KID'S LIST OF MEETINGS
		
		@PutMapping("deletekidmeeting/{id}")
		public List<String> deleteMeetingFromkID(@PathVariable String id,@RequestBody  String meetingId){
			List<String> kidmeetinglist = kidRepo.getMeetingsByKidId(id);
			String newmeetingId = meetingId.replace(String.valueOf('"'),"");
			for(String meetId: kidmeetinglist) {
				if(meetId.equals(newmeetingId)) {
					kidRepo.deleteMeetingFromKid(id, newmeetingId);
				}
			}
			return kidRepo.getMeetingsByKidId(id);
		}
	}
