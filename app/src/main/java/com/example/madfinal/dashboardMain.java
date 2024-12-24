package com.example.madfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboardMain extends AppCompatActivity {
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    TextView testtv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        testtv = (TextView) findViewById(R.id.testTV);

        testtv.setText(currentUser.getUid());


    }
}
