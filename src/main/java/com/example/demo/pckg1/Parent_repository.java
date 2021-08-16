package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


@Repository
public class Parent_repository {
	@Autowired  
	IParentRepository parentRepo;  
	@Autowired
	KidRepository kidRepo; 
	@Autowired
	CourseRepository courseRepo;
	
	/**
	 * Create new Parent
	 * @param Parent
	 * @return new Parent or null if the email already exists 
	 */
	public Parent addNewParent (Parent parent){
		Parent p = findUserByEmail (parent.getEmail());
		if (p != null) {
			if (!p.getActive().equals(Status.InActive)) {
				new ResponseEntity<>("Faild to add new parent, The email already exist in the system", 
						HttpStatus.NOT_ACCEPTABLE);
				return null;
			}
			else {
				p.setActive(Status.Active);
				parentRepo.save(p);
				 new ResponseEntity<>("New parent aded", HttpStatus.OK);
				 return p; 
			}
		}
		parentRepo.save(parent); 
		 new ResponseEntity<>("New parent aded", HttpStatus.OK);
		 return parent;
	}
	/**
	 * Delete Parent - changes the status to not active
	 * @param Parent
	 * @return List of all parents
	 */	
	public List <Parent> deleteParent (String id){
		Optional<Parent> p = parentRepo.findById(id);
		if (p.isPresent()) {
			p.get().setActive(Status.InActive);
			parentRepo.save(p.get());	
			for (String idKid : p.get().getKids()){
				kidRepo.deleteKid (idKid);
			}
			}	
				
		return getAllActiveparents();		
		
	}
	
	/**
	 * @return List of all active parents 
	 */	
	
	public List <Parent> getAllActiveparents (){
		List <Parent> lstParent = new ArrayList<>();
		for (Parent p : parentRepo.findAll()) {
			if (p.getActive().equals(Status.Active))
				lstParent.add(p);
		}
		return lstParent;
	}
	/**
	 * @return List of all parents 
	 */	
	
	public List <Parent> getAllParents (){
		return parentRepo.findAll();
	}
	
	/**
	 * used in the login - get parent with the given email and password
	 * @param email and password
	 * @return the parent if found or null
	 */	
	
	public Parent getSpecificParent (String email, String password) {
		Parent parent = findUserByEmail(email);
		if (parent != null) {
			if (parent.getPassword().equals(password))
				return parent; 
			new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
		}
		else
		new ResponseEntity<>("Email not found", HttpStatus.NOT_ACCEPTABLE);
		return null; 
	}
	
	/**
	 * Change email of existent parent
	 * @param email of parent and the new email
	 * @return the parent if found or null 
	 */	
	
	public Parent changeEmail (String id, String newEmail) {
		Optional<Parent> parent = parentRepo.findById(id);
		if (parent.isPresent() ) {
			parent.get().setEmail(newEmail);
			parentRepo.save(parent.get());
			}
		return parent.get(); 
		}
	
	/**
	 * Add new kid
	 * @param kid
	 * @return the parent if found with the new kid or null
	 */	
	public Parent addNewKid (String id, Kid kid) {
		Optional<Parent> parent = parentRepo.findById(id);
	if (parent.isPresent()) {
			kid.setParentId (parent.get().getId());
			Kid k = kidRepo.addNewKid(kid);
			parent.get().addKid(k.getId());
			parentRepo.save(parent.get());
		}
		else
			new ResponseEntity<>("Parent not found", HttpStatus.NOT_ACCEPTABLE);
		return parent.get(); 
		
	}
	
	/**
	 * get all kids of specific parent
	 * @param email
	 * @return List of all kids of the parent if found or null
	 */	
	public List<Kid> GetAllKidsOfParent (String id) {
		Optional<Parent> parent = parentRepo.findById(id);
		if (parent.isPresent()) {
			 List <Kid> kids = new ArrayList<>(); 
			for (String idKid : parent.get().getKids())
				kids.add (kidRepo.getKidWithId(idKid));
			return kids; 
		
		}
		return null; 
	}
	
	/**
	 * get kid 
	 * @param id of parent, id of kid
	 * @return the kid if found or null
	 */

	public Kid getSpcificKid (String parentId, String kidId) {
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			return kidRepo.getKidWithId(kidId);
		}
		return null; 
	}
	
	/**
	 * Change kid’s picture
	 * @param Id of parent, Id of kid, new picture
	 * @return the kid with the new picture if found or null 
	 */	
	public Kid addProfilePicture (String parentId, String kidId, String picture) {
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			kidRepo.addProfilePicture(kidId, picture);
		}
		return null; 	
	}
	
	/**
	 * Register kid to course  
	 * @param id of parent, id of kid, id of course 
	 * @return the kid if found or null
	 */	
	public Kid addKidToCourse (String parentId, String kidId, String courseId) {
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			 Kid kid = kidRepo.addCourseToKid(kidId, courseId); 
			 return kid; 
		}
		return null; 
	}

	/**
	 * remove kid from course  
	 * @param id of parent, id of kid, id of course 
	 * @return the kid if found or null
	 */	
	public Kid removeKidFromCourse (String parentId, String kidId, String courseId) {
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			
			/*
			 Kid kid = kidRepo.re (kidId, courseId); 
			 return kid; 
			 */
		}
		return null; 
	}
	/**
	 * Delete kid – changes the status to not active   
	 * @param id of parent, id of kid 
	 * @return the parent of the kid if found or null
	 */	
	public Parent deleteKid (String parentId, String kidId) {
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			kidRepo.deleteKid(kidId);
		}
		return parent.get(); 
	}
	
	
	/***
	 * 
	 * @param kidId to get active courses of
	 * @return list of course's IDs
	 */
	public ArrayList<String> getKidActiveCourses(String kidId){
		Optional<Kid> optional = Optional.of(kidRepo.getKidWithId(kidId));
		if(optional.isPresent()) {
			Kid kid = optional.get();
			System.out.println("Found, Returning active Courses of" + kidId);
			return kid.getActiveCourses();
		}
		System.out.println("Couldn't Find A KId With ID: "+ kidId);
		return null;
	}
	
	/**
	 * get all active (future) courses of kid   
	 * @param id of parent, id of kid 
	 * @return list of all active courses of kid or null if not found 
	 */	
	public List<Course> getKidActiveCourses (String parentId, String kidId){
	 	Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			List <Course> lstCourse = kidRepo.getKidActiveCourses(kidId); 
			return lstCourse; 
	}
		return null;
	}
	/**
	 * get all completed courses of kid   
	 * @param id of parent, id of kid 
	 * @return list of all completed courses of kid or null if not found 
	 */	
	 public List<String> getKidCompletedCourses (String parentId, String kidId){
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			List <String> lstCourse = kidRepo.getKidCompletedCourses(kidId); 
			return lstCourse; 
	}
		return null; 
	}

	/**
	 * Find user
	 * @param email
	 * @return parent if found or null
	 */	
	
	private Parent findUserByEmail(String email) {
		for (Parent p : parentRepo.findAll()) {
			if (p.getEmail().equals(email))
				return p; 
		}
		return null; 
	}
	
	/**
	 * get all the courses that the kid is not currently participate in(active courses), for a specific category    
	 * @param id of parent, id of kid , id of category
	 * @return list of all courses in category that the kid is registered to(not active courses)
	 */
	public List<Course> getKidNotRegisteredCoursesByCategory(String parentId, String kidId, String catId){
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
		return kidRepo.getKidNotRegisteredCoursesByCategory(kidId, catId);
		}
		return null; 
	}

	

}
