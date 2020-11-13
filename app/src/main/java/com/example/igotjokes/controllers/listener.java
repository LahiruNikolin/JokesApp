package com.example.igotjokes.controllers;

import com.example.igotjokes.Joke;
import com.example.igotjokes.JokeDisplay;

public class listener {
    JokeDisplay jokeclass;
    public listener(JokeDisplay jd){
        jokeclass=jd;
    }

    public void onDataAvailable(Joke[] jks){

        jokeclass.fillAdapter(jks);

    }

}
