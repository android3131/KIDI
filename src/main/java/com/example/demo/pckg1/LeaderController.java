package com.example.demo.pckg1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaders")
public class LeaderController {

    @Autowired
    Leader_Repository ileaderRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ICourseRepository courseRepository;
    @Autowired
    ICategoryRepository iCategoryRepository;



    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }


    /**
     *
     * @param leader of the course
     * @return the leader that added
     */
    @PostMapping ("/postleader")
    public ResponseEntity<String> addLeader(@RequestBody Leader leader){
        // check for valid input.


        LocalDate lt = LocalDate.now();
        // add address afterwards
        // fix date
        if(leader.getFullName().isEmpty() || !leader.getFullName().contains(" ") ||
        !isValidEmailAddress(leader.getEmail()) ||
         leader.getProfilePic() == null
        || leader.getCategoryIDs().isEmpty() || leader.getActiveStatus() == null ||
        leader.getActiveDate().isEmpty())
            return new ResponseEntity<>("invalid input",HttpStatus.BAD_REQUEST);


        ileaderRepository.addANewLeader(leader);
        return new ResponseEntity<>("data was added successfully",HttpStatus.ACCEPTED);
    }


   /**
    * @param email
    * @return if the email is valid
    * */
    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * @param newstatus
     * @param leaderID
     * @return msg about changing active status of leader
     * */
    @PutMapping("/updatestatus/{leaderID}")
    public ResponseEntity<Leader> updateLeadersStatus(@RequestBody String newstatus,@PathVariable String leaderID){
        Optional<Leader> leaderOptional = ileaderRepository.getASpecificLeader(leaderID);
        Leader leader = leaderOptional.get();
        String status = leader.getActiveStatus().toString();

        if(leaderID.isEmpty()) {
            return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_ACCEPTABLE);
        }
        Leader my_led = null;
        if(newstatus.toString().equals("Active")) {

            my_led = ileaderRepository.updateLeaderStatus(leaderID, Status.Active);
        }
        else if(newstatus.toString().equals("Pending")) {
            my_led = ileaderRepository.updateLeaderStatus(leaderID, Status.Pending);
        }
        else {

            return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Leader>(my_led, HttpStatus.OK);
    }

    /**
     * @param fullName
     * @return array list oof leader, else error message if not found
     * */
    @GetMapping("/getLeadersByName/{fullName}")
    public Object getLeaderByFullName(@PathVariable String fullName) {
            if(fullName==null)
                return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_ACCEPTABLE);

		    ArrayList<Leader> my_lst = ileaderRepository.getLeadersByUserName(fullName);
		    if(my_lst.isEmpty())
                return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_FOUND);
		    else
		        return my_lst;
}
    /**
     * @param categoryID
     * @return courses of category id
     * */
    @GetMapping("/getCoursesByCategory/{categoryID}")
    public ArrayList<Course> getCoursesByCategory(@PathVariable String categoryID){
//        Category ca = new Category("1","2");
//        Course c = new Course("name", 1, null, null, ca,null);
//        courseRepository.save(c);
//        iCategoryRepository.save(ca);
        Category category = categoryRepository.getCategoryById(categoryID);
        return ileaderRepository.getCoursesByCategory(category);
    }


    /**
     * @param ID of course
     * @return course of given ID
     * */
    @GetMapping("/course/getCourseByID/{ID}")
    public Course getCourseByID(@PathVariable String ID){
       return courseRepository.findById(ID).get();
    }

    /**
     * @param id of leader
     * @return leader of given id
     * */
    @GetMapping("/findByID/{id}")
    public Optional<Leader> findLeaderByID(@PathVariable String id){
        return ileaderRepository.getASpecificLeader(id);
    }


}
