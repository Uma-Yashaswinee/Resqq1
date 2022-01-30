package com.example.resqq1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.resqq1.DatabaseHelper.LegalUserDatabaseHelper;
import com.example.resqq1.DatabaseHelper.UserDatabaseHelper;
import com.example.resqq1.LegalUserActivity.LegalUserDashBoardActivity;
import com.example.resqq1.UserActivity.UserDashboardActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity  extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private TextInputLayout textinputlayoutemail, textinputlayoutpassword;
    NestedScrollView nestedScrollView;
    private Button buttonlogin, btn_signupnow;
    private TextView forgetpassword;
    private InputValidation inputValidation;
    private UserDatabaseHelper databaseHelper;
    private LegalUserDatabaseHelper legalUserDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        databaseHelper = new UserDatabaseHelper(activity);
        legalUserDatabaseHelper = new LegalUserDatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

        initViews();
        initListeners();
        initObjects();
    }
    private void initViews() {
        nestedScrollView = findViewById(R.id.nestedScrollView);
        textinputlayoutemail = findViewById(R.id.textInputLayoutEmail);
        textinputlayoutpassword = findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        buttonlogin = findViewById(R.id.buttonlogin);
        btn_signupnow = findViewById(R.id.btn_signupnow);
        forgetpassword = findViewById(R.id.forgetpassword);
    }

    private void initListeners() {
        buttonlogin.setOnClickListener(this);
        btn_signupnow.setOnClickListener(this);
        forgetpassword.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new UserDatabaseHelper(activity);
        legalUserDatabaseHelper = new LegalUserDatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    //mtd to lsten the view
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonlogin:
                verifyFromSQLite();
                break;
            case R.id.btn_signupnow:
                Intent intent = new Intent(LoginActivity.this, Sign_up_main.class);
                startActivity(intent);
                break;
            case R.id.forgetpassword:
                Intent intent1 = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(intent1);
                break;
        }
    }

    //creditanails verification
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textinputlayoutemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textinputlayoutemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textinputlayoutpassword, getString(R.string.error_message_password))) {
            return;
        }


        //for user
        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            Intent accountsIntent = new Intent(activity, UserDashboardActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }


        //for worker
        if (legalUserDatabaseHelper.checkLegalUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            Intent workersIntent = new Intent(activity, LegalUserDashBoardActivity.class);
            workersIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(workersIntent);
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }


    //empty all input edittext
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}



