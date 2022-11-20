package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class BlogDesign extends AppCompatActivity {

    boolean onClicked=false;
    ImageButton img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_design);
        img= findViewById(R.id.like);
        img.setBackgroundResource(R.drawable.like);


    }

    public void ImageClick(View view) {


        onClicked =!onClicked ;
        if(onClicked){
            img.setBackgroundResource(R.drawable.dislike);
        }
        else{
            img.setBackgroundResource(R.drawable.like);
        }

    }
}