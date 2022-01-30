package com.example.resqq1.LegalUserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.resqq1.R;
import com.example.resqq1.UserActivity.UsersListActivity;

public class LegalUserDashBoardActivity extends AppCompatActivity {

    TextView textViewName1;
    CardView book,logout1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legaluser_dashboard);


        book = findViewById(R.id.book);
        logout1 = findViewById(R.id.logout1);

        textViewName1 = findViewById(R.id.workershowname);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName1.setText(emailFromIntent);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LegalUserDashBoardActivity.this, UsersListActivity.class);
                startActivity(i);
            }
        });

        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(LegalUserDashBoardActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();
            }
        });

    }
}
