package com.example.learn_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button logout;
    Button mail;
    Button phone;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcome = findViewById(R.id.txtWelcome);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            welcome.setText("Welcome " + bundle.getString("USERNAME"));
        }

        logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(view -> {
            Intent logoutIntent = new Intent(this, MainActivity.class);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logoutIntent);
        });

        mail = findViewById(R.id.btnMail);
        mail.setOnClickListener(view -> {
            sendMessage();
        });

        phone = findViewById(R.id.btnPhone);
        phone.setOnClickListener(view -> {
            makePhoneCall();
        });
    }

    private void sendMessage() {
        String email = "janiceclarest10@gmail.com";
        String subject = "Hello Beb <3";
        String message = "I love you so much!";

        String mailTo =
                "mailto:" + email +
                "?&subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(message);

        Intent intentMail = new Intent(Intent.ACTION_SENDTO);
        intentMail.setData(Uri.parse(mailTo));
        try {
            startActivity(Intent.createChooser(intentMail, "Send mail..."));
        } catch (Exception e) {
            Toast.makeText(this, "Exception " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void makePhoneCall() {
        Intent intentPhone = new Intent(Intent.ACTION_DIAL);
        intentPhone.setData(Uri.parse("tel: 089630497191"));
        try {
            startActivity(intentPhone);
        } catch (Exception e) {
            Toast.makeText(this, "Exception " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}