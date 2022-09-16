package com.example.luas_segitiga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username = findViewById(R.id.editUsername);
    EditText password = findViewById(R.id.editPassword);
    Button login = findViewById(R.id.btnLogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login.setOnClickListener(view -> {
            if (username.length() == 0 && password.length() == 0)
                Toast.makeText(getApplication(), "Username & Password belum diinput", Toast.LENGTH_LONG).show();
            else if (username.length() == 0)
                Toast.makeText(getApplication(), "Username belum diinput", Toast.LENGTH_LONG).show();
            else if (password.length() == 0)
                Toast.makeText(getApplication(), "Password belum diinput", Toast.LENGTH_LONG).show();
            else if (password.getText().toString().equals("1234567")) {
                Toast.makeText(getApplication(), "Login...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, HomeActivity.class);
                String users = username.getText().toString();
                intent.putExtra("Username", users);
                startActivity(intent);
            }
            else
                Toast.makeText(getApplication(), "Password salah", Toast.LENGTH_LONG).show();
        });
    }
}