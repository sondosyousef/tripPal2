package com.example.trip.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.example.trip.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MylocationActivity extends AppCompatActivity {

    //Initialize variable

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.mylocation );


        //Assign variable

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById( R.id.google_map );

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient( this );

        //Chech permission

        if (ActivityCompat.checkSelfPermission( MylocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            //when permission grated
            //call method
            getCurrentLocation();
        } else {
            //when permission deniel
            //request permission
            ActivityCompat.requestPermissions( MylocationActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44 );
        }

    }

    private void getCurrentLocation() {
        //Initialize task location
        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener( new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                //when success
                if (location != null){
                    //sync map
                    supportMapFragment.getMapAsync( new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //Initialize lat lng
                            LatLng latLng = new LatLng( location.getLatitude(),
                                    location.getLongitude());

                            //Create marker options
                            MarkerOptions options = new MarkerOptions().position( latLng ).title( "I am here" );
                            //Zoom map
                            googleMap.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng,30 ) );
                            //Add marker on map
                            googleMap.addMarker( options );

                        }
                    } );
                }
            }
        } );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }
}