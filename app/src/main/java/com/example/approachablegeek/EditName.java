package com.example.approachablegeek;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditName extends AppCompatActivity {

    Button update;
    EditText firstName;
    EditText lastName;

    SharedPreferences sharedPreferences;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        sharedPreferences = getSharedPreferences("com.example.approachablegeek", Context.MODE_PRIVATE);
        firstName = findViewById(R.id.newFirstName);
        lastName = findViewById(R.id.newLastName);
        String fName = sharedPreferences.getString("userFirstName", "");
        String lName = sharedPreferences.getString("userLastName", "");
        firstName.setText(fName);
        lastName.setText(lName);

        update = findViewById(R.id.updateName);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView firstName = findViewById(R.id.newFirstName);
                TextView lastName = findViewById(R.id.newLastName);
                sharedPreferences.edit().putString("userFirstName", firstName.getText().toString()).apply();
                sharedPreferences.edit().putString("userLastName", lastName.getText().toString()).apply();
                EditName.this.finish();
            }
        });
 }
}