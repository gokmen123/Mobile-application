package com.example.midtermproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditProfile extends Fragment {
    private EditingProfile editingProfile;
    Button button;
    View view;
    EditText username,name,surname,education,email,gender,phoneNo,date;
    String password;
    public interface EditingProfile {
        void updateView(String name,String surname,String dateOfBirth,String education,String email,String gender,String phoneNo);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_edit_profile, container, false);
        username=view.findViewById(R.id.username_prof_edit);
        name=view.findViewById(R.id.name_prof_edit);
        surname=view.findViewById(R.id.surname_prof_edit);
        email=view.findViewById(R.id.email_prof_edit);
        education=view.findViewById(R.id.education_prof_edit);
        gender=view.findViewById(R.id.gender_prof_edit);
        date=view.findViewById(R.id.birthday_prof_edit);
        phoneNo=view.findViewById(R.id.phone_prof_edit);
        getInfo();
        button = view.findViewById(R.id.save_changes_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editingProfile.updateView(name.getText().toString(),surname.getText().toString(),date.getText().toString(),education.getText().toString(),
                        email.getText().toString(),gender.getText().toString(),phoneNo.getText().toString());
            }
        });
        return view;
    }
    public void getInfo(){
        User getUser = (User) getArguments().getSerializable("profile_edit");
        password = getUser.getPassword();
        username.setText(getUser.getUsername());
        name.setText(getUser.getName());
        surname.setText(getUser.getSurname());
        email.setText(getUser.getEmail());
        education.setText(getUser.getEducation());
        gender.setText(getUser.getSex());
        phoneNo.setText(getUser.getPhoneNo());
        date.setText(getUser.getDateOfBirth());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditingProfile){
            editingProfile= (EditingProfile) context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement EditingProfile");
        }
    }
}