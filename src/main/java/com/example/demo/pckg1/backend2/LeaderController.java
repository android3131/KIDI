package com.example.demo.pckg1.backend2;

import com.example.demo.pckg1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    CourseRepository tcourseRepository;


    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }


    /**
     *
     * @param leader of the course
     * @return the added leader.
     */
    @PostMapping ("/postleader")
    public ResponseEntity<String> addLeader(@RequestBody Leader leader){
        // check for valid input.
        if(!isValidLeader(leader))
            return new ResponseEntity<>("invalid input",HttpStatus.BAD_REQUEST);
        ileaderRepository.addANewLeader(leader);
        return new ResponseEntity<>("data was added successfully",HttpStatus.ACCEPTED);
    }

    private boolean isValidLeader(Leader leader){
        String fullNameCheck = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
        Date date = new Date();
        long timeDiff = date.getTime()-leader.getDateOfBirth().getTime();
        long difference_In_Years
                = (timeDiff
                / (1000l * 60 * 60 * 24 * 365));
        if(leader.getFullName().isEmpty() ||
                !isValidEmailAddress(leader.getEmail()) ||
                leader.getProfilePic() == null
                || leader.getCategoryIDs().isEmpty() || leader.getActiveStatus() == null
                || difference_In_Years < 12 || !leader.getFullName().matches(fullNameCheck))
            return false;
        return true;
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
     * @param leaderID
     * @return HTTP status ok if leader found and got updated
     * HTTP status not found otherwise.
     * */

    @PutMapping ("/updateLeader/{leaderID}")
    public Object updateLeaderByID(@PathVariable String leaderID,@RequestBody Leader leader) {

        String fullNameCheck = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
        LocalDate lt = LocalDate.now();
        Date date = new Date();
        long timeDiff = date.getTime()-leader.getDateOfBirth().getTime();
        long difference_In_Years
                = (timeDiff
                / (1000l * 60 * 60 * 24 * 365));
        // add address afterwards
        // fix date
        if(!isValidLeader(leader))
            return new ResponseEntity<>("invalid input",HttpStatus.BAD_REQUEST);
        if(ileaderRepository.getASpecificLeader(leaderID)!=null){
            return ileaderRepository.updateExistingLeader(leader,leaderID);
        }
        else{
            return new ResponseEntity<String>("leader not found",HttpStatus.NOT_FOUND);
        }}


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
        else if(newstatus.toString().equals("InActive")) {
            my_led = ileaderRepository.updateLeaderStatus(leaderID, Status.InActive);
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
     * @param id of leader
     * @return leader of given id
     * */
    @GetMapping("/findByID/{id}")
    public Optional<Leader> findLeaderByID(@PathVariable String id){
        return ileaderRepository.getASpecificLeader(id);
    }


    // Check for create Course.



}
