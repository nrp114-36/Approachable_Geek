package com.example.approachablegeek;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    EditText userPhone;
    EditText userEmail;
    EditText userBio;
    ImageView userPic;
    SharedPreferences sharedPreferences;
    private int request_code = 100;
    private int PICK_IMAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.example.approachablegeek", Context.MODE_PRIVATE);
        userName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.userPhone);
        userEmail = findViewById(R.id.userEmail);
        userPic = findViewById(R.id.userImage);
        userBio = findViewById(R.id.userBio);

        updateFields();

        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userName = findViewById(R.id.userName);
                Intent intent = new Intent(MainActivity.this, EditName.class);
                intent.putExtra("currName", userName.getText().toString());
                startActivityForResult(intent, request_code);
            }
        });

        userPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userName = findViewById(R.id.userName);
                Intent intent = new Intent(MainActivity.this, EditNumber.class);
                intent.putExtra("currPhone", userPhone.getText().toString());
                startActivityForResult(intent, request_code);
            }
        });

        userEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText userName = findViewById(R.id.userName);
                Intent intent = new Intent(MainActivity.this, EditEmail.class);
                intent.putExtra("currEmail", userEmail.getText().toString());
                startActivityForResult(intent, request_code);
            }
        });

        userBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText userName = findViewById(R.id.userName);
                Intent intent = new Intent(MainActivity.this, EditBio.class);
                intent.putExtra("currBio", userBio.getText().toString());
                startActivityForResult(intent, request_code);
            }
        });

        userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == request_code){
            finish();
            startActivity(getIntent());
        }
        else if(requestCode == PICK_IMAGE){
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    //saving in shared pref
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();

                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    //textEncode.setText(encodedImage);
                    //SharedPreferences shre = sharedPreferences;
                    SharedPreferences.Editor edit=sharedPreferences.edit();
                    edit.putString("image_data",encodedImage);
                    edit.apply();

                    userPic.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(MainActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateFields() {
        String firstName = sharedPreferences.getString("userFirstName", "");
        String lastName = sharedPreferences.getString("userLastName", "");
        String number = sharedPreferences.getString("userNumber", "");
        String email = sharedPreferences.getString("userEmail", "");
        String bio = sharedPreferences.getString("userBio", "");
        String previouslyEncodedImage = sharedPreferences.getString("image_data", "");

        userName.setText(firstName + " " + lastName);
        userEmail.setText(email);
        userPhone.setText(number);
        userBio.setText(bio);

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            userPic.setImageBitmap(bitmap);
        }
    }

    public void reset(View v){
        //SharedPreferences sharedPreferences = getSharedPreferences("com.example.authotp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        finish();
        startActivity(getIntent());
    }
}