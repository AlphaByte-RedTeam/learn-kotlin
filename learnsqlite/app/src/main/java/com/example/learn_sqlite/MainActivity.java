package com.example.learn_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    Button login;
    TextView textCreateAccount;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initCreateAccountTextView();
        sqliteHelper = new SqliteHelper(this);

        login.setOnClickListener(view -> {
            if (validate()) {
                String Email = editTextEmail.getText().toString();
                String Password = editTextPassword.getText().toString();
                User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));
                if (currentUser != null) {
                    Snackbar.make(login, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                    String userName = currentUser.userName;
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                } else {
                    Snackbar.make(login, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initCreateAccountTextView() {
        textCreateAccount = findViewById(R.id.txtCreateAccount);
        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editPassword);
        login = findViewById(R.id.btnLogin);
    }

    public boolean validate() {
        boolean isValidUser = false;

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValidUser = false;
            Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
        } else {
            isValidUser = true;
        }

        if (password.isEmpty()) {
            isValidUser = false;
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
        } else {
            isValidUser = true;
        }
        return isValidUser;
    }
}