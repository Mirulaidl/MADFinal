package com.example.madfinal;

public class Feedback {
    private float rating;
    private String feedback;

    // Default constructor for Firebase
    public Feedback() {
    }

    public Feedback(float rating, String feedback) {
        this.rating = rating;
        this.feedback = feedback;
    }

    public float getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }
}
