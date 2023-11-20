package com.example.myaparcialito;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class destino extends AppCompatActivity
        implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback{
    public MarkerOptions inicioMarker;
    public MarkerOptions finMarker;
    public Button btn_conducor;
    private FusedLocationProviderClient fusedLocationProviderClient;
    double latitud;
    double longitud;
    private Map<Marker, String> markerIdMap;
    private GoogleMap googleMap;
    private Bundle extras = new Bundle();

    String UserName;



    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("NOMBREEEEEEEEEEEEEEEEEEEEEEEEEEEE DESTINO");
        System.out.println(getIntent().getStringExtra("NOMBRE_USER"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destino);
        btn_conducor = findViewById(R.id.btn_conductores);
        btn_conducor.setOnClickListener(view -> {
            next();
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapReq);
        mapFragment.getMapAsync(this);
        markerIdMap = new HashMap<>();
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;

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
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        // SABER LA POSICION ACTUAL
                        latitud = location.getLatitude();
                        longitud = location.getLongitude();
                        // CREAR VARIABLES DE POSICION
                        LatLng actual = new LatLng(latitud, longitud);
                        LatLng finall = new LatLng(latitud +0.0000688, longitud + 0.000205);
                        // COLOCAR MARKER
                        addMarker(actual, "Inicio");
                        addMarker(finall,"Fin");

                    }
                });
        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                String markerId = markerIdMap.get(marker);
                if ("Inicio".equals(markerId)) {
                    LatLng newPosition = marker.getPosition();
                    double newLatitude = newPosition.latitude;
                    double newLongitude = newPosition.longitude;
                    extras.putDouble("START_LONGITUDE", newLongitude);
                    extras.putDouble("START_LATITUDE", newLatitude);
                } else if ("Fin".equals(markerId)) {
                    LatLng newPosition = marker.getPosition();
                    double newLatitude = newPosition.latitude;
                    double newLongitude = newPosition.longitude;
                    extras.putDouble("END_LONGITUDE", newLongitude);
                    extras.putDouble("END_LATITUDE", newLatitude);

                }
            }
        });
        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
    }
    public void next(){


        Intent intent = new Intent(this, conductoras.class);
        extras.putString("NOMBRE_USER", getIntent().getStringExtra("NOMBRE_USER"));
        intent.putExtras(extras);
        startActivity(intent);

    }
    private void addMarker(LatLng position, String markerId) {
        Marker marker = googleMap.addMarker(new MarkerOptions().position(position).title(markerId).draggable(true));
        markerIdMap.put(marker, markerId);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, " Posicion Actual:\n" + location, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onMyLocationButtonClick() {

        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


}
