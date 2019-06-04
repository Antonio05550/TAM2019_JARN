package com.example.googlemap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap mMap;
    TextView location;
    Location currentLocation;
    ImageView find_location;
    Marker CurrentMark=null;
    private static final int LOCATION_REQUEST_CODE = 101;
EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        find_location=findViewById(R.id.location);
        location = findViewById( R.id.latlongLocation );

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        find_location.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        fetchLastLocation();
    }
});
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
           mMap.setMyLocationEnabled(true);
        }
        LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Yo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        location.setText( "Lat: " + currentLocation.getLatitude()+" Log: "+currentLocation.getLongitude() );

        if (CurrentMark!=null)
            CurrentMark.remove();
        CurrentMark=googleMap.addMarker(markerOptions);

    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
    task.addOnSuccessListener(new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if (location != null) {
                currentLocation = location;

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(MapsActivity.this);
            }else{
                Toast.makeText(MapsActivity.this,"No encontrada",Toast.LENGTH_SHORT).show();
            }
        }
    });
}

    public void onSend(View view) {
        TextView messageView = (TextView) findViewById(R.id.desc);
        String strTextView = messageView.getText().toString();

        String strTextView2;
        TextView messageView2 = (TextView) findViewById(R.id.numero);
        strTextView2 = messageView2.getText().toString();

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(strTextView2, null, location.getText() + " Des:"  + strTextView, null, null);
        Toast.makeText(getApplicationContext(), "Su ubicación fue enviada.", Toast.LENGTH_LONG).show();

        finish();
    }

}
