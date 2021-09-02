package com.example.demo.backend1front;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;



@Repository
public class MyRepository {
	List<User> allUsersLst = new ArrayList<User>();
	
	
public MyRepository() {
		
		initMyRepository();
	}
	
private void initMyRepository()
{
	

}
public List<User> retrieveAllUsers() {
	return allUsersLst;
}
public User createUser(User user) {
	allUsersLst.add(user);
	return user;
}
public List<User> addUser(User newUser) {
	allUsersLst.add(newUser);
	return allUsersLst;
}
public User getUsertWithName(String name) {
	System.out.println(name);
	User user = findUserByName(name);
	return user;
}
public List<User> deleteByName(String name) {

	User user = findUserByName(name);
	allUsersLst.remove(user);
	
	return allUsersLst;
}
private User findUserByName(String name) {
	User user = allUsersLst.stream()
			  .filter(curr -> curr.getUserName().toLowerCase().equals(name.toLowerCase()))
			  .findAny()
			 // .orElse(new Student("NA",-1,"NA"));
			  .orElse(null);

	return user;
}

}
