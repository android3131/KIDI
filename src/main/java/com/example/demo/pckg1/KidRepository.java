package com.example.demo.pckg1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


@Repository
public class KidRepository {


	
@Autowired
IkidRepository kidRepo;
@Autowired
CourseRepository courseRepo;



/**
 * 
 * @return list of all kids
 */
public List<Kid> retrieveAllKids(){
	return kidRepo.findAll();
}

/**
 * 
 * @param kid a new Kid to Add
 * @return kid got added, else returns null
 */
public Kid addNewKid(Kid kid) {
	kid.setStatus(Status.Active);
	kidRepo.save(kid);
	return kid;
}

/**
 * 
 * @param kid to add to the kids list.
 * @return a list of all kids
 */
public List<Kid> addKid(Kid kid){
	kid.setStatus(Status.Active);
	kidRepo.save(kid);
	return kidRepo.findAll();
}
/**
 * 
 * @param id to find the kid with
 * @return the kid if present, null otherwise.
 */
public Kid getKidWithId(String id) {
	
	Optional<Kid> optional = kidRepo.findById(id);
	if(optional.isPresent()) {
		System.out.println("KID IS PRESENT");
		return optional.get();
	}
	System.out.println("KID ISNT PRESENT");
	return null;
}

/**
 * 
 * @param kidId to get it's parent's ID
 * @return Parent's Id if found, else returns null.
 */
public String getParentId(String kidId) {
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		System.out.println("Found kid, returning parent's ID");
		return optional.get().getParentId();
	}
	System.out.println("Couldn't find kid, can't get parent's ID");
	return null;
}

/**
 * 
 * @param kidId for kid to set new image
 * @param pathToImage of the image to add.
 * @return kid with the image 
 */
public Kid addProfilePicture(String kidId, String pathToImage) {
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		System.out.println("Found, setting his image.");
		Kid kid = optional.get();
		kid.setImage(pathToImage);
		kidRepo.save(kid);
		return kid;
	}
	System.out.println("kid not Found, Couldn't set the Image");
	return null;
}
/**
 * 
 * @param kidId to add Course to his active courses
 * @param courseId to add to kids active courses list
 * @return kid object with course added to active courses.
 */
public Kid addCourseToKid(String kidId, String courseId) {
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		System.out.println("Kid Found, now setting course to his studies.");
		Kid kid = optional.get();
		kid.addCourse(courseId);
		kidRepo.save(kid);
		courseRepo.addKidToCourse(courseId, kidId);
		return kid;
	}
	return null;
}


/**
 * 
 * @param kidId kid to remove course from	
 * @param courseId of course that finished/cancelled
 * @return
 */
public Kid removeCourseFromKid( String kidId, String courseId) {
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		Kid kid = optional.get();
		kid.deleteCourse(courseId);
		kidRepo.save(kid);
		courseRepo.removeKidFromCourse(courseId, kidId);
		return optional.get();
	}
	return null;
}

/**
 * 
 * @param kidId to move Course from active to completed courses
 * @param courseId to move from active to completed for kid.
 * @return list of kid's Completed Courses.
 */
public ArrayList<String> addCourseToCompleteCourses(String kidId, String courseId ) {
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		System.out.println("Kid Found, course completed now to be moved from active to completed courses.");
		Kid kid = optional.get();
		ArrayList<String> activeCourses = kid.getActiveCourses();
		ArrayList<String> completedCourses = kid.getCompletedCourses();
		if(activeCourses.contains(courseId)) {
			System.out.println("Course is in kid's active Courses.");
			activeCourses.remove(courseId);
			if(completedCourses.contains(courseId)) {
				System.out.println("Course is already completed.");
			}else {
				completedCourses.add(courseId);
				System.out.println("Done, Moved From Active to Completed Course.");
				kidRepo.save(kid);
				return completedCourses;
			}
		}
	}
	System.out.println("Kid Couldn't Be Found.");
	return null;
}

/***
 * 
 * @param kidId to get kid's coures
 * @return arrayList of all kid's courses
 */
public ArrayList<String> getTotalCourses(String kidId){
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		System.out.println("Found kid with ID, courses to return");
		Kid kid = optional.get();
		ArrayList<String> toReturn = new ArrayList<String>();
		toReturn.addAll(kid.getActiveCourses());
		toReturn.addAll(kid.getCompletedCourses());
		return toReturn;
	}
	System.out.println("Kid Not found. no courses to return");
	return null;
}

/**
 * method that changes the status of a kid from active to inactive.
 * @param kidId of a kid that has finished his studies with kidi.
 * @return true if changed, false otherwise.
 */
public boolean deleteKid(String kidId) {
	Optional<Kid> optional = kidRepo.findById(kidId);
	if( optional.isPresent()) {
		Kid kid = optional.get();
		if(kid.getStatus().equals(Status.Active)) {
			kid.setStatus(Status.InActive);
			kidRepo.save(kid);
			System.out.println("Status changed to Inactive");
			return true;
		}else {
			System.out.println("Status already inactive.");
			return false;
		}
	}
	System.out.println("Kid id not found.");
	return false;
}


/***
 * 
 * @param kidId to get active courses of
 * @return list of course's IDs
 */
public ArrayList<Course> getKidActiveCourses( String kidId){
	Optional<Kid> optional = kidRepo.findById(kidId);
	ArrayList<String> coursesIds = new ArrayList<String>();
	if(optional.isPresent()) {
		Kid kid = optional.get();
		System.out.println("Found, Returning active Courses of" + kidId);
		coursesIds.addAll(kid.getActiveCourses());
		ArrayList<Course> courses = new ArrayList<Course>();
		if(coursesIds == null) {
			System.out.println("No courses for kid");
		}else {
			for (String s : coursesIds) {
				courses.add(courseRepo.getASpecificCourse(s));
			}
		}
		return courses;
	}
	System.out.println("Couldn't Find A KId With ID: "+ kidId);
	return null;
}

/***
 * 
 * @param kidId to get active courses of
 * @return list of course's IDs
 */
public ArrayList<String> getKidActiveCoursesIds(String kidId){
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		Kid kid = optional.get();
		System.out.println("Found, Returning active Courses of" + kidId);
		return kid.getActiveCourses();
	}
	System.out.println("Couldn't Find A KId With ID: "+ kidId);
	return null;
}


/***
 * 
 * @param kidId to get completed courses of
 * @return list of course's IDs
 */
public ArrayList<String> getKidCompletedCourses(String kidId){
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		Kid kid = optional.get();
		System.out.println("Found, Returning Completed Courses of" + kidId);
		return kid.getCompletedCourses();
	}
	System.out.println("Couldn't Find A KId With ID: "+ kidId);
	return null;
}

/**
 *  a method that returns a list of all kids.
 * @return list of kids
 */
public ArrayList<Kid> getAllKids(){
	return (ArrayList<Kid>) kidRepo.findAll();
}
/**
 *  a method to get all new kids, new kid is considered a kid that has the active date
 *   is within the last month aka joined kidi a month ago or less.
 * @return list of kids
 */
public ArrayList<Kid> getNewKids(){
	LocalDate monthAgo = LocalDate.now().minusMonths(1);
	List<Kid> kids = kidRepo.findAll();
	ArrayList<Kid> newKids = new ArrayList<Kid>();
	if(kids.size()<1) {
		System.out.println("No KIDS IN DATABASE MAN!!!");
		return null;
	}
	Date current = new Date();
	for( Kid k : kids) {
		long difference_In_Time = current.getTime() - k.getActiveDate().getTime();
		long difference_In_Days = (difference_In_Time/ (1000 * 60 * 60 * 24))% 365;
		if(difference_In_Days <=30) {
			newKids.add(k);
		}
	}
	System.out.println("Returned list of new kids.");
	return newKids;
}

/**
 *  a method that returns a list of kids and takes their specific ids as input.
 * @param idList a list of kid's ids to get.
 * @return list of kids
 */
public ArrayList<Kid> getKids(ArrayList<String> idList){
	ArrayList<Kid> kids = new ArrayList<Kid>();
	for(String id : idList) {
		Optional<Kid> optional = kidRepo.findById(id);
		if(!kids.contains(optional.get())) {
			kids.add(optional.get());
		}
	}
	return kids;
}



/**
 * get all the courses that the kid is not currently participate in(active courses), for a specific category    
 * @param id of parent, id of kid , id of category
 * @return list of all courses in category that the kid is registered to(not active courses)
 */
public List<Course> getKidNotRegisteredCoursesByCategory( String kidId, String catId){
	Optional<Kid> kid = kidRepo.findById(kidId);
	if (kid.isPresent()) {
		List<Course> availibleCourses = courseRepo.categoryCourse(catId);
		availibleCourses.removeAll(getKidActiveCourses( kidId));
		return availibleCourses; }
	return null; 
}
}
