package com.example.myaparcialito;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    Button logIn;
    Button signUp;
    private DatabaseReference mDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = findViewById(R.id.Ingresar);
        signUp = findViewById(R.id.Registrar);
        Bundle extras = new Bundle();

        logIn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, logIn.class);
            startActivity(intent);
        });
        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, signUp.class);
            startActivity(intent);
        });

    }


}