//package com.example.demo.pckg1;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/api/subjects")
//public class MyController {
//	
//	@Autowired  
//	ISubjectRepository subjectRepo;
//	
//	@GetMapping("/hello")
//	public ResponseEntity<?> addAllInfo()
//	{
//		if(subjectRepo.findAll().isEmpty())
//		{
//			subjectRepo.save(new Subject("Nature", "The worlds nature at its best"));
//			subjectRepo.save(new Subject("Space", "Stars and beyond"));
//			subjectRepo.save(new Subject("Physics", "Powers, objects and everything between"));
//		}
//		return new ResponseEntity<>("hello", HttpStatus.OK);
//	}
//	
//	
//	@GetMapping("/")
//	public List<Subject> getAllSubjects()
//	{
//		return subjectRepo.findAll();
//	}
//	
//	@PostMapping("/")
//	public Subject addNewSubject(@RequestBody Subject subject) {
//		return subjectRepo.save(subject);
//	}
//
//
//	@GetMapping("/{id}")
//	public Optional<Subject> getSubject(@PathVariable String id) {
//		System.out.println("Getting subject with ID " + id);
//		return subjectRepo.findById(id);
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Object> deleteSubject(@PathVariable String id) {
//		if(! getSubject(id).isEmpty())
//		{
//			subjectRepo.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//	
//	@GetMapping("/examples")
//	public ResponseEntity<?> demonstrateAllMethods(){
////		List saveAll(List subjects);
////
////		List findAll();
////
////		List findAll(Sort sort);
////
////		Optional findById(String subjectId);
////
////		Employee insert(Subject subject);
////
////		List insert(List subjects);
////
////		List findAll(Example subjectExample);
////
////		 List findAll(Example example, Sort sort);
////
////		void delete(Subject subject);
////
////		void deleteById(String id);
//		
////		void deleteAll(List subjects)
//		
//		subjectRepo.deleteById("jhgjhgjg");
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//
//
//}
