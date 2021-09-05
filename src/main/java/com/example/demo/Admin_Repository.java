package com.example.demo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.demo.backend1front.User;

@Repository
public class Admin_Repository {
	
	@Autowired  
	IAdminRepository adminRepo; 


	
	/**
	 * Create new Admin
	 * @param admin
	 * @return new Admin or null if the email already exists 
	 */
	
	public void setActiveDate(String AdminId,Date newActiveDate) {
		Optional<Admin> optional = adminRepo.findById(AdminId);
		if(optional.isPresent()) {
			System.out.println("Kid Found, now setting course to his studies.");
			Admin admin = optional.get();
			admin.setActiveDate(newActiveDate);
			adminRepo.save(admin);
		}
	}
	
	public Admin addNewAdmin (Admin admin){
		admin.setStatus(Status.Active);
		adminRepo.save(admin); 
		 new ResponseEntity<>("New admin added", HttpStatus.OK);
		 return admin;
	}
	
	/**
	 * Delete Admin - changes the status to not active
	 * @param id
	 * @return List of all Admin
	 */	
	public List <Admin> deleteAdmin (String id){
		Optional<Admin> p = adminRepo.findById(id);
		if (p.isPresent()) {
			p.get().setStatus(Status.InActive);
			adminRepo.save(p.get());			
	}
		return getAllActiveadmins();
	}
	
	/**
	 * @return List of all active admin 
	 */	
	
	public List <Admin> getAllActiveadmins (){
		List <Admin> lstAdmin = new ArrayList<>();
		for (Admin p : adminRepo.findAll()) {
			if (p.getStatus().equals(Status.Active))
				lstAdmin.add(p);
		}
		return lstAdmin;
	}
	
	
	public Admin getSpecificAdmin (String email, String password) {
		Admin admin = findUserByEmail(email);
		if (admin != null) {
			if (admin.getPassword().equals(password))
				return admin; 
		}
		return null; 
	}
	
	
	/**
	 * used in the login - get parent with the given email and password
	 * @param email and password
	 * @return the admin if found or null
	 */	
	
	public boolean checkAdmin (User user) {
		Admin admin = findUserByEmail(user.getUserName());
		if (admin != null) {
			if (admin.getPassword().equals(user.getPassword()))
				return true; 
			new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
		}
		else
		new ResponseEntity<>("Email not found", HttpStatus.NOT_ACCEPTABLE);
		return false; 
	}
	
	/**
	 * Find user
	 * @param email
	 * @return admin if found or null
	 */	
	
	private Admin findUserByEmail(String email) {
		for (Admin p : adminRepo.findAll()) {
			if (p.getEmail().equals(email))
				return p; 
		}
		return null; 
	}
	
	
	/**
	 * a function to return the admin of the speciefied email.
	 * @param email
	 * @return Admin
	 */
	public Admin getAdminByEmail(String email) {
		List<Admin> admins = adminRepo.findAll();
		for(Admin a : admins) {
			if(a.getEmail().equals(email)) {
				return a;
			}
		}
		return null;
	}

}
