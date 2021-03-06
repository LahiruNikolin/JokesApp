package com.example.igotjokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.igotjokes.controllers.AlarmController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button sign;
    private ProgressBar progressBar;
    private EditText email,password;


    private FirebaseAuth mAuth;




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login= findViewById(R.id.loginBtn);
        sign= findViewById(R.id.signBtn);
        email=findViewById(R.id.email_et);
        password=findViewById(R.id.password_et);




        progressBar=findViewById(R.id.progressBar3);
        progressBar.setVisibility(ProgressBar.GONE);


        sign.setOnClickListener(this);
        login.setOnClickListener(this);


        AlarmController al=new AlarmController(this);
        al.StartAlarm();





    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.signBtn: /** Start a new Activity MyCards.java */
            signUp();

                break;
            case R.id.loginBtn: /** Start a new Activity MyCards.java */
                login();

                break;
        }

    }

    private void signUp() {


        Intent intent = new Intent(getApplicationContext(), SignUp.class);

        startActivity(intent);


    }

    public void login(){

        String name=email.getText().toString();
        String pass= password.getText().toString();


        if(name.isEmpty() || pass.isEmpty())
            return;

        progressBar.setVisibility(ProgressBar.VISIBLE);
        mAuth.signInWithEmailAndPassword(name,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            intent.putExtra("email", user.getEmail());
                            startActivity(intent);
                            progressBar.setVisibility(ProgressBar.GONE);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Email or Password is incorrect",
                                    Toast.LENGTH_LONG).show();

                            progressBar.setVisibility(ProgressBar.GONE);

                        }

                    }
                });
    }
}