package com.example.resqq1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.resqq1.DatabaseHelper.LegalUserDatabaseHelper;
import com.example.resqq1.DatabaseHelper.UserDatabaseHelper;
import com.example.resqq1.UserActivity.UserDashboardActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class ForgetPassword extends AppCompatActivity {
    private final AppCompatActivity activity = ForgetPassword.this;

    private TextInputEditText inputEmail;
    private Button btnReset, btnBack;
    InputValidation inputValidation;
    UserDatabaseHelper userDatabaseHelper;
    NestedScrollView nestedScrollViewforget;
    LegalUserDatabaseHelper legalUserDatabaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        inputEmail = findViewById(R.id.emailf);
        btnBack = findViewById(R.id.btn_back);
        btnReset = findViewById(R.id.btn_reset_password);
        nestedScrollViewforget = findViewById(R.id.nestedScrollViewforget);

        userDatabaseHelper = new UserDatabaseHelper(this);
        legalUserDatabaseHelper = new LegalUserDatabaseHelper(this);
        inputValidation = new InputValidation(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              VerifyFromSqlite();

            }

            private void VerifyFromSqlite() {
                if(inputEmail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please give your Registered EmailID", Toast.LENGTH_SHORT).show();
                    return;
                }

                //for user
                if(userDatabaseHelper.checkUser(inputEmail.getText().toString().trim())){
                    Intent accountsIntent = new Intent(activity, ConfirmPassword.class);
                    accountsIntent.putExtra("EMAIL", inputEmail.getText().toString().trim());
                    emptyInputEditText();
                    startActivity(accountsIntent);
                } else {
                    // Snack Bar to show success message that record is wrong
                    Snackbar.make(nestedScrollViewforget,"Wrong email", Snackbar.LENGTH_SHORT).show();
                }


                //forlegaluser
                  if(legalUserDatabaseHelper.checkLegalUser(inputEmail.getText().toString().trim())){
                Intent accountsIntent = new Intent(activity, ConfirmPassword.class);
                accountsIntent.putExtra("EMAIL", inputEmail.getText().toString().trim());
                emptyInputEditText();
                startActivity(accountsIntent);
            } else {
                // Snack Bar to show success message that record is wrong
                      Snackbar.make(nestedScrollViewforget,"Wrong email", Snackbar.LENGTH_SHORT).show();
            }
        }


        private void emptyInputEditText() {
                inputEmail.setText("");
            }

        });

    }
}

