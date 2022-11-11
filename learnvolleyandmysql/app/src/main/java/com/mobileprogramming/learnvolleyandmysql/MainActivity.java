package com.mobileprogramming.learnvolleyandmysql;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button viewData;
    Button addData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewData = (Button) findViewById(R.id.btnViewData);
        addData = (Button) findViewById(R.id.btnAddData);

        viewData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewData.class);
            startActivity(intent);
        });

        addData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddData.class);
           startActivity(intent);
        });
    }
}