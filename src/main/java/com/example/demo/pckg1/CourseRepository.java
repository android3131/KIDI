package com.example.demo.pckg1;

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
	ICourseRepository CourseRepository;

	/**
   	 * Adds a new course
   	 * @param course
   	 * @return All Course
   	 */
	public Boolean addANewCourse(Course course) {
		if (CourseRepository.findById(course.getID()).isPresent()) {
			new ResponseEntity<>("Failed to add a new course, the name already exists in the system",
					HttpStatus.NOT_ACCEPTABLE);
			return false;
		}
		CourseRepository.save(course);
		new ResponseEntity<>("New course added successfully", HttpStatus.OK);
		return true;
	}
	
	/**
   	 * Returns all Course
   	 * @return all the Course that exist
   	 */
	public List<Course> getAllCourse() {
		return CourseRepository.findAll();
	}

	/**
   	 * Returns a specific course
   	 * @param  ID
   	 * @return Course if it was found, null if it was not found
   	 */
	public Optional<Course> getASpecificCourse(String ID) {
		Optional<Course> course = CourseRepository.findById(ID);
		if (course.isPresent())
			return course;
		new ResponseEntity<>("Course not found", HttpStatus.NOT_ACCEPTABLE);
		return null;
	}

	
	/**
   	 * Returns a course's category
   	 * @param  ID
   	 * @return Course category if the course was found, null otherwise
   	 */
	public Category getCourseCategory(String ID) {
		Optional<Course> course = CourseRepository.findById(ID);
		if (course.isPresent())
			return course.get().getCategory();
		return null;
	}

	/**
   	 * Returns a specific course's leader
   	 * @param  ID
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
	 * @param courseID, Leader ID
	 * @return returns true if the leader was added successfully , false otherwise.
	 */
	public Boolean addLeaderToCourse(String courseID, String leaderID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if(course.isPresent())
			if(!(course.get().getLeadersIDs().contains(leaderID)))
				if(course.get().getLeadersIDs().add(leaderID)) {
					CourseRepository.save(course.get());

					return true;

				}
		return false;
	}


	/**
	 * Removes a leader from a course
	 * @param courseID, Leader ID
	 * @return returns true if the leader was added successfully , false otherwise.
	 */
	public Boolean removeLeaderCourse(String courseID, String leaderID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if(course.isPresent())
			if((course.get().getLeadersIDs().contains(leaderID)))
				if(course.get().getLeadersIDs().remove(leaderID)) {
					CourseRepository.save(course.get());
					return true;
				}
		return false;
	}
	



	/**
   	 * Returns a specific course's kids
   	 * @param  ID
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
   	 * @param courseID, kidID
   	 * @return returns true if the kid was added successfully , false otherwise.
   	 */
	public Boolean addKidToCourse(String courseID, String kidID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if(course.isPresent())
			if(!(course.get().getKidsIDs().contains(kidID)))
				if(course.get().getKidsIDs().add(kidID))
					return true;
		return false;
	}
	
	/**
   	 * Removes kid from course
   	 * @param courseID, kidID
   	 * @return returns true if the kid was removed successfully , false otherwise.
   	 */
	public Boolean removeKidFromCourse(String courseID, String kidID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if(course.isPresent())
			if(course.get().getKidsIDs().contains(kidID))
				if(course.get().getKidsIDs().remove(kidID))
					return true;
		return false;		
	}
	public Course getASpecificCourseByName(String courseName) {

		for ( Course c :getAllCourse()) {
			if(c.getName().equals(courseName)) {
				return c;

			}
		}
		//new ResponseEntity<>("Course not found", HttpStatus.NOT_ACCEPTABLE);/
		return null;
	}
	public ArrayList<String> getCourseLeadersByName(String courseName) {
		Course course = getASpecificCourseByName(courseName);
		if ( course!= null)
			return course.getLeadersIDs();
		return null;
	}

}
