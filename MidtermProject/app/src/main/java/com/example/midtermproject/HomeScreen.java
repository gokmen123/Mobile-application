package com.example.midtermproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;



public class HomeScreen extends Fragment {
    ImageButton img;
    boolean onClicked=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home_screen, container, false);
        img=view.findViewById(R.id.like);
        img.setBackgroundResource(R.drawable.like);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageClick();
            }
        });
        return view;
    }
    public void ImageClick() {


        onClicked =!onClicked ;
        if(onClicked){
            img.setBackgroundResource(R.drawable.dislike);
        }
        else{
            img.setBackgroundResource(R.drawable.like);
        }

    }
}