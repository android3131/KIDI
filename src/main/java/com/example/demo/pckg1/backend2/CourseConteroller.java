package com.example.demo.pckg1.backend2;

import com.example.demo.pckg1.CategoryRepository;
import com.example.demo.pckg1.Course;
import com.example.demo.pckg1.CourseRepository;
import com.example.demo.pckg1.ICourseRepository;
import com.example.demo.pckg1.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class CourseConteroller {
    @Autowired
    ICourseRepository iCourseRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    LeaderRepository leaderRepository;
    @Autowired
    CategoryRepository catRepository;

  //For screen: New course
  	/**
  	 * crating a new course
  	 * * @param course
  	 * @return true id added, false if not
  	 */
  	@PostMapping("/createNewCourse")
  	public String createCourse(@RequestBody Course course) {
  		String msg = "The course was added successfully";
  		Time tFinish = new Time(course.getFinishDateTime().getTime());
  		Time tStart = new Time(course.getStartDateTime().getTime());
  		if (!(catRepository.getAllCategories().contains(catRepository.getCategoryById(course.getCategoryId())))) {
  			msg= "Failed to add. Category doesn't exist";
  			return msg;
  		}
  		if(tFinish.before(tStart)) {
  			msg= "Failed to add. The finish time is before the start time.";
  			return msg;
  		}
  		if(course.getStartDateTime().after(course.getFinishDateTime())) {
  			msg= "Failed to add. The end date is before the start date.";
  			return msg;
  		}
  		if(course.getStartDateTime().before(new Date()) ) {
  			msg= "Failed to add. The start date is in the past.";
  			return msg;
  		}
  		courseRepository.addANewCourse(course);
  		if (!courseRepository.getAllCourses().contains(course)) {
  			msg= "Failed to add. The course name already exists";
  			return msg;
  		}
  		return msg;
  	}

    /**
     * @param ID of course
     * @return course of given ID
     * */
    @GetMapping("/course/getCourseByID/{ID}")
    public Course getCourseByID(@PathVariable String ID){
        return iCourseRepository.findById(ID).get();
    }

    /**
     * @param categoryID
     * @return courses of category id
     * */
    @GetMapping("/getCoursesByCategory/{categoryID}")
    public ArrayList<Course> getCoursesByCategory(@PathVariable String categoryID){
//        Category ca = new Category("1","2");
//        Category cawew=iCategoryRepository.save(ca);
//        System.out.println("AAAA" + ca.getId());
//        Course c = new Course("name12123", 1, null, null, cawew,null);
//        System.out.println("2***"+cawew.getId());

        //  courseRepository.save(c);

        // Category category = categoryRepository.getCategoryById(categoryID);
        return leaderRepository.getCoursesByCategoryID(categoryID);
    }

    /**
     * @param name
     * @return course of given name
     * */
    @GetMapping("/getCourseByName/{name}")
    public Object getCourseByName(@PathVariable String name) {

        if(name==null)
            return new ResponseEntity<Course>((Course) null, HttpStatus.NOT_ACCEPTABLE);

        Course my_Course = courseRepository.getASpecificCourseByName(name);
        if(my_Course== null)
            return new ResponseEntity<Course>((Course) null, HttpStatus.NOT_FOUND);
        else
            return my_Course;
    }



}
