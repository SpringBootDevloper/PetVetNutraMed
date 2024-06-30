package com.Atul.PetVetNutraMed.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Atul.PetVetNutraMed.ApiResponse.ApiResponse;
import com.Atul.PetVetNutraMed.Entity.Feedback;
import com.Atul.PetVetNutraMed.Interface.FeedbackInterface;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FeedbackService implements FeedbackInterface{
	@Autowired
	private JavaMailSender mailSender;
	@Value("${from}")
	String from;
	@Override
	public ApiResponse sendFeedback(Feedback feedbackDetails) {
		try {
			String to = "divyanshnigamoct13@gmail.com";
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			String name = String.valueOf(feedbackDetails.getName());
			String number = String.valueOf(feedbackDetails.getNumber());
			String feedback =String.valueOf(feedbackDetails.getFeedback());
			String subject="Feedback from customer";
			 String content = "Feedback details \n\n"
			            + "For your reference, here are details:\n"
			            + "Phone Number: " + number + "\n\n"
			            + "Name: "+ name +"\n\n"
			            + "Feedback: "+ feedback +"\n\n";
			simpleMailMessage.setTo(to);
			simpleMailMessage.setText(content);
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setSubject(subject);
			mailSender.send(simpleMailMessage);
			log.info("Mail Object created");
			return new ApiResponse(200, "Feedback Sent to "+to, "null", null);
		} catch (Exception e) {
			log.error("Error while sending email", e);
			return new ApiResponse(500, "INTERNAL SERVER ERROR", e.getMessage(), null);
		}
	}

}
