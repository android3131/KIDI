package com.example.demo.backend1;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.IParentRepository;
import com.example.demo.KidRepository;
import com.example.demo.Parent;
import com.example.demo.Parent_repository;
import com.example.demo.Validation;


@RestController
public class ParentProfileController {
	@Autowired
	Parent_repository parentRepo;
	
	@Autowired
	IParentRepository IparentRepo;
	
	@Autowired
	KidRepository kidRepo;
	
	@Field
	Validation validate;
	
	/**
     * @return all existing parents
     **/
	@GetMapping("getallparents")
	public List<Parent> getAllparents(){
		return parentRepo.getAllParents();
	}
	
	/**
     * @param id of parent
     * @param updatedParent existing parent with updated fields
     * @return parent if updated successfuly, or null if failed
     * */
	@PutMapping("updateparent/{id}")
	ResponseEntity<Parent> updateParent(@PathVariable String id, @RequestBody Parent updatedParent){
		Parent parent = parentRepo.getParentById(id);
		
		if(validate.check_name(updatedParent.getFullName()) 
				& validate.check_phone(updatedParent.getPhoneNumber()) 
				& validate.check_email(updatedParent.getEmail())) {
			
					parent.setFullName(updatedParent.getFullName());
					parent.setPhoneNumber(updatedParent.getPhoneNumber());
					parent.setEmail(updatedParent.getEmail());
					IparentRepo.save(parent);
					return new ResponseEntity<>(parent, HttpStatus.OK);
		}
		
		return new ResponseEntity<>((Parent) null, HttpStatus.NOT_ACCEPTABLE);
	}
}
