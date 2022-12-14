package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;

public class BlogDesign extends AppCompatActivity  {

    ImageButton img;
    User activeUser;
    ImageButton home,profile,logout,createBlog,editprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_design);
        home=findViewById(R.id.homeButton);
        profile=findViewById(R.id.profileButton);
        logout=findViewById(R.id.logoutButton);
        editprofile= findViewById(R.id.edidProfile);
        createBlog=findViewById(R.id.createBlogButton);
        String name=getIntent().getStringExtra("username");
        replaceFragment(new HomeScreen());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreen homeScreen = new HomeScreen();
                replaceFragment(homeScreen);
                Bundle bundle = new Bundle();
                bundle.putString("sended",name);
                homeScreen.setArguments(bundle);


            }


        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = new Profile();
                replaceFragment(profile);
                Bundle bundle = new Bundle();
                bundle.putString("user",name);
                profile.setArguments(bundle);

            }


        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlogDesign.this,MainActivity.class);

                finish();
            }


        });
        createBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBlogFragment createBlogFragment= new CreateBlogFragment();
                Bundle bundle=new Bundle();
                bundle.putString("activeUser",name);
                createBlogFragment.setArguments(bundle);
                replaceFragment(createBlogFragment);
                changeBackground(createBlog);
            }


        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile editprofiles = new EditProfile();
                replaceFragment(editprofiles);
                Bundle bundle = new Bundle();
                bundle.putString("prof",name);
                editprofiles.setArguments(bundle);

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