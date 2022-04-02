package com.example.firebasecreateuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BloodGroupActivity extends AppCompatActivity {

    private Button blood_A_pos,blood_A_neg;
    private Button blood_B_pos,blood_B_neg;
    private Button blood_AB_pos,blood_AB_neg;
    private Button blood_O_pos,blood_O_neg;
    private Button blood_group_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_group);

        blood_A_pos = findViewById(R.id.group_A_pos);
        blood_A_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
        blood_A_neg = findViewById(R.id.group_A_neg);
        blood_A_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
        blood_B_pos = findViewById(R.id.group_B_pos);
        blood_B_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
        blood_B_neg = findViewById(R.id.group_B_neg);
        blood_B_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        blood_O_pos = findViewById(R.id.group_O_pos);
        blood_O_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
        blood_O_neg = findViewById(R.id.group_O_neg);
        blood_O_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        blood_AB_pos = findViewById(R.id.group_AB_pos);
        blood_AB_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
        blood_AB_neg = findViewById(R.id.group_AB_neg);
        blood_AB_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        blood_group_home = findViewById(R.id.blood_group_from_home);
        blood_group_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(BloodGroupActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}