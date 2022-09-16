package com.example.learn_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button second = findViewById(R.id.button);
        second.setOnClickListener(view -> {
            Intent secondActivity = new Intent(this, SecondActivity.class);
            secondActivity.putExtra("nama", "Jenis");
            startActivity(secondActivity);
        });
    }
}