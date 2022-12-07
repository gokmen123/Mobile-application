package com.example.midtermproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;



public class Profile extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        User getUser = (User) getArguments().getSerializable("profile");
        TextView username,name,surname,education,email,gender,phoneNo,birthday;
        username=view.findViewById(R.id.username_prof);
        username.setText(getUser.getUsername());
        name=view.findViewById(R.id.name_prof);
        name.setText(getUser.getName());
        surname=view.findViewById(R.id.surname_prof);
        surname.setText(getUser.getSurname());
        email=view.findViewById(R.id.email_prof);
        email.setText(getUser.getEmail());
        education=view.findViewById(R.id.education_prof);
        education.setText(getUser.getEducation());
        gender=view.findViewById(R.id.gender_prof);
        gender.setText(getUser.getSex());
        phoneNo=view.findViewById(R.id.phone_prof);
        phoneNo.setText(getUser.getPhoneNo());
        birthday=view.findViewById(R.id.birthday_prof);
        birthday.setText(getUser.getDateOfBirth());


        // Inflate the layout for this fragment
        return view;
    }
}