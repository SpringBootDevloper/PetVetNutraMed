package com.Atul.PetVetNutraMed.Interface;


import com.Atul.PetVetNutraMed.ApiResponse.ApiResponse;
import com.Atul.PetVetNutraMed.Entity.Feedback;

public interface FeedbackInterface {
public ApiResponse sendFeedback(Feedback feedbackDetails);
}
