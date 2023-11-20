package com.example.myaparcialito;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.StrokeStyle;
import com.google.android.gms.maps.model.StyleSpan;

public class finalMap extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private double START_LONGITUDE;

    private double START_LATITUDE;
    private double END_LATITUDE;
    private double END_LONGITUDE;
    public MarkerOptions inicioMarker;
    public  MarkerOptions fin;
    TextView user;
    TextView metros;
    Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        START_LONGITUDE = getIntent().getDoubleExtra("START_LONGITUDE", 0.0);
        START_LATITUDE = getIntent().getDoubleExtra("START_LATITUDE", 0.0);
        END_LONGITUDE = getIntent().getDoubleExtra("END_LONGITUDE", 0.0);
        END_LATITUDE = getIntent().getDoubleExtra("END_LATITUDE", 0.0);
        System.out.println("NOMBREEEEEEEEEEEEEEEEEEEEEEEEEEEE FINALLLLLLLLLLLLMAPPPPPPPPPPPPPPPP");
        System.out.println(getIntent().getStringExtra("NOMBRE_USER"));;
        user = findViewById(R.id.txt_User);
        metros = findViewById(R.id.distance);
        btn = findViewById(R.id.btn_next_elec);

        user.setText("Querida usuario " + getIntent().getStringExtra("NOMBRE_USER"));
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, eleccion.class);
            startActivity(intent);
        });


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMapReady(GoogleMap map) {
        System.out.println("onMapReady");
        System.out.println("START_LONGITUDE: ");
        System.out.println(START_LONGITUDE);
        System.out.println("START_LATITUDE: ");
        System.out.println(START_LATITUDE);
        // ----------------
        System.out.println("END_LONGITUDE: ");
        System.out.println(END_LONGITUDE);
        System.out.println("END_LATITUDE: ");
        System.out.println(END_LATITUDE);


        LatLng start = new LatLng(START_LATITUDE, START_LONGITUDE);
        LatLng end = new LatLng(END_LATITUDE, END_LONGITUDE);


        System.out.println(start.longitude);
        System.out.println("START_LATITUDE LATLANGGGGGG: ");
        System.out.println(start.latitude);


        inicioMarker = new MarkerOptions().position(start).title("INCIO").draggable(true);
        fin = new  MarkerOptions().position(end).title("Fin").draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        map.addMarker(inicioMarker);
        map.addMarker(fin);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 90));

        float[] distanceResults = new float[1];
        Location.distanceBetween(start.latitude, start.longitude, end.latitude, end.longitude, distanceResults);
        int distanceInMeters = (int) distanceResults[0];
        //
        metros.setText("Distancia del viaje: " + distanceInMeters);



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }


}