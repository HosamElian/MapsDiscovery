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

public class MainActivity extends AppCompatActivity {
    EditText emailLoginET, passwordLoginET;
    TextView loginBT, signUpLoginBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signUpLoginBT = findViewById(R.id.signUpLoginBT);
        loginBT = findViewById(R.id.loginBT);
        emailLoginET = findViewById(R.id.emailLoginET);
        passwordLoginET = findViewById(R.id.passwordLoginET);

        signUpLoginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignPage = new Intent(view.getContext(), SignupActivity.class);
                startActivity(goToSignPage);
            }
        });

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInputs(emailLoginET.getText().toString(), passwordLoginET.getText().toString()) ){
                    Intent goToMap = new Intent(view.getContext(), MapActivity.class);
                    startActivity(goToMap);
                }else{
                    Toast.makeText(view.getContext(), "invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateInputs(String email, String password) {

        boolean emailValid = email.length() > 10 && email.contains("@");
        boolean passwordValid = password.length() > 7;
        return emailValid & passwordValid;
    }
}