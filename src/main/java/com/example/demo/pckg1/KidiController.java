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
meetingRepo.addANewMeeting(new Meeting("courseId", new Date()));
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

	@PostMapping("/addNewCategory")
	public Meeting addNewCategory(Category category) {
		Meeting meeting = new Meeting("course1",new Date());
		return meetingRepo.addMeeting(meeting);
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