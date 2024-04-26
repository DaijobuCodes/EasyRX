package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registerp2 extends AppCompatActivity {

    Spinner spinner_bloodgroup;
    EditText fName;
    EditText age;
    EditText gender;
    EditText DOB;

    Button next;

    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerp2);
        spinner_bloodgroup = findViewById(R.id.spinner_bloodgroup);
        next = findViewById(R.id.register_button_next);
        fName = findViewById(R.id.p_name);
        age = findViewById(R.id.p_age);
        gender = findViewById(R.id.p_gender);
        DOB = findViewById(R.id.p_DOB);
        login = findViewById(R.id.loginText);
//       Toast.makeText(Registerp2.this, "Kharab hai", Toast.LENGTH_SHORT).show();



       Toast.makeText(Registerp2.this, "Kharab hai", Toast.LENGTH_SHORT).show();

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.spinner_bloodgroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner_bloodgroup.setAdapter(adapter);

        String blood = spinner_bloodgroup.getSelectedItem().toString();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fName.getText().toString();
                String p_age = age.getText().toString();
                String p_gender = gender.getText().toString();
                String dob = DOB.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Register.class);
                intent.putExtra("fname", fullName);
                intent.putExtra("age", p_age);
                intent.putExtra("gender", p_gender);
                intent.putExtra("DOB", dob);
                intent.putExtra("bloodgrp", blood);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent1);
                finish();
            }
        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String fullName = fName.getText().toString();
//                String p_age = age.getText().toString();
//                String p_gender = gender.getText().toString();
//                String dob = DOB.getText().toString();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("fname", fullName);
//                intent.putExtra("age", p_age);
//                intent.putExtra("gender", p_gender);
//                intent.putExtra("DOB", dob);
//                startActivity(intent);
//            }
//        });


    }
}