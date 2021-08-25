package com.example.demo.pckg1.backend2;

import com.example.demo.pckg1.Course;
import com.example.demo.pckg1.CourseRepository;
import com.example.demo.pckg1.ICourseRepository;
import com.example.demo.pckg1.Leader_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Time;
import java.util.ArrayList;

public class CourseConteroller {
    @Autowired
    ICourseRepository iCourseRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    Leader_Repository leaderRepository;


    /**
     * crating a new course
     * * @param course
     * @return true id added, false if not
     */
    //TODO: validations, CHECK WITH POSTMAN+DB
    @PostMapping("/createNewCourse")
    public Boolean createCourse(@RequestBody Course course) {
        int check=1;
        Time tFinish = new Time(course.getFinishDateTime().getTime());
        Time tStart = new Time(course.getStartDateTime().getTime());

//        if(tFinish.before(tStart)) {
//            check=0;
//        }
//        if(course.getStartDateTime().after(course.getFinishDateTime())) {
//            check=0;
//        }
//        if(course.getStartDateTime().before(new Date()) ) {
//            check=0;
//        }
        if(check==1) {
            return courseRepository.addANewCourse(course);
        }
        return false;
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
