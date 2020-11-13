package com.example.igotjokes.controllers;

import com.example.igotjokes.Joke;
import com.example.igotjokes.JokesType;

public class JokeTypeListener {


    JokesType jokeclass;

    public JokeTypeListener(JokesType jd){
        jokeclass=jd;
    }



    public void onDataAvailable(Joke[] jks){

        jokeclass.fillAdapter(jks);

    }

}
