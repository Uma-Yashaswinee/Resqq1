package com.example.resqq1.LegalUserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.work.Worker;


import com.example.resqq1.DatabaseHelper.LegalUserDatabaseHelper;
import com.example.resqq1.InputValidation;
import com.example.resqq1.LoginActivity;
import com.example.resqq1.Model.LegalUser;
import com.example.resqq1.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Legal_User_Registration extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = Legal_User_Registration.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName1, textInputLayoutEmail1, textInputLayoutPhone1, textInputLayoutPassword1,
            textInputLayoutConfirmPassword1, textInputLayoutlocation1 ;
    private TextInputEditText textInputEditTextName1, textInputEditTextEmail1, textInputEditTextPassword1,
            textInputEditTextPhone1, textInputEditTextConfirmPassword1, textInputEditTextlocation1;
    private Button buttonregister1;
    private TextView loginlink1;
    private InputValidation inputValidation;
    private LegalUserDatabaseHelper legalUserDatabaseHelper;
    private LegalUser legalUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_user_registration);
//        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }


    private void initViews() {
        nestedScrollView =  findViewById(R.id.nestedScrollView);
        textInputLayoutName1 =  findViewById(R.id.textInputLayoutName1);
        textInputLayoutEmail1 =  findViewById(R.id.textInputLayoutEmail1);
        textInputLayoutPassword1 =  findViewById(R.id.textInputLayoutPassword1);
        textInputLayoutConfirmPassword1 =  findViewById(R.id.textInputLayoutConfirmPassword1);
        textInputLayoutPhone1 = findViewById(R.id.textInputLayoutPhone1);
        textInputLayoutlocation1 = findViewById(R.id.textInputLayoutlocation1);


        textInputEditTextName1 =  findViewById(R.id.textInputEditTextName1);
        textInputEditTextEmail1 =  findViewById(R.id.textInputEditTextEmail1);
        textInputEditTextPassword1 =  findViewById(R.id.textInputEditTextPassword1);
        textInputEditTextPhone1 = findViewById(R.id.textInputEditTextPhone1);
        textInputEditTextConfirmPassword1 =  findViewById(R.id.textInputEditTextConfirmPassword1);
        textInputEditTextlocation1 = findViewById(R.id.textInputEditTextlocation1);


        buttonregister1 =  findViewById(R.id.buttonregister1);
        loginlink1 =  findViewById(R.id.loginlink1);
    }

    private void initListeners() {
        buttonregister1.setOnClickListener(this);
        loginlink1.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        legalUserDatabaseHelper = new LegalUserDatabaseHelper(activity);
        legalUser = new LegalUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonregister1:
                postDataToSQLite();
                break;
            case R.id.loginlink1:
                Intent i = new Intent (Legal_User_Registration.this, LoginActivity.class);
                startActivity(i);
                break;
        }
    }

    //validate
    private void postDataToSQLite() {

        //validations
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName1, textInputLayoutName1, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail1, textInputLayoutEmail1, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail1, textInputLayoutEmail1, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword1, textInputLayoutPassword1, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword1, textInputEditTextConfirmPassword1,
                textInputLayoutConfirmPassword1, getString(R.string.error_password_match))) {
            return;
        }
        if (!inputValidation.isInputEditTextPhone(textInputEditTextPhone1, textInputLayoutPhone1, getString(R.string.error_message_phone))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextlocation1, textInputLayoutlocation1, getString(R.string.error_message_location))) {
            return;
        }

        if (!legalUserDatabaseHelper.checkLegalUser(textInputEditTextEmail1.getText().toString().trim())) {
            legalUser.setName(textInputEditTextName1.getText().toString().trim());
            legalUser.setEmail(textInputEditTextEmail1.getText().toString().trim());
            legalUser.setPhone(textInputEditTextPhone1.getText().toString().trim());
            legalUser.setPassword(textInputEditTextPassword1.getText().toString().trim());
            legalUser.setLocation(textInputEditTextlocation1.getText().toString().trim());


            legalUserDatabaseHelper.addLegalUser(legalUser);
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();

           /* Intent i = new Intent(ServiceRegisterActivity.this, LoginActivity.class);
            startActivity(i);*/
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName1.setText(null);
        textInputEditTextEmail1.setText(null);
        textInputEditTextPassword1.setText(null);
        textInputEditTextPhone1.setText(null);
        textInputEditTextConfirmPassword1.setText(null);
        textInputEditTextlocation1.setText(null);

    }
}