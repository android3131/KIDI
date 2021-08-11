package com.amal.myNewApp.pkg1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

	@Autowired
	ICoursesRepository coursesRepository;

	/**
   	 * Adds a new course
   	 * @param Course
   	 * @return All courses
   	 */
	public Boolean addANewCourse(Courses course) {
		if (coursesRepository.findById(course.getID()).isPresent()) {
			new ResponseEntity<>("Failed to add a new course, the name already exists in the system",
					HttpStatus.NOT_ACCEPTABLE);
			return false;
		}
		coursesRepository.save(course);
		new ResponseEntity<>("New course added successfully", HttpStatus.OK);
		return true;
	}
	
	/**
   	 * Returns all courses
   	 * @return all the courses that exist
   	 */
	public List<Courses> getAllCourses() {
		return coursesRepository.findAll();
	}

	/**
   	 * Returns a specific course
   	 * @param Course ID
   	 * @return Course if it was found, null if it was not found
   	 */
	public Optional<Courses> getASpecificCourse(String ID) {
		Optional<Courses> course = coursesRepository.findById(ID);
		if (course.isPresent())
			return course;
		new ResponseEntity<>("Course not found", HttpStatus.NOT_ACCEPTABLE);
		return null;
	}
	
	/**
   	 * Returns a course's category
   	 * @param Course ID
   	 * @return Course category if the course was found, null otherwise
   	 */
	public Categories getCourseCategory(String ID) {
		Optional<Courses> course = coursesRepository.findById(ID);
		if (course.isPresent())
			return course.get().getCategory();
		return null;
	}

	/**
   	 * Returns a specific course's leader
   	 * @param Course ID
   	 * @return returns a course's leader if the course exists
   	 */
	public ArrayList<String> getCourseLeaders(String ID) {
		Optional<Courses> course = coursesRepository.findById(ID);
		if (course.isPresent())
			return course.get().getLeadersIDs();
		return null;

	}
	
	/**
   	 * Adds a leader to a course
   	 * @param Course ID, Leader ID
   	 * @return returns true if the leader was added successfully , false otherwise.
   	 */
	public Boolean addLeaderToCourse(String courseID, String leaderID) {
		Optional<Courses> course = coursesRepository.findById(courseID);
		if(course.isPresent())
			if(!(course.get().getLeadersIDs().contains(leaderID)))
				if(course.get().getKidsIDs().add(leaderID))
					return true;
		return false;
	}

	/**
   	 * Returns a specific course's kids
   	 * @param Course ID
   	 * @return returns course's kids if the course exists
   	 */
	public ArrayList<String> getCourseKids(String ID) {
		Optional<Courses> course = coursesRepository.findById(ID);
		if (course.isPresent())
			return course.get().getKidsIDs();
		return null;
	}
	
	/**
   	 * Adds a kid to a course
   	 * @param Course ID, Kid ID
   	 * @return returns true if the kid was added successfully , false otherwise.
   	 */
	public Boolean addKidToCourse(String courseID, String kidID) {
		Optional<Courses> course = coursesRepository.findById(courseID);
		if(course.isPresent())
			if(!(course.get().getKidsIDs().contains(kidID)))
				if(course.get().getKidsIDs().add(kidID))
					return true;
		return false;
	}
	
	/**
   	 * Removes kid from course
   	 * @param Course ID, Kid ID
   	 * @return returns true if the kid was removed successfully , false otherwise.
   	 */
	public Boolean removeKidFromCourse(String courseID, String kidID) {
		Optional<Courses> course = coursesRepository.findById(courseID);
		if(course.isPresent())
			if(course.get().getKidsIDs().contains(kidID))
				if(course.get().getKidsIDs().remove(kidID))
					return true;
		return false;		
	}
}
