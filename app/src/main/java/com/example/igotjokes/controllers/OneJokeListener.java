package com.example.igotjokes.controllers;

import com.example.igotjokes.Dashboard;
import com.example.igotjokes.Joke;
import com.example.igotjokes.JokeDisplay;

public class OneJokeListener {
    Dashboard dashboard;
    public OneJokeListener(Dashboard jd){
        dashboard=jd;
    }

    public void onDataAvailable(Joke jk){

        dashboard.fillAdapter(jk);

    }

}