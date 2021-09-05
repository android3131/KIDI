package com.example.demo;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class CourseRepository {
	
	@Autowired
	ICourseRepository courseRepo;
	
	@Autowired
	IMeetingRepository meetingRepo;
	
	@Autowired
	ICategoryRepository categoryRepo;

	@Autowired

	ILeaderRepository leaderRepo;

	long DAY_IN_MS = 1000 * 60 * 60 * 24;

	/**
	 * Find a course by ID
	 * 
	 * @param courseID ID
	 * @return Course
	 */	
	public Course findCourseByID(String courseID) {
		return courseRepo.findById(courseID).get();
	}
	
	/**
	 * Adds a new course, and returns all courses
	 * 
	 * @param course
	 * @return All Courses
	 */
	public List<Course> addANewCourse(Course course) {
		Category coursecategory = categoryRepo.findById(course.getCategoryId()).get();
		
			for (Course c : courseRepo.findAll()) {
				if (c.getName().equals(course.getName()))
					return courseRepo.findAll();
			
		
			courseRepo.save(course);
		}
		return courseRepo.findAll();
	}
	/**
	 * Adds a new course, and returns it
	 * 
	 * @param course
	 * @return course
	 */
	public Course addNewCourse(Course course) {
		Category coursecategory = categoryRepo.findById(course.getCategoryId()).get();
			for (Course c : courseRepo.findAll()) {
				if (c.getName().equals(course.getName()))
					return c;
			}
			courseRepo.save(course);
		return course;
	}

//	private ArrayList<String> generateCourseMeetings(Course course){
//		Date experationDate = course.getFinishDateTime();
//		Date startDate = course.getStartDateTime();
//		for (int i =0 ; startDate.before(experationDate); i++) {
//			meetingRepo.
//		}
//	}
	
	
	/**
	 * 
	 * @param courseName to get its leaders
	 * @return arraylist of course's leaders ids
	 */
	public ArrayList<String> getCourseLeadersByName(String courseName) {
		Course course = getASpecificCourseByName(courseName);
		if ( course!= null)
			return course.getLeadersIDs();
		return null;
	}

	/**
	 * Returns all Course
	 * 
	 * @return all the Course that exist
	 */
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}

	/**
	 * Returns a specific course
	 * 
	 * @param ID ID
	 * @return Course if it was found, null if it was not found
	 */
	public Course getASpecificCourse(String ID) {
		Optional<Course> course = courseRepo.findById(ID);
		if (course.isPresent())
			return course.get();
		return null;
	}

	/**
	 * Returns a course's category
	 * 
	 * @param courseID ID
	 * @return Returns course category if the course was found, null otherwise
	 */
	public Category getCourseCategory(String courseID) {
		Optional<Course> course = courseRepo.findById(courseID);
		if (course.isPresent()) {
			String categoryID = course.get().getCategoryId();
				for (Category c : categoryRepo.findAll()) {
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
		Optional<Course> course = courseRepo.findById(ID);
		if (course.isPresent())
			return course.get().getLeadersIDs();
		return null;
	}

	/**
	 * Adds a leader to a course
	 * 
	 * @param courseID ID, Leader ID
	 * @return returns true if the leader was added successfully, false otherwise.
	 */
	public Boolean addLeaderToCourse(String courseID, String leaderID) {
		Optional<Course> course = courseRepo.findById(courseID);
		if (course.isPresent()) {
			if (!(course.get().getLeadersIDs().contains(leaderID))) {
				if (course.get().getKidsIDs().add(leaderID)) {
					courseRepo.save(course.get());
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
		Optional<Course> course = courseRepo.findById(courseID);
		Optional<Leader> leader = leaderRepo.findById(leaderID);

		if(course.isPresent() && leader.isPresent())
			if((course.get().getLeadersIDs().contains(leaderID))&&
					(leader.get().getCoursesIDs().contains(courseID)))
				if(course.get().getLeadersIDs().remove(leaderID)&&
						leader.get().getCoursesIDs().remove(courseID)) {
					//leaderRepo.addANewLeader(leader.get());
					courseRepo.save(course.get());
					return true;
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
		Optional<Course> course = courseRepo.findById(ID);
		if (course.isPresent())
			return course.get().getKidsIDs();
		return null;
	}

	/**
	 * Adds a kid to a course
	 * 
	 * @param courseID ID, Kid ID
	 * @return returns true if the kid was added successfully, false otherwise.
	 */
	public boolean addKidToCourse(String courseID, String kidID) {
		Optional<Course> course = courseRepo.findById(courseID);
		if (course.isPresent()) {
			if (!(course.get().getKidsIDs().contains(kidID))) {
				if (course.get().getKidsIDs().add(kidID)) {
					courseRepo.save(course.get());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes kid from course
	 * 
	 * @param courseID ID, Kid ID
	 * @return returns true if the kid was removed successfully , false otherwise.
	 */
	public boolean removeKidFromCourse(String courseID, String kidID) {
		Optional<Course> course = courseRepo.findById(courseID);
		if (course.isPresent()) {
			if (course.get().getKidsIDs().contains(kidID)) {
				if (course.get().getKidsIDs().remove(kidID)) {
					courseRepo.save(course.get());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Updates a course's status
	 * 
	 * @param courseID ID and a status
	 * @return returns true if the course's status was updated, false otherwise.
	 */
	public boolean updateCourseStatus(String courseID, Status status) {
		Optional<Course> course = courseRepo.findById(courseID);
		if (course.isPresent()) {
			course.get().setStatus(status);
			courseRepo.save(course.get());
			return true;
		}
		return false;
	}

	/**
	 * Updating a course status after deleting, then saving the delete time
	 * 
	 * @param courseID ID
	 * @return returns true when the cSourse's finish date and status are updated
	 */
	public boolean updateFinishedDateByDelete(String courseID) {
		Optional<Course> course = courseRepo.findById(courseID);
		if (course.isPresent()) {
			Date currentDate = new Date();
			course.get().setFinishDateTime(currentDate);
			course.get().setStatus(Status.InActive);
			courseRepo.save(course.get());
			return true;
		}
		return false;
	}

	/**
	 * Returning a category's Course
	 * 
	 * @param categoryID ID
	 * @return List of Course that belong to the given category
	 */
	public ArrayList<Course> getCategoryCourses(String categoryID) {
		ArrayList<Course> categoryCourses = new ArrayList<Course>();
		for (Course c : courseRepo.findAll()) {
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
	 * @param courseName name
	 * @return Course
	 */
	public Course getASpecificCourseByName(String courseName) {

		for ( Course c :getAllCourses())
			if(c.getName().equals(courseName)) 
				return c;
		return null;
	}

}