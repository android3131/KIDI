package com.example.demo.pckg1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
@Repository
public class CourseRepository {
	@Autowired
	ICourseRepository CourseRepository;
	@Autowired
	IMeetingRepository meetingRepository;
	@Autowired
	ICategoryRepository categoryRepository;
	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	/**
	 * 
	 * @param name
	 * @return
	 */

	/**
	 * Find a course by ID
	 * 
	 * @param Course ID
	 * @return Course
	 */	
	public Course findCourseByID(String courseID) {
		for (Course c: CourseRepository.findAll())
			if(c.getID().equals(courseID))
				return c;
		return null;
	}
	
	/**
	 * Adds a new course
	 * 
	 * @param course , categoryID
	 * @return All Course
	 */
	public List<Course> addANewCourse(Course course) {
			CourseRepository.save(course);
		
		return CourseRepository.findAll();
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
	 * @param ID
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
	 * @param courseID
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
	 * @param ID
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
	 * @param courseID, eaderID
	 * @return returns true if the leader was added successfully, false otherwise.
	 */
	public Boolean addLeaderToCourse(String courseID, String leaderID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			if (!(course.get().getLeadersIDs().contains(leaderID))) {
				if (course.get().getKidsIDs().add(leaderID)) {
					CourseRepository.save(course.get());
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Removes a leader from a course
	 * 
	 * @param courseID, leaderID
	 * @return returns true if the leader was removed successfully from the course,
	 * false otherwise.
	 */
	public Boolean removeLeaderFromCourse(String courseID, String leaderID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			if ((course.get().getLeadersIDs().contains(leaderID))) {
				if (course.get().getKidsIDs().remove(leaderID)) {
					CourseRepository.save(course.get());
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Returns a specific course's kids
	 * 
	 * @param ID
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
	 * @param courseID, kidID
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
	 * @param courseID, KidID
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
	 * @param courseID
	 * @param status
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
	 * @param courseID
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
	 * @param categoryID
	 * @return List of Course that belong to the given category
	 */
	public ArrayList<Course> getCategoryCourses(String categoryID) {
		ArrayList<Course> categoryCourses = new ArrayList<Course>();
		for (Course c : CourseRepository.findAll())
			if (c.getCategoryId().equals(categoryID)) {
				categoryCourses.add(c);
			}
		return categoryCourses;
	}
	
	/**
	 * Get a course by a course name
	 * 
	 * @param courseName
	 * @return Course
	 */
	public Course getASpecificCourseByName(String courseName) {
		for ( Course c :getAllCourses())
			if(c.getName().equals(courseName)) 
				return c;
		return null;
	}


}