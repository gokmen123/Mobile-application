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

public class BlogDesign extends AppCompatActivity implements EditProfile.EditingProfile {

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
        createBlog=findViewById(R.id.createBlogButton);
        activeUser= (User) getIntent().getSerializableExtra("activeUser");
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
                Profile profile = new Profile();
                replaceFragment(profile);
                Bundle bundle = new Bundle();
                bundle.putSerializable("profile",activeUser);
                profile.setArguments(bundle);

            }


        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlogDesign.this,MainActivity.class);
                intent.putExtra("active",activeUser);
                setResult(RESULT_OK,intent);
                finish();
            }


        });
        createBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CreateBlogFragment());
                changeBackground(createBlog);
            }


        });
        editprofile= findViewById(R.id.edidProfile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile editProfiles = new EditProfile();
                Bundle bundle = new Bundle();
                bundle.putSerializable("profile_edit",activeUser);
                editProfiles.setArguments(bundle);
                replaceFragment(editProfiles);
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

    @Override
    public void updateView(String name, String surname, String dateOfBirth, String education, String email, String gender, String phoneNo) {
        activeUser.setName(name);
        activeUser.setSurname(surname);
        activeUser.setDateOfBirth(dateOfBirth);
        activeUser.setEmail(email);
        activeUser.setEducation(education);
        activeUser.setSex(gender);
        activeUser.setPhoneNo(phoneNo);
        Log.d("updateView", activeUser.getEducation());
    }
}