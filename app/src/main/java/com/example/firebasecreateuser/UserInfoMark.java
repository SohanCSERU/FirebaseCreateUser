package com.example.firebasecreateuser;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class UserInfoMark extends Activity {

    TextView user_name, phone_number,  blood_group,  last_donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_marker);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.25));


        user_name = findViewById(R.id.marker_info_show_Name);
        phone_number = findViewById(R.id.marker_info_show_phone);
        blood_group = findViewById(R.id.marker_info_show_blood_group);
        last_donation = findViewById(R.id.marker_info_show_last_donation);

        String text_name = getIntent().getStringExtra("user_name");
        String text_phone = getIntent().getStringExtra("phone_number");
        String text_blood_group = getIntent().getStringExtra("blood_group");
        String text_last_donation = getIntent().getStringExtra("last_donation");

        user_name.setText("Name: "+text_name);
        phone_number.setText("Phone: "+text_phone);
        blood_group.setText("Blood Group: "+text_blood_group);
        last_donation.setText("Last Donation Date: "+ text_last_donation);
    }
}