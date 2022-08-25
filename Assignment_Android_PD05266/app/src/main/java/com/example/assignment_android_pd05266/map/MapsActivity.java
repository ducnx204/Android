//package com.example.assignment_android_pd05266.map;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.FragmentActivity;
//
//import com.example.assignment_android_pd05266.R;
//import com.example.assignment_android_pd05266.databinding.ActivityMapsBinding;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//    private ActivityMapsBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng fpt = new LatLng(16.075733, 108.169949);
//        mMap.addMarker(new MarkerOptions().position(fpt).title("FPT Polytechnic Đà Nẵng"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fpt, 20));
//    }
//}
package com.example.assignment_android_pd05266.map;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.assignment_android_pd05266.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.assignment_android_pd05266.R;

import org.jetbrains.annotations.NotNull;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    EditText edAddress;
    ImageView searchMap;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private MarkerOptions markerOptions = new MarkerOptions();


    static final LatLng fptHN = new LatLng(21.038128,105.746787);
    static final LatLng fptDN = new LatLng(16.075733, 108.169949);
    static final LatLng fptTN = new LatLng(12.709865, 108.075077);
    static final LatLng fptHCM = new LatLng(10.811857, 106.679912);
    static final LatLng fptCT = new LatLng(10.026958, 105.757155);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        edAddress = findViewById(R.id.edAddress);

        searchMap = findViewById(R.id.SearchMap);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(markerOptions.position(fptHN).title("FPoly Hà Nội"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fptHN));

        mMap.addMarker(markerOptions.position(fptDN).title("FPoly Đà Nẵng"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fptDN));

        mMap.addMarker(markerOptions.position(fptTN).title("FPoly Tây Nguyên"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fptTN));

        mMap.addMarker(markerOptions.position(fptHCM).title("FPoly Hồ Chí Minh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fptHCM));

        mMap.addMarker(markerOptions.position(fptCT).title("FPoly Cần Thơ"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fptCT));
    }

    public void search(View view) {
        String address = edAddress.getText().toString();

        if (address.equalsIgnoreCase("FPoly Hà Nội")||
                address.equalsIgnoreCase("FPoly HN") ||
                address.equalsIgnoreCase("HN")){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fptHN,16));
        }else if (address.equalsIgnoreCase("FPoly Đà Nẵng") ||
                address.equalsIgnoreCase("Fpoly DN") ||
                address.equalsIgnoreCase("DN")){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fptDN,16));
        }else if (address.equalsIgnoreCase("FPoly Tây Nguyên") ||
                address.equalsIgnoreCase("Fpoly TN") ||
                address.equalsIgnoreCase("TN")){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fptTN,16));
        }else if(address.equalsIgnoreCase("FPoly Hồ Chí Minh") ||
                address.equalsIgnoreCase("Fpoly HCM") ||
                address.equalsIgnoreCase("HCM")){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fptHCM,16));
        }else if (address.equalsIgnoreCase("FPoly Cần Thơ") ||
                address.equalsIgnoreCase("FPoly CT") ||
                address.equalsIgnoreCase("CT")) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fptCT,16));
        }


    }
}