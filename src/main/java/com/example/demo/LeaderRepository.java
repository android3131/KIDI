package com.example.demo;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderRepository {

	@Autowired
	ILeaderRepository leaderRepository;
	
	@Autowired
	CourseRepository courseRepository;

	/**
	 * Adds a leader and returns all leaders
	 * 
	 * @param leader
	 * @return list of all leaders
	 */
	public List<Leader> addANewLeader(Leader leader) {
		leader.setActiveStatus(Status.Active);
		leader.setActiveDate(new Date());
		leaderRepository.save(leader);
		return leaderRepository.findAll();
	}
	
	/**
	 * Adds a new leader and return them
	 * 
	 * @param leader
	 * @return leader
	 */
	public Leader addANewLeadre(Leader leader) {
		leader.setActiveStatus(Status.Active);
		leader.setActiveDate(new Date());
		return leaderRepository.save(leader);
	}

	/**
	 * Gets all leaders
	 * 
	 * @return All leaders
	 */
	public List<Leader> getAllLeaders() {
		return leaderRepository.findAll();
	}

	/**
	 * Returns a specific leader
	 * 
	 * @param Leader ID
	 * @return Leader if it was found, null if it was not found
	 */
	public Optional<Leader> getASpecificLeader(String ID) {
		Optional<Leader> leader = leaderRepository.findById(ID);
		if (leader.isPresent())
			return leader;
		return null;
	}

	/**
	 * Returns a leader's category
	 * 
	 * @param Course ID
	 * @return Course's categories if the course was found, null otherwise
	 */
	public ArrayList<String> getLeaderCategory(String ID) {
		Optional<Leader> leader = leaderRepository.findById(ID);
		if (leader.isPresent())
			return leader.get().getCategoriesIDs();
		return null;
	}

	/**
	 * Returns a category Leaders list
	 * 
	 * @param Course ID
	 * @return A list of a specific category leaders
	 */
	public ArrayList<Leader> getCategoryLeaders(String categoryID) {
		ArrayList<Leader> categoryLeaders = new ArrayList<Leader>();
		for (Leader l : leaderRepository.findAll()) {
			if (l.getCategoriesIDs().contains(categoryID))
				categoryLeaders.add(l);
		}
		return categoryLeaders;
	}

	/**
	 * Removes a leader by updating their status to inactive
	 * 
	 * @param Leader ID
	 * @return true when the status is updated
	 */
	public Boolean removeLeader(String leaderID) {
		Optional<Leader> leader = leaderRepository.findById(leaderID);
		if (leader.isPresent()) {
			leader.get().setActiveStatus(Status.InActive);
			leaderRepository.save(leader.get());
			return true;
		}
		return false;
	}

	/**
	 * Updates a leader
	 * 
	 * @param Leader, leader ID
	 * @return Object
	 */
	public Object updateExistingLeader (Leader leader,String Id){
		Optional<Leader> leader1 = leaderRepository.findById(Id);
		leader1.get().setActiveStatus(leader.getActiveStatus());
		leader1.get().setAddress(leader.getAddress());
		//leader1.get().setDate(leader.getDate());
		leader1.get().setEmail(leader.getEmail());
		leader1.get().setCategoriesIDs(leader.getCategoriesIDs());
		leader1.get().setDateOfBirth(leader.getDateOfBirth());
		leader1.get().setFullName(leader.getFullName());
		leader1.get().setProfilePic(leader.getProfilePic());
		leader1.get().setPhoneNumber(leader.getPhoneNumber());
		leaderRepository.save(leader1.get());
		return new ResponseEntity<>("New leader added successfully", HttpStatus.OK);
	}
	
	/**
	 * @param leaderID
	 * @param status
	 * @return leader of updated status	 */
	public Leader updateLeaderStatus(String leaderID, Status status) {
		Optional<Leader> leader = leaderRepository.findById(leaderID);
		if (leader.isPresent()) {
			if(status.equals(Status.Pending)) {
				leader.get().setActiveStatus(Status.Pending);
			}
			else if(status.equals(Status.Active)) {
					leader.get().setActiveStatus(Status.Active);
				}
			else {
				leader.get().setActiveStatus(Status.InActive);
				}
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
		 * @param categoryID
		 * @return courses in given category
		 * */
		public ArrayList<Course> getCoursesByCategoryID(String categoryID){
			ArrayList<Course> coursesByCategory = new ArrayList<>();
			ArrayList<Course> my_courses = (ArrayList<Course>) courseRepository.getAllCourses();
			for(Course c : my_courses){

				if(c.getCategoryId().equals(categoryID))
					coursesByCategory.add(c);
			}
			return coursesByCategory;
		}
	
}