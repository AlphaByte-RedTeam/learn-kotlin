package com.example.learncrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editTextId;
    Button btnAddData, btnViewAll, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        myDb = new DatabaseHelper(this);
        editName = findViewById(R.id.editNameUpdate);
        editSurname = findViewById(R.id.editSurnameUpdate);
        editMarks = findViewById(R.id.editMarksUpdate);
        editTextId = findViewById(R.id.editTextIdUpdate);

        btnAddData = findViewById(R.id.btnAddUpdate);
        btnViewAll = findViewById(R.id.btnViewUpdate);
        btnUpdate = findViewById(R.id.btnUpdateUpdate);
        btnDelete = findViewById(R.id.btnDeleteUpdate);
        addData();
        updateData();
        deleteData();
        viewAll();
    }

    public void addData() {
        btnAddData.setOnClickListener(view -> {
            boolean isInserted = myDb.insertData(
                    editName.getText().toString(),
                    editSurname.getText().toString(),
                    editMarks.getText().toString()
            );
            if (isInserted) {
                Toast.makeText(UpdateActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UpdateActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(view -> {
            Cursor res = myDb.getAllData();
            if (res.getCount() == 0) {
                showMessage("Error", "Nothing found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID: ").append(res.getString(0)).append("\n");
                buffer.append("NAME: ").append(res.getString(1)).append("\n");
                buffer.append("SURNAME: ").append(res.getString(2)).append("\n");
                buffer.append("MARKS: ").append(res.getString(3)).append("\n\n");
            }
            showMessage("Data", buffer.toString());
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData() {
        btnUpdate.setOnClickListener(view -> {
            boolean isUpdate = myDb.updateData(
                    editTextId.getText().toString(),
                    editName.getText().toString(),
                    editSurname.getText().toString(),
                    editMarks.getText().toString()
            );
            if (isUpdate) {
                Toast.makeText(UpdateActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UpdateActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteData() {
        btnDelete.setOnClickListener(view -> {
            Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
            if (deletedRows > 0) {
                Toast.makeText(UpdateActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UpdateActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}