package com.Atul.PetVetNutraMed.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Atul.PetVetNutraMed.ApiResponse.ApiResponse;
import com.Atul.PetVetNutraMed.Entity.Feedback;
import com.Atul.PetVetNutraMed.Interface.FeedbackInterface;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/feedback")
@Log4j2
public class FeedbackController {
	@Autowired
    FeedbackInterface feedbackService;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@PostMapping("/sendFeedback")
	public ResponseEntity<ApiResponse> sendFeeback(@RequestBody Feedback feedback) {
		try {
			ApiResponse successResponse=feedbackService.sendFeedback(feedback);
			return ResponseEntity.status(200).body(successResponse);
		}
		catch(Exception e) {
			log.error("Exception occured in feedback Controller while sending mail "+e.getMessage());
			return ResponseEntity.status(500).body(new ApiResponse(500,"null",e.getMessage(),"null"));
		}
	}
	
	@Scheduled(fixedRate = 5000)
	@GetMapping("/keepAlive")
	public void keepInstanceAlive() {
		log.info("Calling to keep Alive");
	    String ratingsOfUser = restTemplate.getForObject("https://api.restful-api.dev/objects",String.class);
		log.info("Called "+ratingsOfUser);
	}
}
