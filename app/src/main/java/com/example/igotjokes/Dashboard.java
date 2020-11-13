package com.example.igotjokes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.igotjokes.controllers.OneJokeListener;
import com.example.igotjokes.controllers.listener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private TextView welcomeTv;
    private Button Morejokes;
    private ProgressBar progressBar;
    private RadioButton pType;
    private RadioButton gType;
    private Button btn,logout;
    String flag="general";


    Joke joke;
    OneJokeListener ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tv=findViewById(R.id.daily_joke);
        progressBar=findViewById(R.id.progressBar2);
        Morejokes=findViewById(R.id.ten_jokesBtn);
        pType=(RadioButton)findViewById(R.id.rb_pro);
        gType=(RadioButton)findViewById(R.id.rb_gen);
        btn=findViewById(R.id.joke_type_btn);
        logout=findViewById(R.id.logout);

        welcomeTv=findViewById(R.id.welcome_tv);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        welcomeTv.setText( user.getEmail());

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),"Im game",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), JokesType.class);
                intent.putExtra("TAG", flag);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(this);



        Morejokes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),"Im game",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), JokeDisplay.class);
                startActivity(intent);
            }
        });


        ls=new OneJokeListener(this);
        getJoke();


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.type_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(pType.getId()==checkedId){
                    flag="programming";
                    Log.d("RD","pp");
                }
                else if (gType.getId()==checkedId){
                    flag="general";
                    Log.d("RD","rrr");
                }

            }
        });

    }

    public void getJoke(){
        Log.d("JD","im game0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://official-joke-api.appspot.com/jokes/random";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject res) {
                       // textView.setText("Response: " + response.toString());
                        try {
                            joke=new Joke(res.getString("id"),res.getString("type"),
                                    res.getString("setup"),res.getString("punchline"));
                            ls.onDataAvailable(joke);
                        }
                        catch (JSONException error){

                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JD",error.toString());

                    }
                });


        queue.add(jsonObjectRequest);


    }

    public void fillAdapter(Joke jk) {
        Log.d("JD",jk.getPunchline());

        tv.setText(jk.setup);
        progressBar.setVisibility(ProgressBar.GONE);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.logout: 
                logout();

                break;

        }

    }

    private void logout() {

        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(getApplicationContext(), Login.class);

        startActivity(intent);


    }
}