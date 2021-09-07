package com.example.demo.pckg1.backend1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pckg1.IParentRepository;
import com.example.demo.pckg1.KidRepository;
import com.example.demo.pckg1.Parent;
import com.example.demo.pckg1.Parent_repository;

@RestController
public class ParentProfileController {
	@Autowired
	Parent_repository parentRepo;
	
	@Autowired
	IParentRepository IparentRepo;
	
	
	@Autowired
	KidRepository kidRepo;
	
	@GetMapping("getallparents")
	public List<Parent> getAllparents(){
		return parentRepo.getAllParents();
	}
	
	@PutMapping("updateparent/{id}")
	public List<Parent> updateParent(@PathVariable String id, @RequestBody Parent updatedParent){
		Parent parent = parentRepo.getParentById(id);
		
		parent.setFullName(updatedParent.getFullName());
		parent.setPhoneNumber(updatedParent.getPhoneNumber());
		parent.setEmail(updatedParent.getEmail());
		IparentRepo.save(parent);
		
		return parentRepo.getAllParents();
	}
}
