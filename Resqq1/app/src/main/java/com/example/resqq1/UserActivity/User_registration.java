package com.example.resqq1.UserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;


import com.example.resqq1.DatabaseHelper.UserDatabaseHelper;
import com.example.resqq1.InputValidation;
import com.example.resqq1.LegalUserActivity.Legal_User_Registration;
import com.example.resqq1.LoginActivity;
import com.example.resqq1.Model.User;
import com.example.resqq1.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class User_registration extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = User_registration.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName, textInputLayoutEmail, textInputLayoutPassword,
            textInputLayoutConfirmPassword , textInputLayoutPhone ;
    private TextInputEditText textInputEditTextName, textInputEditTextEmail, textInputEditTextPassword,
            textInputEditTextConfirmPassword, textInputEditTextPhone ;
    private Button buttonregister;
    private TextView loginlink;
    private InputValidation inputValidation;
    private UserDatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        initViews();
        initListeners();
        initObjects();
    }


    private void initViews() {
        nestedScrollView =  findViewById(R.id.nestedScrollView);
        textInputLayoutName =  findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail =  findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword =  findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword =  findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);

        textInputEditTextName =  findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail =  findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword =  findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword =  findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextPhone = findViewById(R.id.textInputEditTextPhone);

        buttonregister =  findViewById(R.id.buttonregister);
        loginlink =  findViewById(R.id.loginlink);
    }

    private void initListeners() {
        buttonregister.setOnClickListener(this);
        loginlink.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new UserDatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonregister:
                postDataToSQLite();
                break;
            case R.id.loginlink:
                Intent i = new Intent (User_registration.this, LoginActivity.class);
                startActivity(i);
                break;
        }
    }

    //validate
    private void postDataToSQLite() {
        //name
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }

        //email filled
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        //email
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        //phone
        if (!inputValidation.isInputEditTextPhone(textInputEditTextPhone, textInputLayoutPhone, getString(R.string.error_message_phone))) {
            return;
        }

        //pass
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        //confirm pass
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setPhone(textInputEditTextPhone.getText().toString().trim());
            databaseHelper.addUser(user);
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_SHORT).show();
            emptyInputEditText();
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextPhone.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}