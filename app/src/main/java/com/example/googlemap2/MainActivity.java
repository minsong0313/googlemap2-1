package com.example.googlemap2;

import androidx.appcompat.app.AppCompatActivity;


import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragementManager;
    private MapFragment mapFragment;
    private FusedLocationProviderClient mFusedLocationClinet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragementManager = getFragmentManager();
        mapFragment = (MapFragment)fragementManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        mFusedLocationClinet = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(36.833773, 127.179262); //상명대학교에 마커 생성
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("상명대학교") ;
        markerOptions.position(location);//위도 경도 불러오기
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16));
    }//마커 찍기

}
