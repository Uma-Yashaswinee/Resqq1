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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ConfirmPassword extends AppCompatActivity {

    TextInputEditText newpassword, confimrpassword;
    TextInputLayout textinputlayoutconfirmpass, textinputlayoutnewpassword;
    Button reset1, backtologin;
    NestedScrollView nestedScrollView;
    InputValidation inputValidation;
    UserDatabaseHelper databaseHelper;
    LegalUserDatabaseHelper legalUserDatabaseHelper;
    String email;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_password);

        newpassword = findViewById(R.id.newpassword);
        confimrpassword = findViewById(R.id.confirmpassword);
        reset1 = findViewById(R.id.reset1);
        backtologin = findViewById(R.id.backtologin);
        textinputlayoutconfirmpass = findViewById(R.id.textinputlayoutconfirmpass);
        textinputlayoutnewpassword = findViewById(R.id.textinputlayoutnewpassword);
        nestedScrollView = findViewById(R.id.nestedScrollView123);
        databaseHelper = new UserDatabaseHelper(this);
        legalUserDatabaseHelper = new LegalUserDatabaseHelper(this);
        inputValidation = new InputValidation(this);

        Intent i = getIntent();
        email = i.getStringExtra("EMAIL");


        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePassword();
            }

        });
    }

        private void UpdatePassword() {
            String value1 = newpassword.getText().toString().trim();
            String value2 = confimrpassword.getText().toString().trim();

            if(value1.isEmpty() && value2.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please fill the details", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!value1.contentEquals(value2)){
                Toast.makeText(getApplicationContext(), "Password doesn't Match!", Toast.LENGTH_SHORT).show();
                return;
            }

            //user
            if(!databaseHelper.checkUser(email)){
                Snackbar.make(nestedScrollView,"email doesn't exist", Snackbar.LENGTH_SHORT).show();
                return;
            }
            else{
                databaseHelper.updatePassword(email, value1);
                Toast.makeText(getApplicationContext(), "Password reset Successfull!", Toast.LENGTH_SHORT).show();
                emptyInputEditText();

                Intent i = new Intent(ConfirmPassword.this, LoginActivity.class);
                startActivity(i);
                finish();
            }

            //legaluser
            if(!legalUserDatabaseHelper.checkLegalUser(email)){
                Snackbar.make(nestedScrollView,"email doesn't exist", Snackbar.LENGTH_SHORT).show();
                return;
            }
            else{
                legalUserDatabaseHelper.updatePassword(email, value1);
                Toast.makeText(getApplicationContext(), "Password reset Successfull!", Toast.LENGTH_SHORT).show();
                emptyInputEditText();

                Intent i = new Intent(ConfirmPassword.this, LoginActivity.class);
                startActivity(i);
                finish();
            }

        }


        private void emptyInputEditText() {
            newpassword.setText("");
            confimrpassword.setText("");
        }


}
