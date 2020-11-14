package com.example.igotjokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button register;


    private EditText email,password,conPass;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar=findViewById(R.id.progressBar4);
        progressBar.setVisibility(ProgressBar.GONE);

        email=findViewById(R.id.email_sign_et);
        password=findViewById(R.id.password_sign_et);
        conPass=findViewById(R.id.con_password_sign_et);
        register=findViewById(R.id.register_btn);


        register.setOnClickListener(this);
    }

    public void register(){

        if(validate(email.getText().toString(),password.getText().toString(),conPass.getText().toString())){
            progressBar.setVisibility(ProgressBar.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), conPass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");


                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);

                                startActivity(intent);

                                progressBar.setVisibility(ProgressBar.GONE);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "There was an error,Please try again",
                                        Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(ProgressBar.GONE);
                            }

                        }
                    });


        }
        else{
            Toast.makeText(getApplicationContext(), "Please Enter the Details Correctly",
                    Toast.LENGTH_LONG).show();
        }



    }
    public boolean validate(String mail,String pass,String conpass){
        Log.d("FF","im game");
        Boolean flag=true;
        if(mail.isEmpty() || pass.isEmpty() || conpass.isEmpty())
            flag=false;
        else if(!pass.equals(conpass))
            flag=false;

        return flag;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.register_btn: /** Start a new Activity MyCards.java */
                register();

                break;

        }
    }
}