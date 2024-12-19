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
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    EditText email,password,confirmpass,fullname,phone,ic;
    Button register;


    private FirebaseAuth mAuth;//Used for firebase authentication
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmpass = (EditText) findViewById(R.id.confirmpassword);
        fullname = (EditText) findViewById(R.id.fullname);
        phone = (EditText) findViewById(R.id.phone);
        ic = (EditText) findViewById(R.id.nric);
        register = (Button) findViewById(R.id.btnregister);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount() {
        String eAddress = email.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String cpwd = confirmpass.getText().toString().trim();
        String fn = fullname.getText().toString().trim();
        String nric = ic.getText().toString().trim();
        String num = phone.getText().toString().trim();


        if(TextUtils.isEmpty(eAddress))
        {
            Toast.makeText(register.this,"Please enter email id",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pwd))
        {
            Toast.makeText(register.this,"Please enter password",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(num))
        {
            Toast.makeText(register.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(nric))
        {
            Toast.makeText(register.this,"Please enter your identification number",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(fn))
        {
            Toast.makeText(register.this,"Please enter your full name",Toast.LENGTH_SHORT).show();
        }
        if(!pwd.equals(cpwd))
        {
            Toast.makeText(register.this,"Password does not match",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait, we are creating new Account");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(eAddress,pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult> (){
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())//If account creation successful print message and send user to Login Activity
                            {
                                user user = new user(eAddress,pwd,fn,num,nric);
//                                FirebaseDatabase.getInstance().getReference("user")
                                sendUserToLoginActivity();
                                Toast.makeText(register.this,"Account created successfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else//Print the error message incase of failure
                            {
                                String msg = task.getException().toString();
                                Toast.makeText(register.this,"Error: "+msg,Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void sendUserToLoginActivity() {
        //This is to send user to Login Activity.
        Intent loginIntent = new Intent(register.this,login.class);
        startActivity(loginIntent);
    }
}
