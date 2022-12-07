package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateAccount extends AppCompatActivity {


    EditText password,passwordAgain,username,email;
    ArrayList<User> user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);
        setTitle("CREATE ACCOUNT");
        Button btn = findViewById(R.id.createAccountPage);
        password=findViewById(R.id.password);
        passwordAgain=findViewById(R.id.password_again);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);

        user = (ArrayList<User>) getIntent().getSerializableExtra("array");





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkConditions()){
                    Intent intent = new Intent();
                    String usernames=username.getText().toString();
                    String emails=email.getText().toString();
                    String passwords=password.getText().toString();
                    User newUser = new User(usernames,passwords,"","","",
                            "","",emails,"",new ArrayList<Blog>(),false);
                    user.add(newUser);
                    intent.putExtra("arrayChanged",user);
                    setResult(RESULT_OK,intent);
                    Toast.makeText(getApplicationContext(),"Account successfully created",Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }

    public boolean checkConditions() {
        user= (ArrayList<User>) getIntent().getSerializableExtra("array");
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        passwordAgain=findViewById(R.id.password_again);
        if(!password.getText().toString().equals(passwordAgain.getText().toString())){
            Toast.makeText(getApplicationContext(),"Passwords are not Equal",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(username.getText())){
            Toast.makeText(getApplicationContext(),"Username cannot be empty!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(email.getText())){
            Toast.makeText(getApplicationContext(),"Email cannot be empty!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password.getText())){
            Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(user.size()!=0){
            for(int i=0;i<user.size();i++){
                if(TextUtils.equals(user.get(i).getUsername(),username.getText())){
                    Toast.makeText(getApplicationContext(),"Username already taken!",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }





}