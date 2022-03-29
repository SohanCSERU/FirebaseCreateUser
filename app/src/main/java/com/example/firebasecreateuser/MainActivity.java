package com.example.firebasecreateuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private Button search_blood_button;

    private TextView login_at_home,register_at_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_blood_button =(Button) findViewById(R.id.search_blood);
        login_at_home =(TextView) findViewById(R.id.login_at_home);
        register_at_home =(TextView) findViewById(R.id.register_at_home);

        login_at_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        register_at_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });


        search_blood_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(MainActivity.this, MapPointActivity.class);
                startActivity(i);
            }
        });
    }
}