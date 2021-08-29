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
	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	@Autowired
	Parent_repository repoParent; 
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	KidRepository kidRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	MeetingRepository meetingRepo;
	
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

		/**
		 * Space 
		 * Art
		 * Animals
		 * Science
		 */

		ArrayList<String> kids = new ArrayList<String>();
		kids.add("kid1");
		kids.add("kid2");
		kids.add("kid3");
		kids.add("kid4");
		kids.add("kid5");
		kids.add("kid6");
		kids.add("kid7");
		kids.add("kid8");
		kids.add("kid9");
		kids.add("kid10");
//		ArrayList<String> categories = categoryRepo.getAllCategoriesIds();
//		String catId = "612a326989674a4e38a688a0";
//			Course course1 = new Course("course21", new Date(121,7,27),catId,kids);
//			Course course2 = new Course("course22", new Date(121,7,28),catId,kids);
//			Course course3 = new Course("course23", new Date(121,6,28),catId,kids);
//			Course course4 = new Course("course44", new Date(121,6,28),catId,kids);
//			Course course5 = new Course("course254", new Date(121,5,28),catId,kids);
//			Course course6 = new Course("course2546", new Date(121,5,28),catId,kids);
//			courseRepo.addANewCourse(course1);
//			courseRepo.addANewCourse(course2);
//			courseRepo.addANewCourse(course3);
//			courseRepo.addANewCourse(course4);
//			courseRepo.addANewCourse(course5);
//			courseRepo.addANewCourse(course6);
			
			String catId1 = "612a326989674a4e38a688a1";
			Course course11 = new Course("course21", new Date(121,7,27),catId1,kids);
			Course course21 = new Course("course22", new Date(121,7,28),catId1,kids);
			Course course31 = new Course("course23", new Date(121,6,28),catId1,kids);
			Course course42 = new Course("course44", new Date(121,6,28),catId1,kids);
			Course course52 = new Course("course254", new Date(121,5,28),catId1,kids);
			Course course62 = new Course("course2546", new Date(121,5,28),catId1,kids);
			courseRepo.addANewCourse(course11);
			courseRepo.addANewCourse(course21);
			courseRepo.addANewCourse(course31);
			courseRepo.addANewCourse(course42);
			courseRepo.addANewCourse(course52);
			courseRepo.addANewCourse(course62);
			
			String catId11 ="612a326989674a4e38a688a2";
			Course course111 = new Course("course21", new Date(121,7,27),catId11,kids);
			Course course211 = new Course("course22", new Date(121,7,28),catId11,kids);
			Course course311 = new Course("course23", new Date(121,6,28),catId11,kids);
			//Course course421 = new Course("course44", new Date(121,6,28),catId11,kids);
			//Course course521 = new Course("course254", new Date(121,5,28),catId11,kids);
			Course course621 = new Course("course2546", new Date(121,5,28),catId11,kids);
			courseRepo.addANewCourse(course111);
			courseRepo.addANewCourse(course211);
			courseRepo.addANewCourse(course311);
			//courseRepo.addANewCourse(course421);
			//courseRepo.addANewCourse(course521);
			courseRepo.addANewCourse(course621);
			
			String catId112 = "612a326989674a4e38a688a3";
			Course course1112 = new Course("course21", new Date(121,7,27),catId112,kids);
			Course course2112 = new Course("course22", new Date(121,5,28),catId112,kids);
			Course course3112 = new Course("course23", new Date(121,6,28),catId112,kids);
			Course course4212 = new Course("course44", new Date(121,6,28),catId112,kids);
			//Course course5212 = new Course("course254", new Date(121,5,28),catId11,kids);
			Course course6212 = new Course("course2546", new Date(121,5,28),catId112,kids);
			courseRepo.addANewCourse(course1112);
			courseRepo.addANewCourse(course2112);
			courseRepo.addANewCourse(course3112);
			courseRepo.addANewCourse(course4212);
			//courseRepo.addANewCourse(course521);
			courseRepo.addANewCourse(course6212);

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
		return courseRepo.addANewCourse(course);
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
	
	@GetMapping("/getActivityTime/{period}")
	public HashMap<String,Double> getActivityTime(@PathVariable int period){
		return meetingRepo.getActivityTime(period);
	}
	@GetMapping("/test")
	public Integer getCategoryKids() {
		String catId = categoryRepo.getAllCategoriesIds().get(1);
		ArrayList<Course> courses = courseRepo.getCategoryCourses(catId);
		Date d = new Date((new Date()).getTime()- 7*DAY_IN_MS);
		int num = 0;
		for(Course c : courses) {
			if(c.getStartDateTime().after(d)) {
				num+=c.getKidsIDs().size();
			}
		}
		return num;
	}
	
	
	
}