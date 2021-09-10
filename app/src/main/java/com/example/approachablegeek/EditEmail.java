package com.example.approachablegeek;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditEmail extends AppCompatActivity {
    Button update;
    SharedPreferences sharedPreferences;
    EditText userEmail;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_email);
        sharedPreferences = getSharedPreferences("com.example.approachablegeek", Context.MODE_PRIVATE);
        update = findViewById(R.id.updateEmail);
        userEmail = findViewById(R.id.newEmail);
        Intent intent = getIntent();
        String email = intent.getStringExtra("currEmail");
        userEmail.setText(email);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView email = findViewById(R.id.newEmail);
                //TextView lastName = findViewById(R.id.newLastName);
                //sharedPreferences.edit().putString("userFirstName", firstName.getText().toString()).apply();
                sharedPreferences.edit().putString("userEmail", email.getText().toString()).apply();
                EditEmail.this.finish();
            }
        });
    }
}