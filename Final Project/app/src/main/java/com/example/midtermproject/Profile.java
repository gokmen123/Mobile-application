package com.example.midtermproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Profile extends Fragment {

    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        String names = getArguments().getString("user");

        TextView username,name,surname,education,email,gender,phoneNo,birthday;
        username=view.findViewById(R.id.username_prof);
        name=view.findViewById(R.id.name_prof);
        surname=view.findViewById(R.id.surname_prof);
        email=view.findViewById(R.id.email_prof);
        education=view.findViewById(R.id.education_prof);
        gender=view.findViewById(R.id.gender_prof);
        phoneNo=view.findViewById(R.id.phone_prof);
        birthday=view.findViewById(R.id.birthday_prof);

        db.collection("Users").document(names).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    username.setText(task.getResult().getString("username"));
                    name.setText(task.getResult().getString("name"));
                    surname.setText(task.getResult().getString("surname"));
                    email.setText(task.getResult().getString("email"));
                    education.setText(task.getResult().getString("education"));
                    gender.setText(task.getResult().getString("sex"));
                    phoneNo.setText(task.getResult().getString("phoneNo"));
                    birthday.setText(task.getResult().getString("dateOfBirth"));
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}