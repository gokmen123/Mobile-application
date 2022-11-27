package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;

public class BlogDesign extends AppCompatActivity {

    ImageButton img;

    ImageButton home,profile,logout,createBlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_design);
        home=findViewById(R.id.homeButton);
        profile=findViewById(R.id.profileButton);
        logout=findViewById(R.id.logoutButton);
        createBlog=findViewById(R.id.createBlogButton);
        replaceFragment(new HomeScreen());




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeScreen());
                changeBackground(home);
            }


        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Profile());
                changeBackground(profile);
            }


        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlogDesign.this,MainActivity.class);
                startActivity(intent);
            }


        });
        createBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CreateBlogFragment());
                changeBackground(createBlog);
            }


        });


    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.blogDesignFrame,fragment);
        fragmentTransaction.commit();

    }
    public void changeBackground(ImageButton button){
//        button.setBackgroundColor(getResources().getColor(R.color.teal_700));
    }
}