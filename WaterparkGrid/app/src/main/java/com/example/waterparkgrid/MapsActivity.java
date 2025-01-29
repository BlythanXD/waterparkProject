package com.example.waterparkgrid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1; // Define a constant for the request code
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Check for location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permissions are already granted, initialize the map
            initializeMap();
        }
    }

    private void initializeMap() {
        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);  // Set the callback for map initialization
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Define the locations
        LatLng location1 = new LatLng(3.069495584040626, 101.60673262305654); // Sunway Lagoon WaterPark
        LatLng location2 = new LatLng(3.0648566815956064, 101.48526565962462); // WaterWorld by i-City
        LatLng location3 = new LatLng(2.904618879337674, 101.79090633800544); // Bangi Wonderland
        LatLng location4 = new LatLng(3.2747309051092883, 101.55249076986968); // Gamuda Luge Waterpark
        LatLng location5 = new LatLng(2.8887837938809726, 101.61634685793194); // SplashMania WaterPark
        LatLng location6 = new LatLng(3.0724040682529625, 101.51246711560255); // Wetworld Waterpark

        // Add markers for each location
        mMap.addMarker(new MarkerOptions().position(location5).title("Sunway Lagoon WaterPark"));
        mMap.addMarker(new MarkerOptions().position(location1).title("WaterWorld by i-City"));
        mMap.addMarker(new MarkerOptions().position(location2).title("Bangi Wonderland"));
        mMap.addMarker(new MarkerOptions().position(location3).title("Gamuda Luge Waterpark"));
        mMap.addMarker(new MarkerOptions().position(location4).title("SplashMania WaterPark"));
        mMap.addMarker(new MarkerOptions().position(location6).title("Wetworld Waterpark"));

        // Move the camera to the first location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 10)); // Adjust zoom level as needed
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, initialize the map
                initializeMap();
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
            }
        }
    }
}