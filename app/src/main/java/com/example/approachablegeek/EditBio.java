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

public class EditBio extends AppCompatActivity {
    Button update;
    EditText userBio;
    SharedPreferences sharedPreferences;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio);
        sharedPreferences = getSharedPreferences("com.example.approachablegeek", Context.MODE_PRIVATE);
        update = findViewById(R.id.updateBio);
        userBio = findViewById(R.id.newBio);
        Intent intent = getIntent();
        String bio = intent.getStringExtra("currBio");
        userBio.setText(bio);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView bio = findViewById(R.id.newBio);
                //TextView lastName = findViewById(R.id.newLastName);
                //sharedPreferences.edit().putString("userFirstName", firstName.getText().toString()).apply();
                sharedPreferences.edit().putString("userBio", bio.getText().toString()).apply();
                EditBio.this.finish();
            }
        });
    }
}