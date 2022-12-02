package com.mobileprogramming.sistemabsen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();
    String action = "";
    TextView txt_nik;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txt_nik = findViewById(R.id.txt_nik);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        User user = SharedPrefManager.getInstance(this).getUser();

        txt_nik.setText(user.getUsername());

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    public void in(View view) {
        action = "in";
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dfin = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDatein = dfin.format(c.getTime());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat tfin = new SimpleDateFormat("HH:mm:ss");
        String formattedTimein = tfin.format(c.getTime());
        showProgressDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, URLs.absen, response ->
        {
            hideProgressDialog();
            try {
                JSONObject jObj = new JSONObject(response);
                Toast.makeText(this, jObj.getString("error_text"), Toast.LENGTH_SHORT).show();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            hideProgressDialog();
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("tanggal", formattedDatein);
                params.put("waktumasuk", formattedTimein);
                params.put("nik", String.valueOf(txt_nik.getText()));
                params.put("action", action);

                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }

    public void out(View view) {
        action = "out";
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dfout = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateout = dfout.format(c.getTime());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat tfout = new SimpleDateFormat("HH:mm:ss");
        String formattedTimeout = tfout.format(c.getTime());
        showProgressDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, URLs.absen, response -> {
            hideProgressDialog();
            try {
                JSONObject jObj = new JSONObject(response);
                Toast.makeText(this, jObj.getString("error_text"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) { e.printStackTrace(); }
        }, error -> {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            hideProgressDialog();
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("tanggal", formattedDateout);
                params.put("waktukeluar", formattedTimeout);
                params.put("nik", String.valueOf(txt_nik.getText()));
                params.put("action", action);

                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }

    public void keluar(View view) {
        //Intent intent = new Intent(this, LoginActivity.class);
        finish();
        //startActivity(intent);
        SharedPrefManager.getInstance(getApplicationContext()).logout();
    }
}