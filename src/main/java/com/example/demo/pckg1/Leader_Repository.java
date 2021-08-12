package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
@Repository
public class Leader_Repository {
	
	@Autowired
	ILeaderRepository leaderRepository;
	
	/**
   	 * Adds a new leader
   	 * @param Leader
   	 * @return All leaders
   	 */
	public List<Leader> addANewLeader (Leader leader){
		if(leaderRepository.findById(leader.getID()).isPresent()) {
			new ResponseEntity<>("Failed to add a new leader, the name already exists in the system", 
					HttpStatus.NOT_ACCEPTABLE);
			return leaderRepository.findAll();
		}
		leaderRepository.save(leader);
		new ResponseEntity<>("New leader added successfully", HttpStatus.OK);
		return leaderRepository.findAll();
	}
	
	/**
   	 * Gets all leaders
   	 * @return All leaders
   	 */
	public List<Leader> getAllLeaders() {
		return leaderRepository.findAll();
	}
	
	/**
   	 * Returns a specific leader
   	 * @param Leader ID
   	 * @return Leader if it was found, null if it was not found
   	 */
	public Optional<Leader> getASpecificLeader(String ID) {
		Optional<Leader> leader = leaderRepository.findById(ID);
		if (leader.isPresent())
			return leader;
		new ResponseEntity<>("Leader not found", HttpStatus.NOT_ACCEPTABLE);
		return null;
	}
	
	/**
   	 * Returns a leader's category
   	 * @param Course ID
   	 * @return Course's categories if the course was found, null otherwise
   	 */
	public ArrayList<String> getLeaderCategory(String ID){
		Optional<Leader> leader = leaderRepository.findById(ID);
		if (leader.isPresent())
			return leader.get().getCategoryIDs();
		return null;
		}
	
	/**
   	 * Returns a category Leaders list
   	 * @param Course ID
   	 * @return A list of a specific category leaders 
   	 */
	public ArrayList<Leader> getCategoryLeaders(String categoryID){
		ArrayList<Leader> categoryLeaders = new ArrayList<Leader>(); 
		for(Leader l: leaderRepository.findAll()) {
			if(l.getCategoryIDs().contains(categoryID))
				categoryLeaders.add(l);
		}
		return categoryLeaders;
	}
	
	/**
   	 * Removes a leader by updating their status to inactive
   	 * @param Leader ID
   	 * @return true when the status is updated 
   	 */
	public Boolean removeLeader(String leaderID) {
		Optional<Leader> leader = leaderRepository.findById(leaderID);
		if (leader.isPresent()) {
			leader.get().setActiveStatus(Status.InActive);
			return true;
		}
		return false;
	}
}
