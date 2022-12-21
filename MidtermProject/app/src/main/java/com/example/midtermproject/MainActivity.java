package com.example.midtermproject;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    TextView title;
    EditText usernames,passwords;
    CheckBox remember ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WELCOME");

        TextView sign= findViewById(R.id.createAccount);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        usernames=findViewById(R.id.username_main);
        passwords=findViewById(R.id.password_main);
        remember = findViewById(R.id.rememberMes);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(MainActivity.this,CreateAccount.class);
                startActivity(intent1);


//
            }
        });

        Button btn = findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("Users").document(usernames.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            if (TextUtils.equals(task.getResult().getString("username"),usernames.getText().toString()) && TextUtils.equals(task.getResult().getString("password"),passwords.getText().toString())) {
                                SharedPreferences pref = getSharedPreferences("apppref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                if (remember.isChecked()) {
                                    editor.putString("username", usernames.getText().toString());
                                    editor.putString("password", passwords.getText().toString());

                                } else {
                                    editor.remove("username");
                                    editor.remove("password");

                                }
                                editor.putBoolean("rememberMe", remember.isChecked());
                                editor.commit();
                                Intent intent = new Intent(MainActivity.this, BlogDesign.class);
                                intent.putExtra("username", usernames.getText().toString());
                                Toast.makeText(getApplicationContext(), "Successfully Logged In Welcome", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Username or password wrong!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }

        });
        SharedPreferences pref = getSharedPreferences("apppref", Context.MODE_PRIVATE);
        String usernamess = pref.getString("username", "");
        passwords.setText(pref.getString("password", ""));
        usernames.setText(usernamess);
        remember.setChecked(pref.getBoolean("rememberMe", false));
    }



}