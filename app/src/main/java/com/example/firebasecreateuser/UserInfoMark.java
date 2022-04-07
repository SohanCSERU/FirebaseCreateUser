package com.example.firebasecreateuser;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class UserInfoMark extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    TextView user_name, phone_number,  blood_group,  last_donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_marker);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.26));


        user_name = findViewById(R.id.marker_info_show_Name);
        phone_number = findViewById(R.id.marker_info_show_phone);
        blood_group = findViewById(R.id.marker_info_show_blood_group);
        last_donation = findViewById(R.id.marker_info_show_last_donation);

        String text_name = getIntent().getStringExtra("user_name");
        String text_phone = getIntent().getStringExtra("phone_number");
        String text_blood_group = getIntent().getStringExtra("blood_group");
        String text_last_donation = getIntent().getStringExtra("last_donation");

        user_name.setText("Name: "+text_name);

        phone_number.setText(text_phone);


        phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ text_phone));
                startActivity(callIntent);
            }
        });


        blood_group.setText("Blood Group: "+text_blood_group);
        last_donation.setText("Last Donation Date: "+ text_last_donation);
    }


}
//
//<meta-data
//        android:name="com.google.android.geo.API_KEY"
//        android:value="AIzaSyCtABDKqNWAc5IcUW7pP3YaL-vKrXX1YkA" />