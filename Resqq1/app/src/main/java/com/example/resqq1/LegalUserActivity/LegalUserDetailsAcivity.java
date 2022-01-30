package com.example.resqq1.LegalUserActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.resqq1.R;
import com.example.resqq1.UserActivity.ChatActivity;
import com.example.resqq1.UserActivity.UserBookingActivity;

public class LegalUserDetailsAcivity extends Activity {

    TextView legaluser_details_name, legaluser_details_email, legaluser_details_phone, legaluser_details_location;
    Button call, bookuserdetails, chat1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legal_user_details1);

        legaluser_details_name = findViewById(R.id.legaluser_details_name);
        legaluser_details_email = findViewById(R.id.legaluser_details_email);
        legaluser_details_phone = findViewById(R.id.legaluser_details_phone);
        legaluser_details_location = findViewById(R.id.legaluser_details_location);


        Intent i1 = getIntent();
        String name = i1.getStringExtra("name");
        legaluser_details_name.setText(name);

        Intent i2 = getIntent();
        String email = i2.getStringExtra("email");
        legaluser_details_email.setText(email);

        Intent i3 = getIntent();
        String location = i3.getStringExtra("location");
        legaluser_details_location.setText(location);

        Intent i4 = getIntent();
        String phone = i4.getStringExtra("phone");
        legaluser_details_phone.setText(phone);


        call = findViewById(R.id.call);
        bookuserdetails = findViewById(R.id.bookuserdetails);
        chat1 = findViewById(R.id.chat1);

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegalUserDetailsAcivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ phone));
                startActivity(callIntent);

              /*  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
                intent.putExtra("sms_body", "Hello, I wanted to enquiry about my Case!");
                startActivity(intent);*/
            }
        });


        bookuserdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //from worker details to booking
                Intent intent = new Intent(LegalUserDetailsAcivity.this, UserBookingActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("location", location);
                startActivity(intent);

            }
        });

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //from worker details to booking
                Intent intent = new Intent(LegalUserDetailsAcivity.this, ChatActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

    }
}
