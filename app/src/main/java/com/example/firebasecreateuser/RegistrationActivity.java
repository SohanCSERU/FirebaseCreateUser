package com.example.firebasecreateuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    private EditText editTextName,editTextPassword,editTextCnfPassword;
    private TextView loginbtn_below;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginbtn_below = (TextView) findViewById(R.id.idTVLoginUser);
        mAuth = FirebaseAuth.getInstance();
        editTextName = (EditText)findViewById(R.id.idEdtUserName);
        editTextPassword = (EditText)findViewById(R.id.idEdtPassword);
        editTextCnfPassword = (EditText)findViewById(R.id.idEdtConfirmPassword);
        register_btn = (Button)findViewById(R.id.idBtnRegister);


        progressBar = (ProgressBar)findViewById(R.id.idPBLoading);


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
                String password = editTextPassword.getText().toString().trim();
                String cnfPassword = editTextCnfPassword.getText().toString().trim();
                if (name.isEmpty()){
                    editTextName.setText("Name is empty");
                    editTextName.requestFocus();
                    return;
                }
                if (!password.equals(cnfPassword)){
                    editTextPassword.setText("");
                    editTextCnfPassword.requestFocus();
                    Toast.makeText(RegistrationActivity.this, "Please check both having same password..", Toast.LENGTH_SHORT).show();
                    return;
                }



                progressBar.setVisibility(view.GONE);
                mAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,password);

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