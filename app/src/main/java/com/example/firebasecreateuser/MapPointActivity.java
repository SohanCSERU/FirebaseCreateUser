package com.example.firebasecreateuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MapPointActivity extends AppCompatActivity {

    TextView text_to_show;
    TextView text_to_show1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        text_to_show = (TextView) findViewById(R.id.hello_world);
        text_to_show1 = (TextView) findViewById(R.id.hello_world1);

        String sessionId = getIntent().getStringExtra("LATITUDE");
        String ses = getIntent().getStringExtra("LONGITUDE");

        text_to_show.setText(sessionId);
        text_to_show1.setText(ses);
    }
}