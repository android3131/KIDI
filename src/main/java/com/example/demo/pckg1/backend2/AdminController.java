package com.example.demo.pckg1.backend2;

import com.example.demo.pckg1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    Leader_Repository leaderRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CourseRepository courseRepository;

    /**
     * @param categoryID
     * @return array list of leader in given category
     * */

    @GetMapping("/getLeaderByCategory/{categoryID}")
    public ArrayList<Leader> getLeadersByCategoryID(@PathVariable String categoryID){
    	return leaderRepository.getCategoryLeaders(categoryID);
    }

    /**
     * @param leaderID
     * @param courseID
     * @return boolean variable if leader was added to given
     * course of id or not
     * */
    @PutMapping("/LeaderCourse/{courseID}/{leaderID}")
    public ResponseEntity<Boolean> AddLeaderToCourse(@PathVariable String courseID , @PathVariable String leaderID){
//  Leader le=ileaderRepository.updateLeaderTOActive(leaderID);
        if(leaderID.isEmpty()|| courseID.isEmpty())
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);

        Boolean b = courseRepository.addLeaderToCourse(courseID,leaderID);
        if(!b)
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);

        return new ResponseEntity<Boolean>(b, HttpStatus.OK);
    }

    /**
     * @param courseID
     * @param leaderID
     * @return response if leader was removed from given course
     * */
    @PutMapping("/LeaderCourseRemove/{courseID}/{leaderID}")
    public ResponseEntity<Boolean> RemoveLeaderToCourse(@PathVariable String courseID , @PathVariable String leaderID){
//  Leader le=ileaderRepository.updateLeaderTOActive(leaderID);
        if(leaderID.isEmpty()|| courseID.isEmpty())
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);

        Boolean b = courseRepository.removeLeaderCourse(courseID,leaderID);
        if(!b)
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);

        return new ResponseEntity<Boolean>(b, HttpStatus.OK);
    }


    /**
     * @param courseName
     * @return list of leaders of given course name
     * */
    @GetMapping("/getLeadersBysCourseName/{courseName}")
    public Object getLeadersBysCourseName(@PathVariable String courseName) {
        if(courseName==null)
            return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_ACCEPTABLE);

        ArrayList<String> my_leaders = courseRepository.getCourseLeadersByName(courseName);
        if(my_leaders.isEmpty())
            return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_FOUND);
        else
            return my_leaders;
    }

    /**
     * @return array list of all categories
     * */
    @GetMapping("/categories/getCategories")
    public ArrayList<Category> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

}
