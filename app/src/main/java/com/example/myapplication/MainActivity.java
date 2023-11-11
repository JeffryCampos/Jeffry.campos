package com.example.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {


    private String validUsername = "admin";
    private String validPassword = "123";

    GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameEditText = findViewById(R.id.editTextTextEmailAddress2);
        EditText passwordEditText = findViewById(R.id.editTextTextPassword);
        Button loginButton = findViewById(R.id.button);
        TextView errorTextView = findViewById(R.id.errorTextView);



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isValidCredentials(username, password)) {
                    setContentView(R.layout.page1);

                    TextView usuarioPage1 = findViewById(R.id.usuarioPage1);
                    usuarioPage1.setText(username);
                } else {
                    errorTextView.setText("Credenciales incorrectas");
                }
            }
        });
    }


    private boolean isValidCredentials(String username, String password) {
        return username.equals(validUsername) && password.equals(validPassword);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng Chile = new LatLng(-36.621371,-72.1286212);
        mMap.addMarker(new MarkerOptions().position(Chile).title("Mi casa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Chile));


    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        EditText txtLatitud = findViewById(R.id.txtLatitud);
        EditText txtLongitud = findViewById(R.id.txtLongitud);

        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

        EditText txtLatitud = findViewById(R.id.txtLatitud);
        EditText txtLongitud = findViewById(R.id.txtLongitud);

        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);

    }
}
