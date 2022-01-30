package com.example.resqq1.UserActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.resqq1.LegalUserActivity.LegalUserDetailsAcivity;
import com.example.resqq1.R;

public class ChatActivity extends AppCompatActivity {

    TextView legaluser_details_name1, legaluser_details_email1, legaluser_details_phone1, legaluser_details_location1;
    Button  chat1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatactivity);

        legaluser_details_name1 = findViewById(R.id.legaluser_details_name1);
        legaluser_details_email1 = findViewById(R.id.legaluser_details_email1);
        legaluser_details_phone1 = findViewById(R.id.legaluser_details_phone1);
        legaluser_details_location1 = findViewById(R.id.legaluser_details_location1);


        Intent i1 = getIntent();
        String name12 = i1.getStringExtra("name");
        legaluser_details_name1.setText(name12);

        Intent i2 = getIntent();
        String email12 = i2.getStringExtra("email");
        legaluser_details_email1.setText(email12);

        Intent i3 = getIntent();
        String location12 = i3.getStringExtra("location");
        legaluser_details_location1.setText(location12);

        Intent i4 = getIntent();
        String phone12 = i4.getStringExtra("phone");
        legaluser_details_phone1.setText(phone12);



        chat1 = findViewById(R.id.chat);

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(legaluser_details_name1.getText().toString().length()==0 ||
                        legaluser_details_email1.getText().toString().length()==0 ||
                        legaluser_details_location1.getText().toString().length()==0 ||
                        legaluser_details_phone1.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter All Values....", Toast.LENGTH_SHORT).show();
                    return;
                }

                  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone12));
                intent.putExtra("sms_body", "Hello, I wanted to enquiry about the Case!");
                startActivity(intent);
            }
        });



    }
}
