package com.example.madfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText email,password;
    Button btnLogin;

    FirebaseUser currentUser;//used to store current user of account
    FirebaseAuth mAuth;//Used for firebase authentication
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        loadingBar = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowUserToLogin();
            }
        });

    }

    private void AllowUserToLogin() {
        String eAddress = email.getText().toString().trim();
        String pwd = password.getText().toString();

        if(TextUtils.isEmpty(eAddress))
        {
            Toast.makeText(login.this,"Please enter email id",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pwd))
        {
            Toast.makeText(login.this,"Please enter password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Signing In...");
            loadingBar.setMessage("Please wait ,Because Good things always take time");
            mAuth.signInWithEmailAndPassword(eAddress,pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())//If account login successful print message and send user to main Activity
                            {
                                sendToMainActivity();
                                Toast.makeText(login.this,"Welcome to Reference Center",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else//Print the error message incase of failure
                            {
                                String msg = task.getException().toString();
                                Toast.makeText(login.this,"Error: "+msg,Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    protected void onStart(){
        super.onStart();
        if(currentUser!=null)
        {
            sendToMainActivity();
        }
    }
    private void sendToMainActivity() {
        //This is to send user to MainActivity
        Intent MainIntent = new Intent(login.this,MainActivity.class);
        startActivity(MainIntent);
    }
}
