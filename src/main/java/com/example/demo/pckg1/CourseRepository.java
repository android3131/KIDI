package com.example.demo.pckg1;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class CourseRepository {
	@Autowired
	ICourseRepository CourseRepository;
	@Autowired
	IMeetingRepository meetingRepository;
	@Autowired
	ICategoryRepository categoryRepository;

	/**
	 * 
	 * @param name
	 * @return
	 */
	private boolean findCourseByName(String name) {
		for (Course c : CourseRepository.findAll()) {
			if (c.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
   	 * Adds a new course
   	 * @param Course
   	 * @return All Course
   	 */
	public List<Course> addANewCourse(Course course, String categoryID) {
		Optional<Category> courseCategory = categoryRepository.findById(categoryID);
		if(courseCategory.isPresent()) {
			for(Course c: CourseRepository.findAll()) {
				if (c.getName().equals(course.getName()))
						return CourseRepository.findAll();
			}
			course.setCategoryId(categoryID);
			CourseRepository.save(course);
		}
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
	 * @param Course ID
	 * @return Course if it was found, null if it was not found
	 */
	public Course getASpecificCourse(String ID) {
		Optional<Course> course = CourseRepository.findById(ID);
		if (course.isPresent())
			return course.get();
		;
		new ResponseEntity<>("Course not found", HttpStatus.NOT_ACCEPTABLE);
		return null;
	}

	/**
	 * Returns a course's category
	 * 
	 * @param Course ID
	 * @return Returns course category if the course was found, null otherwise
	 */
	@SuppressWarnings("unlikely-arg-type")
	public Category getCourseCategory(String courseID) {
		Optional<Course> course = CourseRepository.findById(courseID);
		if (course.isPresent()) {
			String categoryID = course.get().getCategoryId();
			Optional<Category> courseCategory = categoryRepository.findById(categoryID);
			if (courseCategory.isPresent()) {
				for (Category c : categoryRepository.findAll()) {
					if (c.equals(courseCategory))
						return c;
				}
			}
		}
		;
		return null;
	}

	/**
	 * Returns a specific course's leader
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
	 * @return returns true if the leader was added successfully , false otherwise.
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
	 * @return returns true if the kid was added successfully , false otherwise.
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
	 * Updating a course status after deleting and saving the delete time
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
			// updateCoursetatus(courseID, Status.InActive);
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
		for (Course c : CourseRepository.findAll())
			if (c.getCategoryId().equals(categoryID)) {
				categoryCourses.add(c);
			}

		return categoryCourses;
	}

	/**
	 * 
	 * @return Monthly activity percentage: total duration of meetings devide total
	 *         time of courses in the last 28 days
	 */
	public double getMonthlyActivity() {
		int totalCoursesTime = 0;
		Date currentDate = new Date();
		for (Course c : CourseRepository.findAll()) {
			long dit = currentDate.getTime() - c.getStartDateTime().getTime();
			long did = (dit / (1000 * 60 * 60 * 24)) % 365; // difference in days
			if (did <= 28) {
				// calculate totalcourses time
				long difference_In_Time = c.getFinishDateTime().getTime() - c.getStartDateTime().getTime();
				long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
				int numberOfMeetings = (int) (difference_In_Days / 7);
				totalCoursesTime += numberOfMeetings * c.getMeetingDuration();
			}
		}
		//// now in the last 28 days get the meetings that occured
		double totalMeetingsDurations = 0;
		for (Meeting m : meetingRepository.findAll()) {
			Date meetingDate = m.getMeetingDateTime();
			long differenceInTime = currentDate.getTime() - meetingDate.getTime();
			long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
			if (differenceInDays <= 28) {
				String courseId = m.getCourseId();
				Course c = getASpecificCourse(courseId);
				totalMeetingsDurations += c.getMeetingDuration();
			}
		}
		return totalMeetingsDurations / totalCoursesTime;
	}

}