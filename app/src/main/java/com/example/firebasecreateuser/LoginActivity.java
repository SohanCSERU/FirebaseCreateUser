package com.example.firebasecreateuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText userNameEdt, passwordEdt;
    private Button loginBtn;
    private TextView newUserTV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    private TextView registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        registerbtn = (TextView) findViewById(R.id.idTVNewUser);
        loginBtn = (Button)findViewById(R.id.idBtnLogin);

        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);

        loadingPB = (ProgressBar) findViewById(R.id.idPBLoading);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userNameEdt.getText().toString().trim();
                String password = passwordEdt.getText().toString().trim();

                if(name.isEmpty()){
                    userNameEdt.setError("Email is required!");
                    userNameEdt.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()){
                    userNameEdt.setError("Please Enter a valid email !");
                    userNameEdt.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    passwordEdt.setError("Password is Required!");
                    passwordEdt.requestFocus();
                    return;
                }

                loadingPB.setVisibility(view.VISIBLE);

                mAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //redirecte to user profile
                            startActivity(new Intent(LoginActivity.this,MapsActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Failed to login!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line opening a login activity.
                Intent i = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
}