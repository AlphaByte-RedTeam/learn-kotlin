package com.mobileprogramming.learnvolleyandmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.mobileprogramming.learnvolleyandmysql.config.Config;
import com.mobileprogramming.learnvolleyandmysql.controller.AppController;

import java.util.HashMap;
import java.util.Map;

public class AddData extends AppCompatActivity {

    String TAG = AddData.class.getSimpleName();
    String id = "";
    String action = "";
    EditText editTextNama;
    EditText editTextHarga;
    EditText editTextDeskripsi;
    Button add;
    Button edit;
    Button delete;
    Intent intent;
    Bundle bundle;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        editTextDeskripsi = findViewById(R.id.editTextDeskripsi);
        editTextNama = findViewById(R.id.editTextNama);
        editTextHarga = findViewById(R.id.editTextHarga);

        add = findViewById(R.id.btnAdd);
        edit = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelete);

        intent = getIntent();
        bundle = intent.getExtras();

        if (bundle != null) {
            id = bundle.getString("id");
            editTextNama.setText(bundle.getString("nama"));
            editTextHarga.setText(bundle.getString("harga"));
            editTextDeskripsi.setText(bundle.getString("deskripsi"));
        } else {
            edit.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        add.setOnClickListener(view -> {
            action = "add";
            kirimData();
        });

        edit.setOnClickListener(view -> {
            action = "add";
            kirimData();
        });

        delete.setOnClickListener(view -> {
            action = "add";
            kirimData();
        });
    }

    private void kirimData() {
        showProgressDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.senddata, response -> {
            hideProgressDialog();
            Toast.makeText(AddData.this, response, Toast.LENGTH_SHORT).show();
            editTextNama.setText(null);
            editTextHarga.setText(null);
            editTextDeskripsi.setText(null);

            if (action.equals("delete"))
                finish();
        }, error -> {
            VolleyLog.d(TAG, String.format("Error: %s", error.getMessage()));
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nama", String.valueOf(editTextNama.getText()));
                params.put("harga", String.valueOf(editTextHarga.getText()));
                params.put("deskripsi", String.valueOf(editTextDeskripsi.getText()));
                params.put("action", action);
                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void showProgressDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.hide();
    }

}