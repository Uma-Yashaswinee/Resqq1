package com.example.resqq1.UserActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.resqq1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserBookingActivity extends AppCompatActivity {

    TextView  legaluser_name_book, legaluser_phone_book,
            legaluser_location_book, legaluser_email_book;
    Button makebooking;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_booking);


        legaluser_name_book = findViewById(R.id.legaluser_name_book);
        legaluser_phone_book = findViewById(R.id.legaluser_phone_book);
        legaluser_location_book = findViewById(R.id.legaluser_location_book);
        legaluser_email_book = findViewById(R.id.legaluser_email_book);

        makebooking = findViewById(R.id.makebooking);

        //catching data from wokrer details to show in user booking
        Intent intent1 = getIntent();
        String name1 = intent1.getStringExtra("name");
        legaluser_name_book.setText(name1);

        Intent intent2 = getIntent();
        String phone1 = intent2.getStringExtra("phone");
        legaluser_phone_book.setText(phone1);

        Intent intent3 = getIntent();
        String location1 = intent3.getStringExtra("location");
        legaluser_location_book.setText(location1);

        Intent intent4 = getIntent();
        String email1 = intent4.getStringExtra("email");
        legaluser_email_book.setText(email1);


        //alert for the confirm bookings
        builder = new AlertDialog.Builder(this);
        makebooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(legaluser_name_book.getText().toString().length()==0 ||
                        legaluser_phone_book.getText().toString().length()==0 ||
                        legaluser_location_book.getText().toString().length()==0 ||
                        legaluser_email_book.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter All Values....", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage("") .setTitle("CONFIRM BOOKING");

                //Setting message manually and performing action on button click
                builder.setMessage("Confirm Booking of hiring the service provider ?")
                        .setCancelable(false)
                        .setPositiveButton("Confirm Booking", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Booking Confirmed!",
                                        Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("Cancel Booking", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Booking Cancelled!",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("CONFIRM BOOKING");
                alert.show();
            }
        });




    }


}

