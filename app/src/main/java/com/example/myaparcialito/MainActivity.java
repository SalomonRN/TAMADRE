package com.example.myaparcialito;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    Button logIn;
    Button signUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = findViewById(R.id.Ingresar);
        signUp = findViewById(R.id.Registrar);
        Bundle extras = new Bundle();

        extras.putString("usuario", "user.getName()");



        logIn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, logIn.class);
            intent.putExtras(extras);
            startActivity(intent);
        });
        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, signUp.class);
            intent.putExtras(extras);
            startActivity(intent);
        });

        Button ja = findViewById(R.id.ja);
        ja.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, destino.class);
            intent.putExtras(extras);
            startActivity(intent);
        });
    }


}