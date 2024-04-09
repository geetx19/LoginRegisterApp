package com.cscorner.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signupEmail,signupPass;
    private Button signupButton;
    TextView loginReadingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        signupEmail=findViewById(R.id.signupEmail);

        signupPass=findViewById(R.id.signupPass);
        signupButton=findViewById(R.id.signup_btn);
        loginReadingText=findViewById(R.id.loginReadingText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = signupEmail.getText().toString().trim();
                String pass = signupPass.getText().toString().trim();

                if (mail.isEmpty()) {
                    signupEmail.setError("Email cannot be empty!");
                }
                if(pass.isEmpty()){
                    signupPass.setError("Password cannot be empty!");
                }else {
                    auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(Register.this, "Signup Fail!! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        loginReadingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }
}