package com.example.myaparcialito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class signUp extends AppCompatActivity {

    Button button_register;
    EditText name;
    EditText email;
    EditText pasword;
    EditText age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        button_register = findViewById(R.id.button_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.emailSign);
        pasword = findViewById(R.id.contra);
        age = findViewById(R.id.age);


        button_register.setOnClickListener(view -> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            User nuevoUsuario = new User(name.getText().toString(), email.getText().toString(), pasword.getText().toString(), Integer.parseInt(age.getText().toString()));

            String claveUsuario = databaseReference.child("usuarios").push().getKey();

            databaseReference.child("usuarios").child(claveUsuario).setValue(nuevoUsuario);

            Intent intent = new Intent(this, logIn.class);
            startActivity(intent);
        });



        // -----------------

    }
}
