package com.example.demo.pckg1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
	@Autowired
	CategoryRepository categoryRepo;
	
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
	@Autowired
	IkidRepository kRep;
	//----------------- KID -----------------
	@PostMapping("/initiateRepository")
	public ResponseEntity<String> intiate() {
			//if(kidRepo.getAllKids().isEmpty()) {
			/*Date date = new Date();
			Kid kid1 =new Kid("kid1", new Date(), Gender.Boy);
			kid1.setActiveDate(new Date(121,7,22));
			Kid kid2 =new Kid("kid2", new Date(), Gender.Boy);
			kid2.setActiveDate(new Date(121,7,12));
			Kid kid4 =new Kid("kid4", new Date(), Gender.Boy);
			kid4.setActiveDate(new Date(121,7,10));
			Kid kid5 =new Kid("kid5", new Date(), Gender.Boy);
			kid5.setActiveDate(new Date(121,5,12));
			Kid kid6 =new Kid("kid6", new Date(), Gender.Boy);
			kid6.setActiveDate(new Date(121,4,12));
			Kid kid7 =new Kid("kid7", new Date(), Gender.Boy);
			kid7.setActiveDate(new Date(121,6,12));
			kidRepo.addNewKid(kid1);
			kidRepo.addNewKid(kid2);
			kidRepo.addNewKid(kid4);
			kidRepo.addNewKid(kid5);
			kidRepo.addNewKid(kid6);
			kidRepo.addNewKid(kid7);*/
			Parent p1 = new Parent("p1","25a22","p1email.com", "SSSSsad");
			p1.setActiveDate(new Date(121,7,22));
			Parent p2 = new Parent("p2","252v2","p1adsemail.com", "SSSSsad");
			p2.setActiveDate(new Date(121,7,12));
			Parent p3 = new Parent("p3","252w2","p1emasdail.com", "SSSSsad");
			p3.setActiveDate(new Date(121,7,10));
			Parent p4 = new Parent("p4","25a22","p1emaiaal.com", "SSSSsad");
			p4.setActiveDate(new Date(121,5,12));
			Parent p5 = new Parent("p5","25s22","p1eddmail.com", "SSSSsad");
			p5.setActiveDate(new Date(121,4,12));
			Parent p6 = new Parent("p6","2d522","p1emaddil.com", "SSSSsad");
			p6.setActiveDate(new Date(121,6,12));
			repoParent.addNewParent(p1);
			repoParent.addNewParent(p2);
			repoParent.addNewParent(p3);
			repoParent.addNewParent(p4);
			repoParent.addNewParent(p5);
			repoParent.addNewParent(p6);
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
	
	@DeleteMapping("/clearContent")
	public void clearContent() {
		kidRepo.clearAllDocuments();
	}
	
	
	
	
	
	
	//------------------------------- COURSE ----------------------------------------
	@PostMapping("addNewCourse")
	public List<Course> addCourse(Course course) {
		return courseRepo.addANewCourse(course, course.getCategoryId());
	}
	
	
	//------------------------------- CATEGORY ----------------------------------------

	@PostMapping("addNewCategory")
	public List<Category> addNewCategory(Category category) {
		return categoryRepo.addCategory(category);
	}
	
	

	//-------------------------- FOR STATISTICS ----------------------------------------
	@GetMapping("/getNewKids/{period}")
	public HashMap<String,Integer> getNewKids(@PathVariable int period){
		return kidRepo.getNewKids(period);
	}
	@GetMapping("/getNewParents/{period}")
	public HashMap<String,Integer> getNewParents(@PathVariable int period){
		return repoParent.getNewParents(period);
	}
	
	@GetMapping("/getKidsCountByCategory/{period}")
	public HashMap<String,Integer> getKidsCountByCategory(@PathVariable int period){
		return categoryRepo.getKidsCountByCategory(period);
	}
	
	
	

	
	
	
	
}