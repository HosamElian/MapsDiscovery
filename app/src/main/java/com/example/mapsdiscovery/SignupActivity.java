package com.example.mapsdiscovery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    EditText passwordConfirmationSignUpET,passwordSignUpET, emailSgiUpET;
    String message = "";
    TextView loginSignUpTV, signUpBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailSgiUpET = findViewById(R.id.emailSgiUpET);
        passwordSignUpET = findViewById(R.id.passwordSignUpET);
        passwordConfirmationSignUpET = findViewById(R.id.passwordConfirmationSignUpET);
        loginSignUpTV = findViewById(R.id.loginSignUpTV);
        signUpBT = findViewById(R.id.signUpBT);


        loginSignUpTV.setOnClickListener(view -> {
            Intent gotoLogin = new Intent(view.getContext(), MainActivity.class);
            startActivity(gotoLogin);
        });


        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateSignUp(emailSgiUpET.getText().toString(),
                                   passwordSignUpET.getText().toString(),
                                   passwordConfirmationSignUpET.getText().toString()))
                {
                    Intent gotoMap = new Intent(view.getContext(), MapActivity.class);
                    startActivity(gotoMap);
                }else{
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validateSignUp(String email, String password, String passwordConfirmation) {
        boolean emailValid = email.length() > 10 && email.contains("@");
        boolean passwordValid = password.length() > 7;
        boolean confirmPasswordValid = password.equals(passwordConfirmation);
        if(!emailValid){
            message = "invalid email ";
            return false;
        }
        if(!passwordValid){
            message = "invalid password ";
            return false;
        }
        if(!confirmPasswordValid){
            message = "invalid confirmed password not match password";
            return false;
        }
        return true;

    }
}