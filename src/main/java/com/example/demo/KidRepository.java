package com.example.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
@Repository
public class KidRepository {
@Autowired
MeetingRepository meetingRepo;
@Autowired
IkidRepository kidRepo;
@Autowired
CourseRepository courseRepo;
@Autowired
CategoryRepository categoryRepo;
long DAY_IN_MS = 1000 * 60 * 60 * 24;
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
	List<Kid> kids = kidRepo.findAll();
	for(Kid k : kids) {
		if(k.getParentId().equals(kid.getParentId()) && k.getFullName().equals(kid.getFullName()) && k.getStatus().equals(Status.Active)){
			new ResponseEntity<>("Kid Already Registered", HttpStatus.NOT_ACCEPTABLE);
			return null;
		}
	}
	kid.setStatus(Status.Active);
	kid.setActiveDate(new Date());
	kidRepo.save(kid);
	return kid;
}
/**
 *
 * @param kid to add to the kids list.
 * @return a list of all kids
 */
public List<Kid> addKid(Kid kid){
	List<Kid> kids = kidRepo.findAll();
	for(Kid k : kids) {
		if(k.getParentId().equals(kid.getParentId()) && k.getFullName().equals(kid.getFullName())){
			new ResponseEntity<>("Kid Already Registered", HttpStatus.NOT_ACCEPTABLE);
			return null;
		}
	}
	kid.setStatus(Status.Active);
	kid.setActiveDate(new Date());
	kidRepo.save(kid);
	return kidRepo.findAll();
}
/**
 * the repository turns empty
 */
public void clearAllDocuments() {
	kidRepo.deleteAll();
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
		for(Meeting meet: meetingRepo.getAllMeetings()) {
			if(meet.getCourseId().equals(courseId)) {
				kid.addMeeting(meet.getId());
				//addMeetingToKid(kidId,meet.getId());
			}
		}
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
		ArrayList<String> activeCourses = (ArrayList<String>) kid.getActiveCourses();
		ArrayList<String> completedCourses = (ArrayList<String>) kid.getCompletedCourses();
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
			for(String course : optional.get().getActiveCourses()) {
				removeCourseFromKid(kidId, course);
			}
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
		return (ArrayList<String>) kid.getActiveCourses();
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
		return (ArrayList<String>) kid.getCompletedCourses();
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
 *   @param period is the type of period you want to get new kids: 1- For week 2- For month 3- For year.
 * @return list of kids
 */
public HashMap<String, Integer> getNewKids(int period){
	if(period != 1 && period !=2 && period !=3) {
		new ResponseEntity<>("Input: 1- For week 2- For month 3- For year.", HttpStatus.NOT_ACCEPTABLE);
		return null;
	}
	Date d;
	if(period == 1) {
		d = new Date((new Date()).getTime()- 7*DAY_IN_MS);
	}else if(period == 2) {
		d = new Date((new Date()).getTime()-35*DAY_IN_MS);
	}
	else {
		d = new Date((new Date()).getTime()- 365*DAY_IN_MS);
	}
	List<Kid> kids = kidRepo.findAll();
	if(kids.size()<1) {
		System.out.println("No KIDS IN DATABASE MAN!!!");
		return null;
	}
	int kidsCount = 0;
	int totalKids = 0;
	for( Kid k : kids) {
		if(  k.getStatus().equals(Status.Active)) {
			totalKids++;
			if(k.getActiveDate().after(d)) {
				kidsCount++;
			}
		}
	}
HashMap<String, Integer> toReturn = new HashMap<String, Integer>();
toReturn.put("newKids", kidsCount);
toReturn.put("totalKids",totalKids-kidsCount );
	return toReturn;
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
 * get all the categories that the kid is not currently participate in(active courses)
 * @param  id of kid
 * @return list of all categories that the kid is registered to(not active courses)
 */
public List<Category> getKidNotRegisteredCategories( String kidId){
    List<Category> cats = new ArrayList<Category>();
    Optional<Kid> kid = kidRepo.findById(kidId);
    if (kid.isPresent()) {
        for (Category cat: categoryRepo.getAllCategories()) {
            if (getKidNotRegisteredCoursesByCategory(kidId, cat.getId()).isEmpty() == false) {
                cats.add(cat);
            }
        }
    }
    return cats;
}
public TreeMap<Integer,Integer> getKidsCategoryMonth(String categType){
	HashMap<Integer, Integer> kidsCountByCategoryMonth = new HashMap<Integer,Integer>();
	for(int i=0;i<12;i++) {
		kidsCountByCategoryMonth.put(i, 0);
	}
	List<Kid> allKids = kidRepo.findAll();
	for(int i=0;i<60;i++){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(allKids.get(i).getActiveDate());
		int num_kids = kidsCountByCategoryMonth.get(calendar.get(Calendar.MONTH));
		String coursecateg = courseRepo.getASpecificCourse(allKids.get(i).getActiveCourses().get(0)).getCategoryId();
		if(coursecateg.indexOf(categType)==0){
			num_kids++;
		}
		kidsCountByCategoryMonth.put((calendar.get(Calendar.MONTH)), num_kids);			
	}
	
    TreeMap<Integer, Integer> sorted = new TreeMap<>();
    sorted.putAll(kidsCountByCategoryMonth);
	return sorted;
}
/**
 * get all the courses that the kid is not currently participate in(active courses), for a specific category
 * @param id of parent, id of kid , id of category
 * @return list of all courses in category that the kid is registered to(not active courses)
 */
public List<Course> getKidNotRegisteredCoursesByCategory( String kidId, String catId){
	Optional<Kid> kid = kidRepo.findById(kidId);
	if (kid.isPresent()) {
		List<Course> availibleCourses = courseRepo.getCategoryCourses(catId);
		availibleCourses.removeAll(getKidActiveCourses( kidId));
		return availibleCourses; }
	return null;
}
/**
 *
 * @param kid
 * @return
 */
public List<Kid> createKid(Kid kid) {	
	kid.setActiveDate(new Date());	
	kid.setStatus(Status.Active);
	kidRepo.save(kid);	
	return kidRepo.findAll();	
}
/**
 * gets kids meetings by id
 * @param kidId
 * @return list of meetings
 */
public List<String> getMeetingsByKidId(String kidId){
	Optional<Kid> optional = kidRepo.findById(kidId);
	ArrayList<String> toReturn = new ArrayList<String>();
	Kid kid;
	if(optional.isPresent()) {
		kid = optional.get();
		ArrayList<String> meetingsId = kid.getMeetings();
		for(String meetId: meetingsId) {
			toReturn.add(meetId);
		}
		return toReturn;
	}
	return null;	
}

/**
 * delete meeting from kid
 * @param kidId
 * @param meetingId
 * @return true if there was a meeting and deleted,
 */
public boolean deleteMeetingFromKid(String kidId, String meetingId) {
	Optional<Kid> optional = kidRepo.findById(kidId);
	if(optional.isPresent()) {
		Kid kid = optional.get();
		kid.getMeetings().remove(meetingId);
		kidRepo.save(kid);
		return true;
	}
	return false;
}

}