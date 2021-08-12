package com.example.demo.pckg1;


import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IkidRepository extends MongoRepository<Kid, String>{
/*
	public Kid getKidById(String id); 
	public Kid addNewKid(Kid kid);  
	public String getParentId(String kidId); 
	public Kid addProfilePicture(String kidId,String pathToImage); 
	public String addCourseToKid(String kidId, String courseId); // returns course's id, make sure that course repository also has one like this.
	public String addCourseToCompleteCourses(String kidId, String courseId ); //returns kid's id
	public ArrayList<String> getTotalCourses(String kidId);
	public String removeCourse(String kidId, String courseId);
	public Kid inactivateKid(String id);

	public ArrayList<Kid> addKid(Kid kid);
	public ArrayList<String> getKidActiveCourses(String kidId); // returns Ids of kid's Active Courses
	public ArrayList<String> getKidCompletedCourses(String kidId); // returns Ids of kid's completed Courses
	public ArrayList<Kid> getAllKids();
	public ArrayList<Kid> getNewKids(); // kids in last month or time period
	public ArrayList<Kid> getKids(ArrayList<String> idList);
*/
}
