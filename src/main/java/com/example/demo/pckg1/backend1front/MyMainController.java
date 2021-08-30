package com.example.demo.pkg1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyMainController {
	@Autowired
private NotificationService notification;
	@RequestMapping("/my_hello")
    public String sayHello() 
    {
      return "hello hello";
    }
	
	
	@RequestMapping("/My_world")
    public String sayWorld() 
    {
      return "hello world";
    }
	
	
	//====================================
	// localhost:8090/findUser
	@Autowired
	private MyRepository myRepository;
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers(){
		return myRepository.retrieveAllUsers();
	}
	
	@GetMapping("/finduser/{name}")
	public User retrieveUserByName(@PathVariable String name) {
		
		return myRepository.getUsertWithName(name);
	}
	
	@DeleteMapping("/deleteuser/{name}")
	public List<User> deleteUser(@PathVariable String name) {
		return myRepository.deleteByName(name);
	}
	
	
	
	
	// localhost:8090/createStudent
	/* 
	 {
	  		"name":"john",
	  		"birthYear":2002,
	  		"address":"great street 15"
	  }
	 */
	
	@PostMapping("/createUser")
	public User createNewUser(@RequestBody User user) {
		return myRepository.createUser(user);
	}
	@GetMapping("/sendMail/{name}")
	public void  sendMailToUser(@PathVariable String name) {
		notification.sendNotification(name);
	}
	
	

}


/*
@GetMapping("/students/{name}")
public Student retrieveStudentByName(@PathVariable String name) {
	return myRepository.getStudentWithName(name);
}

@DeleteMapping("/students/{name}")
public List<Student> deleteStudent(@PathVariable String name) {
	return myRepository.deleteByName(name);
}
*/