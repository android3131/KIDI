package com.example.demo.pckg1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class KidiController {
	
	@Autowired
	Parent_repository repoParent; 
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	KidRepository kidRepo;
	
	
	@PostMapping("/addNewParent")
	public Parent addNewParent (@RequestBody Parent parent){
		return repoParent.addNewParent(parent);
	}
	
	@GetMapping("/getAllUsers")
	public List <Parent> getAllparents (){
		return  repoParent.getAllParents();
	}
	
	@GetMapping ("/getAllActiveUsers")
	public List<Parent> getAllActiveParents(){
		return repoParent.getAllActiveparents();
	}
	@GetMapping("/getSpecificParent/{email}/{password}")
	public Parent getSpecificParent (@PathVariable String email,@PathVariable String password) {
		return repoParent.getSpecificParent(email, password);
	}
	
/*	@PutMapping("/addNewKid/{email}")
	public Parent addNewKid (@PathVariable String email,@RequestBody Kid kid) {
		return repoParent.addNewKid(email, kid);
		
	}*/
	@GetMapping ("/GetAllKidsOfParent/{id}")
	public List<Kid> GetAllKidsOfParent (@PathVariable String id) {
		return repoParent.GetAllKidsOfParent(id);
	}
	@PutMapping("/changeEmail/{id}/{newEmail}")
	public Parent changeEmail (@PathVariable String id,@PathVariable String newEmail) {
		return repoParent.changeEmail(id, newEmail);
	}
	@DeleteMapping ("/deleteParent/{id}")
	public List<Parent> deleteParent (@PathVariable String id){
		return repoParent.deleteParent(id);
	}
	
	//----------------- KID -----------------
	@PostMapping("/initiateRepository")
	public ResponseEntity<String> intiate() {
		//if(kidRepo.getAllKids().isEmpty()) {
			kidRepo.addNewKid(new Kid("111111", new Date(), Gender.Boy));
			kidRepo.addNewKid(new Kid("222222", new Date(), Gender.Girl));
			kidRepo.addNewKid(new Kid("444444444", new Date(), Gender.Girl));
			kidRepo.addNewKid(new Kid("555555555", new Date(), Gender.Boy));
		//}
		return new ResponseEntity<>("initiateRepository", HttpStatus.OK);
	}
	
	@PostMapping("/AddNewKid")
	public Kid addNewKid(@RequestBody Kid kid) {
		return kidRepo.addNewKid(kid);
	}
	
	@GetMapping("/getAllKids")
	public ArrayList<Kid> getAllKids(){
		return kidRepo.getAllKids();
	}
	
	
	
	
	
	
	
	//------------------------------- COURSE ----------------------------------------
	@PostMapping("addNewCourse")
	public boolean addArbitraryCourse() {
//		Course course = new Course("course", 25,new Date(), LocalDateTime.now(),new Category("Space", "image"), "zoom");
//		return courseRepo.addANewCourse(course);
		return true;
	}

	
	
	
	
	
}