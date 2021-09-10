package com.example.approachablegeek;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditNumber extends AppCompatActivity {
    Button update;
    SharedPreferences sharedPreferences;
    TextView userNumber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);
        sharedPreferences = getSharedPreferences("com.example.approachablegeek", Context.MODE_PRIVATE);
        update = findViewById(R.id.updateNumber);
        Intent intent = getIntent();
        String number = intent.getStringExtra("currPhone");
        userNumber = findViewById(R.id.newNumber);
        userNumber.setText(number);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView number = findViewById(R.id.newNumber);
                //TextView lastName = findViewById(R.id.newLastName);
                //sharedPreferences.edit().putString("userFirstName", firstName.getText().toString()).apply();
                if(number.length() == 10){
                    sharedPreferences.edit().putString("userNumber", number.getText().toString()).apply();
                    EditNumber.this.finish();
                }
                else{
                    number.setError("Valid Phone Number required");
                }

            }
        });
    }
}