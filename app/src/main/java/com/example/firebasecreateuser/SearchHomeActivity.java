package com.example.firebasecreateuser;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebasecreateuser.databinding.ActivitySearchBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.firebasecreateuser.databinding.ActivitySearchHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchHomeActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Map<Marker, MarkerInfo> mMarkerMap = new HashMap<>();

    private ActivitySearchBinding binding;
    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    ArrayList<String> list = new ArrayList<>();
    private String name;

    TextView search_blood_result;
    Button search_to_home_btn;
    Button search_to_blood_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        search_blood_result = findViewById(R.id.search_blood_result);
        search_to_home_btn = findViewById(R.id.back_to_home);
        search_to_blood_show = findViewById(R.id.search_blood_at_search_activity);

        search_to_blood_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchHomeActivity.this,BloodGroupActivity.class);
                startActivity(i);
            }
        });
        search_to_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchHomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
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


        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot snap: snapshot.getChildren()){
                    User user =  snap.getValue(User.class);
                    String text = user.latitude;
                    String text1 = user.longitude;

                    String user_name = user.user_name;
                    String phone_number = user.phone_number;
                    String blood_group = user.blood_group;
                    String last_donation = user.last_donation;

                    list.add(user.blood_group);

                    Double lat = Double.parseDouble(user.latitude);
                    Double lon = Double.parseDouble(user.longitude);


                        Marker marker;
                        marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat,lon)));
                        marker.setTitle(blood_group);
//                        marker.showInfoWindow();
                        MarkerInfo markerInfo = new MarkerInfo(user_name,  phone_number,  blood_group,  last_donation);
                        mMarkerMap.put(marker, markerInfo);
                        search_blood_result.setText("Showing Result for ALL Group");


                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.370706,88.636881), 12.0f));

                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            MarkerInfo markerInfo = mMarkerMap.get(marker);
                            User user =  snap.getValue(User.class);
                            Intent intent = new Intent(SearchHomeActivity.this, UserInfoMark.class);

                            intent.putExtra("user_name", markerInfo.user_name);
                            intent.putExtra("phone_number", markerInfo.phone_number);
                            intent.putExtra("last_donation", markerInfo.last_donation);
                            intent.putExtra("blood_group", markerInfo.blood_group);
                            startActivity(intent);
                            return true;
                        }
                    });
//                    //Set this only once:
//                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//
//                        @Override
//                        public void onInfoWindowClick(Marker marker) {
//                            MarkerInfo markerInfo = mMarkerMap.get(marker);
//                            User user =  snap.getValue(User.class);
//                            Intent intent = new Intent(SearchActivity.this, UserInfoMark.class);
//
//                            intent.putExtra("user_name", markerInfo.user_name);
//                            intent.putExtra("phone_number", markerInfo.phone_number);
//                            intent.putExtra("last_donation", markerInfo.last_donation);
//                            intent.putExtra("blood_group", markerInfo.blood_group);
//                            startActivity(intent);
//                        }
//                    });


//                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                        @Override
//                        public boolean onMarkerClick(@NonNull Marker marker) {
//                            Toast.makeText(getApplicationContext(),
//                                    "New marker Added: "+ user.user_name, Toast.LENGTH_LONG)
//                                    .show();
//                            return false;
//                        }
//                    });
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}