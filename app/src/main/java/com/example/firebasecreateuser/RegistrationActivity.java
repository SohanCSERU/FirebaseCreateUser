package com.example.firebasecreateuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextName,editTextEmail,editTextPassword,editTextCnfPassword,editPhoneNumber,editLatDonDate,editBloodGroup;
    private TextView loginbtn_below,setLatitudeBtn,setLongitudeBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button register_btn,map_add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginbtn_below = (TextView) findViewById(R.id.idTVLoginUser);
        mAuth = FirebaseAuth.getInstance();

        editTextName = (EditText)findViewById(R.id.idEdtUserName);
        editTextPassword = (EditText)findViewById(R.id.idEdtPassword);
        editTextCnfPassword = (EditText)findViewById(R.id.idEdtConfirmPassword);
        editTextEmail = (EditText)findViewById(R.id.idEdtUserEmail);
        editPhoneNumber = (EditText)findViewById(R.id.idEdtUserPhone);
        editLatDonDate = (EditText)findViewById(R.id.idEdtUserLastDon);
        editBloodGroup = (EditText)findViewById(R.id.idEdtUserBloodGroup);

        register_btn = (Button)findViewById(R.id.idBtnRegister);
//        map_add_button = (Button)findViewById(R.id.idBtnAddLocMap);

        progressBar = (ProgressBar)findViewById(R.id.idPBLoading);

//        new Operation added
        setLatitudeBtn = (TextView)findViewById(R.id.idLatitude);
        setLongitudeBtn = (TextView)findViewById(R.id.idLongitude);

        Intent intent = getIntent();
        String text_lat = intent.getStringExtra(MapsActivity.LATITUDE);
        String text_lon = intent.getStringExtra(MapsActivity.LONGITUDE);


        setLatitudeBtn.setText(text_lat);
        setLongitudeBtn.setText(text_lon);

//        map_add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(RegistrationActivity.this,MapsActivity.class);
//                startActivity(i);
//            }
//        });

        loginbtn_below.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                // on below line opening a login activity.
                Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editPhoneNumber.getText().toString().trim();
                String blood_group = editBloodGroup.getText().toString().trim();
                String last_donation_date= editLatDonDate.getText().toString().trim();

                String password = editTextPassword.getText().toString().trim();
                String cnfPassword = editTextCnfPassword.getText().toString().trim();
                if (name.isEmpty()){
                    editTextName.setText("Name is empty");
                    editTextName.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    editTextEmail.setText("Email is empty");
                    editTextEmail.requestFocus();
                    return;
                }
                if (!password.equals(cnfPassword)){
                    editTextPassword.setText("");
                    editTextCnfPassword.requestFocus();
                    Toast.makeText(RegistrationActivity.this, "Please check both having same password..", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(view.GONE);

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,
                                    email,
                                    phone,
                                    blood_group,
                                    last_donation_date,
                                    password);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        progressBar.setVisibility(view.VISIBLE);
                                        Toast.makeText(RegistrationActivity.this,"User Successfully Regestrated",Toast.LENGTH_LONG).show();

                                        //Login Redirect
                                        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                                    }
                                    else{
                                        Toast.makeText(RegistrationActivity.this,"Failed To  Regestrated",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(view.GONE);
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(RegistrationActivity.this,"Failed To  Register",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(view.GONE);
                        }
                    }
                });

//            OnClick FInished
            }
        });

    }




}