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
   	 * @param leader
   	 * @return All leaders
   	 */
	public List<Leader> addANewLeader (Leader leader){
		leaderRepository.save(leader);
		new ResponseEntity<>("New leader added successfully", HttpStatus.OK);
		return leaderRepository.findAll();
	}

	public Object updateExistingLeader (Leader leader,String Id){
		Optional<Leader> leader1 = leaderRepository.findById(Id);
		leader1.get().setActiveStatus(leader.getActiveStatus());
		leader1.get().setActiveDate(leader.getActiveDate());
		//leader1.get().setAddress(leader.getAddress());
		//leader1.get().setDate(leader.getDate());
		leader1.get().setEmail(leader.getEmail());
		leader1.get().setCategoryIDs(leader.getCategoryIDs());
		//leader1.get().setDateOfBirth(leader.getDateOfBirth());
		leader1.get().setFullName(leader.getFullName());
		leader1.get().setProfilePic(leader.getProfilePic());
		leader1.get().setPhoneNumber(leader.getPhoneNumber());
		leaderRepository.save(leader1.get());
		return new ResponseEntity<>("New leader added successfully", HttpStatus.OK);
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
   	 * @param ID
   	 * @return Leader if it was found, null if it was not found
   	 */
	public Optional<Leader> getASpecificLeader(String ID) {
		System.out.println("============="+leaderRepository.findById("611a55e2befa14396e70f493"));
		Optional<Leader> leader = leaderRepository.findById(ID);
		if (leader.isPresent())
			return leader;
		return null;
	}
	
	/**
   	 * Returns a leader's category
   	 * @param ID
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
   	 * @param categoryID
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
   	 * @param leaderID
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

	/**
	 * @param leaderID
	 * @param status
	 * @return leader of updated status	 */

	public Leader updateLeaderStatus(String leaderID, Status status) {
		Optional<Leader> leader = leaderRepository.findById(leaderID);
		if (leader.isPresent()) {
			if(status.equals(Status.Pending))
				leader.get().setActiveStatus(Status.Pending);
			if(status.equals(Status.Active))
				leader.get().setActiveStatus(Status.Active);
			return	leaderRepository.save(leader.get());
		}
		return null;
	}


/**
 * @param userName
 * @return leader of given user name
 * */
	public ArrayList<Leader> getLeadersByUserName(String userName) {
		ArrayList<Leader> leaders = (ArrayList<Leader>) getAllLeaders();
		ArrayList<Leader> leadersToReturn= new ArrayList<>();
		for( Leader le :leaders){
			if(le.getFullName().indexOf(userName)!=-1)
				leadersToReturn.add(le);
		}
		return leadersToReturn;
	}

	/**
	 * @param category
	 * @return courses in given category
	 * */
	public ArrayList<Course> getCoursesByCategory(Category category){
		CourseRepository course_repository = new CourseRepository();
		ArrayList<Course> coursesByCategory = new ArrayList<>();
		ArrayList<Course> my_courses = (ArrayList<Course>) course_repository.getAllCourse();
		for(Course c : my_courses){
			if(c.getCategory().equals(category))
				coursesByCategory.add(c);
		}
		return coursesByCategory;
	}



}
