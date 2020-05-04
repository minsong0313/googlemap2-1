package com.example.googlemap2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap = null;
    private GoogleApiClient mGoogleApiClient = null;

    private FusedLocationProviderClient  mFusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap =  googleMap;

        LatLng univer = new LatLng(36.833773, 127.179262);
        mMap.addMarker(new MarkerOptions().position(univer).title("상명대학교"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(univer));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));//1~21

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0412222222"));
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }//상명대학교 위도, 경도로 마커 찍기

    public void onLastLocationButtonClicked(View view) {//현재 위치 정보 얻어오기

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>(){
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                        .position(mylocation)
                        .title("현재 위치"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

                }
            }
        });
    }
}
