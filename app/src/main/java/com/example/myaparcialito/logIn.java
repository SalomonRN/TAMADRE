package com.example.myaparcialito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;

public class logIn extends AppCompatActivity {
    EditText email;
    EditText pass;
    Button log;
    boolean res = false;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference usuariosRef = mDatabase.getReference().child("usuarios");
    private Bundle extras = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        email = findViewById(R.id.email);
        pass = findViewById(R.id.edit_pass_log);
        log = findViewById(R.id.login);

        log.setOnClickListener(view -> {
            obtenerEdadPorNombre(email.getText().toString(), pass.getText().toString());

        });
    }
    private void obtenerEdadPorNombre(String nombreIngresado, String passwordTxts) {

        usuariosRef.orderByChild("username").equalTo(nombreIngresado).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()) {
                        String password = usuarioSnapshot.child("password").getValue(String.class);
                        if (password != null && password.equals(passwordTxts)) {
                            String nombre = usuarioSnapshot.child("username").getValue(String.class);
                            System.out.println("JSAFJDASKLNFKASNFAS::::::::::::::::::::::");
                            System.out.println(nombre);
                            extras.putString("NOMBRE_USER", nombre);
                            Intent intent = new Intent(logIn.this, destino.class);
                            intent.putExtras(extras);
                            startActivity(intent);


                        }else
                        {
                            Toast.makeText(logIn.this, "USUARIO O CONTRASEÑA INCORRECTAS" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(logIn.this, "USUARIO O CONTRASEÑA INCORRECTAS" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de base de datos, si es necesario
            }
        });

    }
    }


