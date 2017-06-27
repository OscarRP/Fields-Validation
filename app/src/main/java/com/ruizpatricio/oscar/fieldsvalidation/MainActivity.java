package com.ruizpatricio.oscar.fieldsvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText name, email, password, repeatPassword, phone;
    private Button validateButton;
    private CheckBox agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setListeners();
    }

    // Method to get views
    private void getViews() {
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        repeatPassword = (EditText)findViewById(R.id.repeat_password);
        phone = (EditText)findViewById(R.id.phone);
        validateButton = (Button)findViewById(R.id.validate);
        agree = (CheckBox)findViewById(R.id.agree);
    }

    // Method to set listeners
    private void setListeners() {
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });
    }

    // Method to check if all fields are correctly filled
    private void checkFields() {

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "You must fill name field", Toast.LENGTH_SHORT).show();

        } else if (phone.getText().toString().isEmpty()) {
            Toast.makeText(this, "You must fill phone field", Toast.LENGTH_SHORT).show();

        } else if (!checkEmail(email.getText().toString())) {
            Toast.makeText(this, "You must fill email field", Toast.LENGTH_SHORT).show();

        } else if (checkPasswords(password.getText().toString(), repeatPassword.getText().toString()) != 0) {
            switch (checkPasswords(password.getText().toString(), repeatPassword.getText().toString())) {
                case 1:
                    Toast.makeText(this, "Password field is empty", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "Repeat password field is empty", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "Passwords doesn´t match", Toast.LENGTH_SHORT).show();
                    break;
            }

        } else if (!agree.isChecked()) {
            Toast.makeText(this, "You must check I Agree", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "All fields are correctly filled", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to validate email
    private boolean checkEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    // Method to validate passwords
    private int checkPasswords(String password, String repeatedPassword) {
        // Result code
        // 0 = passwords ok
        // 1 = password empty
        // 2 = repeated password empty
        // 3 = passwords doesnt match
        int code;
        if (password.isEmpty()) {
            code = 1;
        } else if (repeatedPassword.isEmpty()) {
            code = 2;
        } else if (!password.equals(repeatedPassword)) {
            code = 3;
        } else {
            code = 0;
        }
        return code;
    }
}
