package com.example.demo.backend1;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Kid;
import com.example.demo.KidRepository;



@RestController
public class ThirdRegisterationScreenController {
	@Autowired
	private KidRepository kidRepository;
	
	@GetMapping("getallkids")
	public List<Kid> getAllKids(){
		return kidRepository.getAllKids();
	}
	

	@PostMapping("/createkid")
	public List<Kid> createKidByName(@RequestBody Kid kid){
		return kidRepository.createKid(kid);
	}
	
	@RequestMapping(path = "/spring-rest/fileserver/multiplefileupload/", method = RequestMethod.POST)
	  public ResponseEntity<String> processFile(@RequestParam("files") List<MultipartFile> files) throws IOException {
      for (MultipartFile file : files) {
          byte[] bytes = file.getBytes();

          System.out.println("avital File Name: " + file.getOriginalFilename());
          System.out.println("avital File Content Type: " + file.getContentType());
          System.out.println("avital File Content:\n" + new String(bytes));
      }

      return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
  }
	 
	 
	 
	@RequestMapping(path = "/spring-rest/fileserver/singlefileupload/", method = RequestMethod.POST)
	 public ResponseObj processFile(@RequestParam("file") MultipartFile file) throws IOException {

	    	 try{
	             if(file.isEmpty() ==false){
	                 System.out.println("Successfully Uploaded: "+ file.getOriginalFilename());
	                 
	                 byte[] bytes = file.getBytes();
	                 
	                 File f = new File("src/main/resources/targetFile.jpg");
	                 
	                 try (OutputStream os = new FileOutputStream(f)) {
	                     os.write(bytes);
	                 }

	                 System.out.println("avital File Name: " + file.getOriginalFilename());
	                 System.out.println("avital File Content Type: " + file.getContentType());
	                 //System.out.println("File Content:\n" + new String(bytes));
	                 ResponseObjectInfo respObj = new ResponseObjectInfo("success");
	                 return respObj;

	             }
	             else{
	                 System.out.println("avital ERROR");
	                 ResponseObjectInfo respObj = new ResponseObjectInfo("error");
	                 return respObj;

	             }
	         }
	         catch(Exception e){
	             ResponseObjectInfo respObj = new ResponseObjectInfo("avital ediot  " + e.getMessage());
	             return respObj;
	         }
	}

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public String handleHttpMediaTypeNotAcceptableException() {
	    return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
	}
}