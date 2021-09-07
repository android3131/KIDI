package com.example.demo.backend2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.CategoryRepository;
import com.example.demo.Course;
import com.example.demo.CourseRepository;
import com.example.demo.ICourseRepository;
import com.example.demo.LeaderRepository;
import com.example.demo.Validation;

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
    @Field
	Validation validate;

  //For screen: New course
  	/**
  	 * crating a new course
  	 * * @param course
  	 * @return true id added, false if not
  	 */
  	@PostMapping("/createNewCourse")
  	public ResponseEntity<String> createCourse(@RequestBody Course course) {
  		String msg = "The course was added successfully";
  		Time tFinish = new Time(course.getFinishDateTime().getTime());
  		Time tStart = new Time(course.getStartDateTime().getTime());
  		if (!(catRepository.getAllCategories().contains(catRepository.getCategoryById(course.getCategoryId()))) 
  				|| tFinish.before(tStart)
  				|| course.getStartDateTime().after(course.getFinishDateTime())
  				|| course.getStartDateTime().before(new Date())
  				|| courseRepository.getAllCourses().contains(course)
  				|| !validate.check_course_duration(course.getStartDateTime(), course.getFinishDateTime())
  				|| !validate.check_course_name(course.getName())
  				|| !validate.check_zoom_link(course.getZoomMeetingLink())) {
  			
  				return new ResponseEntity<>("failed", HttpStatus.NOT_ACCEPTABLE);
  		}
  		
  		courseRepository.addANewCourse(course);
  		return new ResponseEntity<>("successfuly added course", HttpStatus.OK);
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
