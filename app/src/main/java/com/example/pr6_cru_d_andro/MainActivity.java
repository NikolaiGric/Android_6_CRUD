package com.example.pr6_cru_d_andro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private EditText editTextName, editTextEmail, editTextId;
    private TextView textViewResult;
    private Button buttonAdd, buttonRead, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextId = findViewById(R.id.editTextId);
        textViewResult = findViewById(R.id.textViewResult);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonRead = findViewById(R.id.buttonRead);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            databaseHelper.addUser(name, email);
            textViewResult.setText("User Added!");
        });

        buttonRead.setOnClickListener(v -> {
            Cursor cursor = databaseHelper.getUsers();
            StringBuilder result = new StringBuilder();
            while (cursor.moveToNext()) {
                result.append("ID: ").append(cursor.getInt(0))
                        .append(", Name: ").append(cursor.getString(1))
                        .append(", Email: ").append(cursor.getString(2))
                        .append("\n");
            }
            textViewResult.setText(result.toString());
        });

        buttonUpdate.setOnClickListener(v -> {
            int id = Integer.parseInt(editTextId.getText().toString());
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            databaseHelper.updateUser(id, name, email);
            textViewResult.setText("User Updated!");
        });

        buttonDelete.setOnClickListener(v -> {
            int id = Integer.parseInt(editTextId.getText().toString());
            databaseHelper.deleteUser(id);
            textViewResult.setText("User Deleted!");
        });
    }
}
