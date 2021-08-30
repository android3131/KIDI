package com.example.demo.pkg1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
private JavaMailSender javaMailSender;


@Autowired
public NotificationService(JavaMailSender javaMailSender) {
	this.javaMailSender=javaMailSender;
	
}

public void sendNotification(String name) {
	
	SimpleMailMessage mail=new SimpleMailMessage();
	
	mail.setTo(name);
	mail.setFrom("haddadelie12@hotmail.com");
	mail.setSubject("you forgot your password");
	mail.setText("your new password is");
	
	javaMailSender.send(mail);

}



}
