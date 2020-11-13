package com.example.igotjokes.controllers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igotjokes.Joke;
import com.example.igotjokes.R;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.MyViewHolder> {
    private Joke[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout cl;
        public MyViewHolder(ConstraintLayout v) {
            super(v);
            cl = v;
        }
    }


    public JokeAdapter(Joke[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public JokeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.joke_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //holder.textView.setText(mDataset[position]);
        TextView tv=(TextView) holder.cl.getViewById(R.id.joke_id);
        TextView tv2=(TextView) holder.cl.getViewById(R.id.punchline);
        tv.setText(mDataset[position].setup);
        tv2.setText(mDataset[position].punchline);


    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}