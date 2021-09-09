package com.example.demo.backend1;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Admin;
import com.example.demo.Admin_Repository;
import com.example.demo.Leader;
import com.example.demo.LeaderRepository;
import com.example.demo.Parent;
import com.example.demo.Parent_repository;
import com.example.demo.backend1front.User;

@RestController
public class LogInController {
	
	@Autowired
	Parent_repository parentRepo;
	@Autowired
	Admin_Repository adminRepo;
	@Autowired
	LeaderRepository leaderRepo;
	
	@PatchMapping("/updatepass")
	public void updatePassword(@RequestBody User user) {
		System.out.print(user.getId() + "   " + user.getPassword());
		leaderRepo.changePassword(user.getId(),user.getPassword());
		new ResponseEntity<>("success",HttpStatus.OK);
	}


	
	@PostMapping("/init")
	public void init() {
		Parent newParent = new Parent("Parent", "000", "Parent", "Parent");
		Admin newAdmin = new Admin("Admin","Admin", "Admin");
		Leader newLeader = new Leader("Leader","Leader");
		Leader newLeader2 = new Leader("mo","mo","mo");
		parentRepo.addNewParent(newParent);
		adminRepo.addNewAdmin(newAdmin);
		leaderRepo.addANewLeader(newLeader);
		leaderRepo.addANewLeader(newLeader2);
		return;
		
	}
	
	@PostMapping("/checkuserpass")
	public HashMap<String,String> checkUsernameCredentials(@RequestBody User user) {
		String id = "meoooooooooooooow";
		HashMap<String,String> hash = new HashMap<String,String>();
		System.out.println(user.getUserName() + "  " +user.getPassword());
		if(parentRepo.getSpecificParent(user.getUserName(),user.getPassword())!=null) {
			id = (parentRepo.getSpecificParent(user.getUserName(),user.getPassword())).getId();
			System.out.print(id);
			hash.put("ID", id);
			hash.put("flag","4" );
			return hash;
		}
		if(adminRepo.getSpecificAdmin(user.getUserName(),user.getPassword())!=null) {
			id = (adminRepo.getSpecificAdmin(user.getUserName(),user.getPassword())).getId();
			System.out.print(id);
			hash.put("ID", id);
			hash.put("flag","1" );
			return hash;
		}
		if(leaderRepo.getSpecificLeader(user.getUserName(),user.getPassword())!=null) {
			if((leaderRepo.getSpecificLeader(user.getUserName(),user.getPassword()).getGeneratedPassword())) {	//generated password is true -> in android change password to continue
				id = (leaderRepo.getSpecificLeader(user.getUserName(),user.getPassword())).getId();
				System.out.print(id);
				hash.put("ID", id);
				hash.put("flag","2" );
				return hash;
			}
			else {
				id = (leaderRepo.getSpecificLeader(user.getUserName(),user.getPassword())).getId();
				System.out.print(id);
				hash.put("ID", id);
				hash.put("flag","3" );
				return hash;
			}
		}
		hash.put("ID", "");
		hash.put("flag","0" );
		return hash;
	}

}
