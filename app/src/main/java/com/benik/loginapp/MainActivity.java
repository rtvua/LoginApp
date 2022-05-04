package com.benik.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText formEmail;
        EditText formPassword;
        TextView register;
        Button loginBtn = findViewById(R.id.btn_login);
        DatabaseHelper db;


        db = new DatabaseHelper(this);
        formEmail = (EditText) findViewById(R.id.txt_email);
        formPassword = (EditText) findViewById(R.id.txt_password);
        register = (TextView) findViewById(R.id.view_register);
        loginBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String email = formEmail.getText().toString().trim();
                String password = formPassword.getText().toString().trim();
                String username = email.substring(0, email.indexOf("@"));
                if (db.isUserRegistered(email, password)) {
                    // We can create an Intent and go to another activity or screen, else show a Toast Message
                    Intent moveToWelcomePage = new Intent(MainActivity.this, WelcomeActivity.class);
                    moveToWelcomePage.putExtra("username", username);
                    startActivity(moveToWelcomePage);
                    Toast.makeText(MainActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User does not Exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}