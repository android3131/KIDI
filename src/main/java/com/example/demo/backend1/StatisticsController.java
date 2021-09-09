package com.example.demo.backend1;

import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.CategoryRepository;
import com.example.demo.KidRepository;
import com.example.demo.MeetingRepository;
import com.example.demo.Parent_repository;

public class StatisticsController {

	@Autowired
	MeetingRepository meetingRepo;
	@Autowired
	KidRepository kidRepo;
	@Autowired
	Parent_repository parentRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@GetMapping("getactivitiesperweek")
	public HashMap<String, Double> activitiesWeek(){
		return meetingRepo.getActivityTime(1);
	}
	
	@GetMapping("getactivitiespermonth")
	public HashMap<String, Double> activitiesMonth(){
		return meetingRepo.getActivityTime(2);
	}
	@GetMapping("getactivitiesperyear")
	public HashMap<String, Double> activitiesYear(){
		return meetingRepo.getActivityTime(3);	
	}


	@GetMapping("getlistofactivekidsperweek")
	public HashMap<String, Integer> activeKidsWeek(){
		
		return kidRepo.getNewKids(1);
	}
	
	@GetMapping("getlistofactivekidspermonth")
	public HashMap<String, Integer> activeKidsMonth(){
		return kidRepo.getNewKids(2);
	}
	@GetMapping("getlistofactivekidsperyear")
	public HashMap<String, Integer> activeKidsYear(){
		return kidRepo.getNewKids(3);
	}

	@GetMapping("getallactiveparentsbyweek")
	public HashMap<String, Integer> activeParentsWeek(){
		return parentRepo.getNewParents(1);
	}
	
	@GetMapping("getallactiveparentsbymonth")
	public HashMap<String, Integer> activeParentsMonth(){
		return parentRepo.getNewParents(2);
	}
	
	@GetMapping("getallactiveparentsbyyear")
	public HashMap<String, Integer> activeParentsYear(){
		return parentRepo.getNewParents(3);
	}
	
	// -------- get active kids in category per week/month/year -------- 	
	@GetMapping("getlistofactivekidspercategoryperweek")	
	public HashMap<String, Integer> activeKidsCategWeek(){		
		return categoryRepo.getKidsCountByCategory(1);
		}		
	
	@GetMapping("getlistofactivekidspercategorypermonth")	
	public HashMap<String, Integer> activeKidsCategMonth(){		
		return categoryRepo.getKidsCountByCategory(2);	
		}		
	@GetMapping("getlistofactivekidspercategoryperyear")	
	public HashMap<String, Integer> activeKidsCategYear(){	
		return categoryRepo.getKidsCountByCategory(3);	
		}	
	@GetMapping("getkidsbycategorymonth/{category}")	
	public TreeMap<Integer,Integer> kidsByCategoryMonth(@PathVariable String category){	
		return kidRepo.getKidsCategoryMonth(category);	
		}
}
