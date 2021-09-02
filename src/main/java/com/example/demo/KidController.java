package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class KidController {
	
	
	@Autowired
	KidRepository kidRepo;
	
	@PostMapping("/api/kids/addNewKid")
	public Kid addNewKid (@RequestBody Kid kid){
		return kidRepo.addNewKid(kid);
	}
	
	
	

}
