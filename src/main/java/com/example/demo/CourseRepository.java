package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Repository
public class CourseRepository {
	
	@Autowired
	ICourseRepository CourseRepository;
	
	@Autowired
	IMeetingRepository meetingRepository;
	
	@Autowired
	ICategoryRepository categoryRepository;

	@Autowired
	ILeaderRepository leaderRepository;


	/**
	 * Find a course by ID
	 * 
	 * @param Course ID
	 * @return Course
	 */	
	public Course findCourseByID(String courseID) {
		return CourseRepository.findById(courseID).get();
	}
	
	/**
	 * Adds a new course, and returns all courses
	 * 
	 * @param Course
	 * @return All Courses
	 */
	public List<Course> addANewCourse(Course course) {
		Optional<Category> coursecategory = categoryRepository.findById(course.getCategoryId());
		if (coursecategory.isPresent()) {
			for (Course c : CourseRepository.findAll()) {
				if (c.getName().equals(course.getName()))
					return CourseRepository.findAll();
			}
			CourseRepository.save(course);
		}
		return CourseRepository.findAll();
	}
	
	public ArrayList<String> getCourseLeadersByName(String courseName) {
		Course course = getASpecificCourseByName(courseName);
		if ( course!= null)
			return course.getLeadersIDs();
		return null;
	}
	/**
	 * Adds a new course, and returns it
	 * 
	 * @param course
	 * @return course
	 */
	public Course addNewCourse(Course course) {
		Optional<Category> coursecategory = categoryRepository.findById(course.getCategoryId());
		if (coursecategory.isPresent()) {
			for (Course c : CourseRepository.findAll()) {
				if (c.getID().equals(course.getID()))
					return c;
			}
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(course.getStartDateTime());
			for(int i = 0; i < 5; i++) {
				calendar.add(Calendar.WEEK_OF_YEAR,i);
				Meeting meeting =  new Meeting(course.getID(), calendar.getTime());
				meetingRepository.save(meeting);
				course.addMeeting(meeting.getId());
			}
			CourseRepository.save(course);
		}
		return course;
	}

	/**
	 * Returns all Course
	 * 
	 * @return all the Course that exist
	 */
	public List<Course> getAllCourses() {
		return CourseRepository.findAll();
	}

	/**
	 * Returns a specific course
	 * 
	 * @param Course ID
	 * @return Course if it was found, null if it was not found
	 */
	public Course getASpecificCourse(String ID) {
		Optional<Course> course = CourseRepository.findById(ID);
		if (course.isPresent())
			return course.get();
		return null;
	}

	/**
	 * Returns a course's category
	 * 
	 * @param Course ID
	 * @return Returns course category if the course was found, null otherwise
	 */
	public Category getCourseCategory(String courseID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			String categoryID = course.get().getCategoryId();
				for (Category c : categoryRepository.findAll()) {
					if (c.getId().equals(categoryID))
						return c;
				}
			}
		return null;
	}

	/**
	 * Returns a specific course's leaders
	 * 
	 * @param Course ID
	 * @return returns a course's leader if the course exists
	 */
	public ArrayList<String> getCourseLeaders(String ID) {
		Optional<Course> course = CourseRepository.findById(ID);
		if (course.isPresent())
			return course.get().getLeadersIDs();
		return null;
	}

	/**
	 * Adds a leader to a course
	 * 
	 * @param Course ID, Leader ID
	 * @return returns true if the leader was added successfully, false otherwise.
	 */
	public Boolean addLeaderToCourse(String courseID, String leaderID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			if (!(course.get().getLeadersIDs().contains(leaderID))) {
				if (course.get().getLeadersIDs().add(leaderID)) {
					CourseRepository.save(course.get());
					return true;
				}
			}
		}
		return false;
	}


	/** Removes a leader from a course
	 * @param courseID, Leader ID
	 * @return returns true if the leader was added successfully , false otherwise.
	 */
	public Boolean removeLeaderCourse(String courseID, String leaderID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		Optional<Leader> leader = leaderRepository.findById(leaderID);

		if(course.isPresent() && leader.isPresent())
			if((course.get().getLeadersIDs().contains(leaderID))&&
					(leader.get().getCoursesIDs().contains(courseID)))
				if(course.get().getLeadersIDs().remove(leaderID)&&
						leader.get().getCoursesIDs().remove(courseID)) {
					leaderRepository.save(leader.get());
					CourseRepository.save(course.get());
					return true;
				}
		return false;
	}
	/**
	 * Returns a specific course's kids
	 * 
	 * @param Course ID
	 * @return returns course's kids if the course exists
	 */
	public ArrayList<String> getCourseKids(String ID) {
		Optional<Course> course = CourseRepository.findById(ID);
		if (course.isPresent())
			return course.get().getKidsIDs();
		return null;
	}

	/**
	 * Adds a kid to a course
	 * 
	 * @param Course ID, Kid ID
	 * @return returns true if the kid was added successfully, false otherwise.
	 */
	public boolean addKidToCourse(String courseID, String kidID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			if (!(course.get().getKidsIDs().contains(kidID))) {
				if (course.get().getKidsIDs().add(kidID)) {
					CourseRepository.save(course.get());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes kid from course
	 * 
	 * @param Course ID, Kid ID
	 * @return returns true if the kid was removed successfully , false otherwise.
	 */
	public boolean removeKidFromCourse(String courseID, String kidID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			if (course.get().getKidsIDs().contains(kidID)) {
				if (course.get().getKidsIDs().remove(kidID)) {
					CourseRepository.save(course.get());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Updates a course's status
	 * 
	 * @param Course ID and a status
	 * @return returns true if the course's status was updated, false otherwise.
	 */
	public boolean updateCourseStatus(String courseID, Status status) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			course.get().setStatus(status);
			CourseRepository.save(course.get());
			return true;
		}
		return false;
	}

	/**
	 * Updating a course status after deleting, then saving the delete time
	 * 
	 * @param Course ID
	 * @return returns true when the course's finish date and status are updated
	 */
	public boolean updateFinishedDateByDelete(String courseID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			Date currentDate = new Date();
			course.get().setFinishDateTime(currentDate);
			course.get().setStatus(Status.InActive);
			CourseRepository.save(course.get());
			return true;
		}
		return false;
	}

	/**
	 * Returning a category's Course
	 * 
	 * @param Category ID
	 * @return List of Course that belong to the given category
	 */
	public ArrayList<Course> getCategoryCourses(String categoryID) {
		ArrayList<Course> categoryCourses = new ArrayList<Course>();
		for (Course c : CourseRepository.findAll()) {
			if (c.getCategoryId().equals(categoryID)) {
				categoryCourses.add(c);
				System.out.println("mutlaq      " + c.getID());
			}
		}
		return categoryCourses;
	}
	

	/**
	 * Get a course by a course name
	 * 
	 * @param Course name
	 * @return Course
	 */
	public Course getASpecificCourseByName(String courseName) {

		for ( Course c :getAllCourses())
			if(c.getName().equals(courseName)) 
				return c;
		return null;
	}
	
	

}

