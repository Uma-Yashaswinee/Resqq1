package com.example.resqq1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resqq1.LegalUserActivity.Legal_User_Registration;
import com.example.resqq1.UserActivity.User_registration;

public class Sign_up_main extends AppCompatActivity {
    private Button user, legaluser;
    TextView loginlink3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user = findViewById(R.id.user);
        legaluser = findViewById(R.id.legaluser);
        loginlink3 = findViewById(R.id.loginlink3);

        loginlink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_up_main.this, LoginActivity.class);
                startActivity(i);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_up_main.this, User_registration.class);
                startActivity(i);
            }
        });

        legaluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_up_main.this, Legal_User_Registration.class);
                startActivity(i);
            }
        });
    }
}