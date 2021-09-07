package com.example.demo;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Validation {

	/* password regular expression */
	String passwordRegex = "^(?=.[a-z])(?=.[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	
	/* email regular expression */
	String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	
	/* name regular expression */
	String nameRegex = "(?=^.{6,40}$)^[a-zA-Z-]+\\s[a-zA-Z-]+$";
	
	/* phone regular expression */
	String phoneRegex = "^\\d{10}$";
	
	/* zoom link regular expression */
	String zoomLinkRegex = "((http|https)://)(www.)?" + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]" + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
	
	/* image regular expression */
	String imageRegex = "([^\\\\s]+(\\\\.(?i)(jpe?g|png|gif|bmp))$)";
	
	/* password regular expression pattern */
	Pattern password_pattern = Pattern.compile(passwordRegex);
	
	/* email regular expression pattern */
	Pattern email_pattern = Pattern.compile(emailRegex);
	
	/* name regular expression pattern */
	Pattern name_pattern = Pattern.compile(nameRegex);
	
	/* phone regular expression pattern */
	Pattern phone_pattern = Pattern.compile(phoneRegex);
	
	/* zoom link regular expression pattern */
	Pattern link_pattern = Pattern.compile(zoomLinkRegex);
	
	/* image regular expression pattern */
	Pattern image_pattern = Pattern.compile(imageRegex);
	
	Validation(){
		
	}

	// FUNCTION RETURNS TRUE IF PASSWORD IS VALID
	
    public boolean check_password(String password,String retyped_password) {
	    if(password!=null && retyped_password!=null) {
		    if (! password.equals(retyped_password)){
		    	return false;
		    }
		
		    if (! password_pattern.matcher(password).matches()) {
		        return false;
		    }
		}
        return true;
    }
   
    // FUNCTION RETURNS TRUE IF EMAIL IS VALID
    
    public boolean check_email(String email){
	    if(email!=null) {
		    if (! email_pattern.matcher(email).matches()) {
		    	return false;
		    }
	    }
        return true;
    }
   
    // FUNCTION RETURNS TRUE IF NAME IS VALID
    
    public boolean check_name(String fullName){
	    if(fullName!=null) {
		    if (!name_pattern.matcher(fullName).matches()) {
		    	return false;
		    }
		}
        return true;
    }
   
    // FUNCTION RETURNS TRUE IF PHONE-NUMBER IS VALID
    
    public boolean check_phone(String phoneNumber){
	    if(phoneNumber!=null) {
		    if (!phone_pattern.matcher(phoneNumber).matches()) {
		    	return false;
		    }
	    }
        return true;
    }
    
    // FUNCTION RETURNS TRUE IF AGE IS VALID
    
    public boolean check_age(Date dateOfBirth,String type) {
    	Calendar calendar_type = Calendar.getInstance();
		calendar_type.setTime(dateOfBirth);
		Calendar calendar_current = Calendar.getInstance();
		calendar_current.setTime(new Date());

		int age = calendar_current.get(Calendar.YEAR)-calendar_type.get(Calendar.YEAR);
		
		if((age>=25 && type=="parent") || (age>=20 && type=="leader") || (age>=7 && age<=14 && type=="kid")) {
			return true;
		}
    	return false;  	
    }
    
    public boolean check_zoom_link(String zoomLink) {
    	if(zoomLink!=null) {
    		if(!link_pattern.matcher(zoomLink).matches()) {
    			return false;
    		}
    	}
    	return true;
    }
    
    
    // FUNCTION RETURNS TRUE IF COURSE NAME IS VALID
    
    public boolean check_course_name(String course_name) {
    	if(course_name.length()>=2) {
    		return true;
    	}
    	return false;
    }
    
    // FUNCTION RETURNS TRUE IF COURSE DURATION IS VALID
    
    public boolean check_course_duration(Date startTime, Date endTime) {
    	Calendar calendar_start = Calendar.getInstance();
		calendar_start.setTime(startTime);
		Calendar calendar_end = Calendar.getInstance();
		calendar_end.setTime(endTime);
		
		if(calendar_start.get(Calendar.YEAR)  == calendar_end.get(Calendar.YEAR)
			&& calendar_start.get(Calendar.MONTH)  == calendar_end.get(Calendar.MONTH)
			&& calendar_start.get(Calendar.DAY_OF_MONTH)  == calendar_end.get(Calendar.DAY_OF_MONTH)){
			int duration = calendar_end.get(Calendar.HOUR)-calendar_start.get(Calendar.HOUR);
			if(duration<=12) {
				return true;
			}
		}
    	return false;
    }
    
    // FUNCTION RETURNS TRUE IF IMAGE IS VALID
    
    public boolean check_image(String image_extension) {
    	if(image_extension!=null) {
    		if(!image_pattern.matcher(image_extension).matches()) {
    			return false;
    		}
    	}
    	return true;
    }
    
    // FUNCTION RETURNS TRUE IF GENDER IS VALID
    
    public boolean check_gender(Gender gender) {
    	if(gender!=null) {
    		if(gender == Gender.Boy | gender == Gender.Girl | gender==Gender.NotRelevant) {
    			return true;
    		}
    	}
    	return false;
    }
    
}
