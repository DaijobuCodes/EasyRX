package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText username;
    EditText password;
    EditText mobile;
    Button register_button;
    FirebaseAuth auth;

//    FirebaseFirestore fStore;

    TextView login;

    TextView goback;
    String fullname, age, gender, DOB,blood_grp, userID;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();
        register_button = findViewById(R.id.register_button);
        login = findViewById(R.id.loginText);
        goback = findViewById(R.id.goBackText);

        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        Intent intent=getIntent();
        fullname = intent.getStringExtra("fname");
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");
        DOB = intent.getStringExtra("DOB");
        blood_grp = intent.getStringExtra("bloodgrp");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registerp2.class);
                startActivity(intent);
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.username);
                password = findViewById(R.id.password);


                mobile = findViewById(R.id.mobile);
                String email, pass, mobile1;
                email = username.getText().toString();
                pass = password.getText().toString();
                mobile1 =mobile.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(mobile1)){
                    Toast.makeText(Register.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }


                //                if(username.getText().toString().equals("user")&& password.getText().toString().equals("1234")){
//                    Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this, "Signed Up successfully.", Toast.LENGTH_SHORT).show();
                                userID = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                                DocumentReference documentReference = fStore.collection("Users").document(userID);



                                Map<String, Object> user = new HashMap<>();
                                user.put("fName",fullname);
                                user.put("email",email);
                                user.put("phone", mobile1);
                                user.put("age", age);
                                user.put("DOB", DOB);
                                user.put("gender", gender);
                                user.put("bloodgroup", blood_grp);

//                                fStore.collection("user").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                    @Override
//                                    public void onSuccess(DocumentReference documentReference) {
//                                        Toast.makeText(getApplicationContext(), "Registered",Toast.LENGTH_LONG).show();
////                                        login();
//                                    }
//                                });
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "OnSuccess: user Profile is created for "+userID);
                                    }
                                });


                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("id",userID);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//    }
}