package com.example.igotjokes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.igotjokes.controllers.JokeAdapter;
import com.example.igotjokes.controllers.listener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JokeDisplay extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JokeAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;


    listener ls;
    Joke[] jokes= new Joke[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        progressBar=findViewById(R.id.progressBar);

        ls=new listener(this);


        getJokes();
      //  Log.d("jokeD","ggg");


    }

    public void getJokes(){
        Log.d("JD","im game0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://official-joke-api.appspot.com/jokes/ten";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{

                            for(int i=0;i<response.length();i++){

                                JSONObject res = response.getJSONObject(i);

                                jokes[i]=new Joke(res.getString("id"),res.getString("type"),
                                        res.getString("setup"),res.getString("punchline"));
                               // Log.d("JD",joke.toString());

                            }

                            ls.onDataAvailable(jokes);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){


                    }
                }
        );




        queue.add(jsonArrayRequest);


    }

    public void fillAdapter(Joke[] jks) {

       // Log.d("JD", jks[1].());

        mAdapter=new JokeAdapter(jks);

        recyclerView = (RecyclerView) findViewById(R.id.joke_rv);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);

        progressBar.setVisibility(ProgressBar.GONE);


    }
}