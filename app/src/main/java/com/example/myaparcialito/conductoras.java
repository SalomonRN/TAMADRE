package com.example.myaparcialito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class conductoras extends AppCompatActivity {
    private Bundle extras = new Bundle();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conductoras);
        Button next = findViewById(R.id.btn_next);


        extras.putDouble("START_LONGITUDE", getIntent().getDoubleExtra("START_LONGITUDE", 9.9) );
        extras.putDouble("START_LATITUDE", getIntent().getDoubleExtra("START_LATITUDE", 9.9));
        extras.putDouble("END_LONGITUDE", getIntent().getDoubleExtra("END_LONGITUDE", 9.9));
        extras.putDouble("END_LATITUDE", getIntent().getDoubleExtra("END_LATITUDE", 9.9));
        extras.putString("NOMBRE_USER", getIntent().getStringExtra("NOMBRE_USER"));
        next.setOnClickListener(view -> {
            Intent intent = new Intent(this, finalMap.class);
            intent.putExtras(extras);
            startActivity(intent);

        });
    }

}
