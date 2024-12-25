package com.example.madfinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    private static final String TAG = "FeedbackActivity";

    private RatingBar ratingBar;
    private EditText feedbackText;
    private Button submitButton;

    // Firebase reference
    private DatabaseReference feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize views
        ratingBar = findViewById(R.id.ratingBar);
        feedbackText = findViewById(R.id.edtFeedback);
        submitButton = findViewById(R.id.btnSubmit);

        // Initialize Firebase Database reference
        feedbackRef = FirebaseDatabase.getInstance().getReference("Feedback");

        // Submit button listener
        submitButton.setOnClickListener(view -> submitFeedback());
    }

    private void submitFeedback() {
        // Get user input
        float rating = ratingBar.getRating();
        String feedback = feedbackText.getText().toString().trim();

        // Validate input
        if (rating == 0) {
            Toast.makeText(this, "Please give a rating!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(feedback)) {
            Toast.makeText(this, "Please write your feedback!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a feedback object
        Feedback feedbackData = new Feedback(rating, feedback);

        // Save feedback to Firebase
        feedbackRef.push().setValue(feedbackData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                        ratingBar.setRating(0);
                        feedbackText.setText("");
                    } else {
                        Toast.makeText(this, "Failed to submit feedback.", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error submitting feedback", task.getException());
                    }
                });
    }
}
