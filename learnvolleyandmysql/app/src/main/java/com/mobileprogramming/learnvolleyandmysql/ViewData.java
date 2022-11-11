package com.mobileprogramming.learnvolleyandmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.mobileprogramming.learnvolleyandmysql.config.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewData extends AppCompatActivity {

    private final String TAG = ViewData.class.getSimpleName();
    ListView listView;
    ArrayList<Product> arrayListData = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        listView = findViewById(R.id.ListViewData);
        getData();

        listView.setOnItemClickListener(((parent, view, position, id)) -> {
            Intent intent = new Intent(ViewData.this, AddData.class);
            intent.putExtra("id", arrayListData.get(position).getId());
            intent.putExtra("nama", arrayListData.get(position).getNama());
            intent.putExtra("harga", arrayListData.get(position).getHarga());
            intent.putExtra("deskripsi", arrayListData.get(position).getDeskripsi());
        });
    }

    private void getData() {
        showProgressDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.getdata, response -> {
            hideProgressDialog();
            System.out.println();

            try {
                JSONObject response = new JSONObject(responses);
                Toast.makeText(ViewData.this, response.getString("error_text"), Toast.LENGTH_SHORT).show();
                JSONObject header = response.getJSONObject("data");
                Iterator<String> iterator = header.iterator();

                while (iterator.hasNext()) {
                    String key = iterator.next();
                    JSONObject item = (JSONObject) header.get(key);
                    Product product = new Product();
                    product.setId(item.getString("id"));
                    product.setNama(item.getString("nama"));
                    product.setHarga(item.getString("harga"));
                    product.setDeskripsi(item.getString("deskripsi"));
                    product.setCreated_at(item.getString("created_at"));
                    product.setUpdated_at(item.getString("updated_at"));
                    arrayListData.add(product);
                    Log.e(TAG, item.toString());
                    Log.e(TAG, item.getString("nama"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            System.out.println();
            VolleyLog.d(TAG, String.format("Error: %s", error.getMessage()));
            hideProgressDialog();
        });
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