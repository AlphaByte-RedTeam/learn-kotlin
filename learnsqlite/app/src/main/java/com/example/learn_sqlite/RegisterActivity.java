package com.example.learn_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextPassword;
    EditText editTextEmail;
    Button buttonRegister;
    TextView textViewLogin;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initViews();
        initTextViewLogin();
        buttonRegister.setOnClickListener(view -> {
            if (validate()) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                if (!sqliteHelper.isEmailExists(email)) {
                    User user = new User(null, userName, email, password);
                    sqliteHelper.addUser(user);
                    Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, Snackbar.LENGTH_LONG);
                } else {
                    Toast.makeText(RegisterActivity.this, "User already exists with same email ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initTextViewLogin() {
        textViewLogin = findViewById(R.id.txtCreateAccount);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editEmailRegis);
        editTextPassword = findViewById(R.id.editPasswordRegis);
        editTextUserName = findViewById(R.id.editUsernameRegis);
        buttonRegister = findViewById(R.id.btnRegister);
    }

    public boolean validate() {
        boolean isValidUser = false;

        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValidUser = false;
            Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
        } else {
            isValidUser = true;
        }

        if (password.isEmpty()) {
            isValidUser = false;
            Toast.makeText(getApplicationContext(), "Please enter valid password", Toast.LENGTH_SHORT).show();
        } else {
            isValidUser = true;
        }

        if (userName.isEmpty()) {
            isValidUser = false;
            Toast.makeText(getApplicationContext(), "Please enter valid username", Toast.LENGTH_SHORT).show();
        } else {
            isValidUser = true;
        }

        return isValidUser;
    }
}