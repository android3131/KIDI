package com.example.demo;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	/**
	 * Create new Parent
	 * @param parent
	 * @return new Parent or null if the email already exists 
	 */
	
	public Parent addNewParent (Parent parent){
		List<Parent> parents = parentRepo.findAll();
		for(Parent p : parents) {
			if(p.getEmail().equals(parent.getEmail()) && p.getStatus().equals(Status.Active)){
				new ResponseEntity<>("Parent Already Registered", HttpStatus.NOT_ACCEPTABLE);
				return null;
			}
		}
		parent.setStatus(Status.Active);
		parentRepo.save(parent); 
		 new ResponseEntity<>("New parent added", HttpStatus.OK);
		 return parent;
	}
	/**
	 * Delete Parent - changes the status to not active
	 * @param id
	 * @return List of all parents
	 */	
	public List <Parent> deleteParent (String id){
		Optional<Parent> p = parentRepo.findById(id);
		if (p.isPresent()) {
			p.get().setStatus(Status.InActive);
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
			if (p.getStatus().equals(Status.Active))
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
	
	
	public Parent getParentById(String id) {
		Optional<Parent> optional = parentRepo.findById(id);
		if(optional.isPresent()) {
			System.out.println("KID IS PRESENT");
			return optional.get();
		}
		System.out.println("KID ISNT PRESENT");
		return null;
	}
	
	/**
	 * Change email of existent parent
	 * @param id of parent and the new Email
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
	 * @param id
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
	 * Change info of existent parent
	 * @param id of parent, new mail, new full name and new phone
	 * @return the parent if found or null 
	 */	
	public Parent changeInfo (String id, String newEmail, String newName, String newPhone) {
		Optional<Parent> parent = parentRepo.findById(id);
		if (parent.isPresent() ) {
			parent.get().setEmail(newEmail);
			parent.get().setFullName(newName);
			parent.get().setPhoneNumber(newPhone);
			parentRepo.save(parent.get());
			}
		return parent.get(); 
		}

	/**
	 * get kid 
	 * @param parentId of parent, kidId of kid
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
	 * Change kid�s picture
	 * @param parentId of parent, Id of kid, new picture
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
	 * @param parentId of parent, id of kid, id of course
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
	 * @param parentId of parent, id of kid, id of course
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
	 * Delete kid � changes the status to not active   
	 * @param parentId of parent, id of kid
	 * @return the parent of the kid if found or null
	 */	
	public Parent deleteKid (String parentId, String kidId) {
		Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			kidRepo.deleteKid(kidId);
		}
		return parent.get(); 
	}
	
	/**
	 * get all active (future) courses of kid   
	 * @param parentId of parent, id of kid
	 * @return list of all active courses of kid or null if not found 
	 */	
	public ArrayList<Course> getKidActiveCourses (String parentId, String kidId){
	 	Optional<Parent> parent = parentRepo.findById(parentId);
		if (parent.isPresent()) {
			ArrayList<Course> lstCourse = kidRepo.getKidActiveCourses(kidId); 
			return lstCourse; 
	}
		return null; 

	}
	/**
	 * get all completed courses of kid   
	 * @param parentId of parent, id of kid
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
	/**
     * get all the categories that the kid is not currently participate in(active courses)    
     * @param id of parent, id of kid , id of category
     *  @return list of all categories that the kid is registered to(not active courses)
     */
    public List<Category> getKidNotRegisteredCategories(String parentId, String kidId){
        Optional<Parent> parent = parentRepo.findById(parentId);
        if (parent.isPresent()) {
            return kidRepo.getKidNotRegisteredCategories(kidId);
        }
        return null; 
    }
	
	/**
	 * 
	 * @param period Input: 1- For week 2- For month 3- For year.
	 * @return hashMap : with two keys: "New Parents": new parents Count, "totalParents": total Parents count, null otherwise 
	 */
	public HashMap<String, Integer> getNewParents(int period){
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
		List<Parent> parents = parentRepo.findAll();
		if(parents.size()<1) {
			System.out.println("No PARENTS IN DATABASE MAN!!!");
			return null;
		}
		int parentsCount = 0;
		int totalParents = 0;
		for( Parent p : parents) {
			if(p.getStatus().equals(Status.Active)) {
				totalParents ++;
				if(p.getActiveDate().after(d)) {
					parentsCount++;
				}
			}
		}
	HashMap<String, Integer> toReturn = new HashMap<String, Integer>();
	toReturn.put("newParents", parentsCount);
	toReturn.put("totalParents",totalParents-parentsCount );
		return toReturn;
	}
	
	
	public boolean getSpecificParentbyusername (String email) {
		Parent parent = findUserByEmail(email);
		if (parent != null) {
				return true; 
		}
		else
		new ResponseEntity<>("Email not found", HttpStatus.NOT_ACCEPTABLE);
		return false; 
	}



}
