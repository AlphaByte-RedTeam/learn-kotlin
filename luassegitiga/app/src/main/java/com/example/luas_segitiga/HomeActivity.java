package com.example.luas_segitiga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    EditText alas = findViewById(R.id.editAlas);
    EditText tinggi = findViewById(R.id.editTinggi);
    EditText luas = findViewById(R.id.textLuas);
    TextView user = findViewById(R.id.textUser);
    Button hitung = findViewById(R.id.btnHitung);
    Button clear = findViewById(R.id.btnClear);
    Button logout = findViewById(R.id.btnLogout);
    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (bundle != null)
            user.setText(String.format("Welcome, %s", bundle.getString("Username")));

        logout.setOnClickListener(view -> {
            Intent logoutIntent = new Intent(this, MainActivity.class);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        hitung.setOnClickListener(view -> {
            if (alas.length() == 0 && tinggi.length() == 0)
                Toast.makeText(getApplication(), "Alas & Tinggi belum diinput", Toast.LENGTH_LONG).show();
            else if (alas.length() == 0)
                Toast.makeText(getApplication(), "Alas belum diinput", Toast.LENGTH_LONG).show();
            else if (tinggi.length() == 0)
                Toast.makeText(getApplication(), "Tinggi belum diinput", Toast.LENGTH_LONG).show();
            else {
                String alasStr = alas.getText().toString();
                String tinggiStr = tinggi.getText().toString();
                double alasDbl = Double.parseDouble(alasStr);
                double tinggiDbl = Double.parseDouble(tinggiStr);
                double result = LuasSegitiga(alasDbl, tinggiDbl);
                luas.setText(String.valueOf(result));
            }
        });

        clear.setOnClickListener(view -> {
            clear();
        });
    }

    private void clear() {
        alas.getText().clear();
        tinggi.getText().clear();
        luas.getText().clear();
    }

    private double LuasSegitiga(double alasParam, double tinggiParam) {
        return (alasParam * tinggiParam) / 2;
    }
}