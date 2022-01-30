package com.example.resqq1.UserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.example.resqq1.DatabaseHelper.LegalUserDatabaseHelper;
import com.example.resqq1.DatabaseHelper.UserDatabaseHelper;
import com.example.resqq1.LegalUserActivity.LegalUserListActivity;
import com.example.resqq1.R;


public class UserDashboardActivity extends AppCompatActivity {
    CardView logout, connections, userbookings, chatting;
    private UserDatabaseHelper userDatabaseHelper;
    private LegalUserDatabaseHelper legalUserDatabaseHelper;
    TextView textViewName1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logout = findViewById(R.id.logout);
        connections = findViewById(R.id.connections);
        userbookings = findViewById(R.id.userbookings);
        chatting = findViewById(R.id.chatting);

        textViewName1 = findViewById(R.id.showname);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName1.setText(emailFromIntent);

        chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (UserDashboardActivity.this, ChatActivity.class);
                startActivity(i);
            }
        });

        userbookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (UserDashboardActivity.this, UserBookingActivity.class);
                startActivity(i);
            }
        });

        connections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (UserDashboardActivity.this, LegalUserListActivity.class);
                startActivity(i);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(UserDashboardActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();
            }
        });
    }
}
