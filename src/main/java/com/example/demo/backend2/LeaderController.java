package com.example.demo.backend2;


import com.example.demo.CategoryRepository;
import com.example.demo.CourseRepository;
import com.example.demo.ICategoryRepository;
import com.example.demo.ICourseRepository;
import com.example.demo.Leader;
import com.example.demo.LeaderRepository;
import com.example.demo.Status;
import com.example.demo.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaders")
public class LeaderController {

    @Autowired
    LeaderRepository ileaderRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ICourseRepository courseRepository;
    @Autowired
    ICategoryRepository iCategoryRepository;
    @Autowired
    CourseRepository tcourseRepository;
    @Field
	Validation validate;

    /**
     *
     * @param leader of the course
     * @return the added leader.
     */
    @PostMapping ("/postleader")
    public ResponseEntity<String> addLeader(@RequestBody Leader leader){
    	if(validate.check_age(leader.getDateOfBirth(), "leader") 
    			& validate.check_name(leader.getFullName()) 
    			& validate.check_email(leader.getEmail())
    			& validate.check_phone(leader.getPhoneNumber())) {
    			ileaderRepository.addANewLeadre(leader);
    	        return new ResponseEntity<>("data was added successfully",HttpStatus.OK);
    	}
        
        return new ResponseEntity<>("invalid input",HttpStatus.NOT_ACCEPTABLE);
    }
    
    /**
     * @param leaderID
     * @return HTTP status ok if leader found and got updated
     * HTTP status not found otherwise.
     * */

    @PutMapping ("/updateLeader/{leaderID}")
    public Object updateLeaderByID(@PathVariable String leaderID,@RequestBody Leader leader) {
    	
    	List<Leader> allLeaders = ileaderRepository.getAllLeaders();
    	if(!allLeaders.contains(leader) 
    			|| !allLeaders.contains(ileaderRepository.getASpecificLeader(leaderID))
    			|| !validate.check_age(leader.getDateOfBirth(), "leader")
    			|| !validate.check_name(leader.getFullName())
    			|| !validate.check_email(leader.getEmail())
    			|| !validate.check_phone(leader.getPhoneNumber())) {
    		 
    			return new ResponseEntity<>("invalid input",HttpStatus.NOT_ACCEPTABLE);
    	}

    	ileaderRepository.updateExistingLeader(leader,leaderID);
    	return new ResponseEntity<>("successfuly updated existing leader",HttpStatus.OK);
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

}
