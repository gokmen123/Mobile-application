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

public class CreateAccount extends AppCompatActivity implements AddUser {


    EditText password,passwordAgain,username,email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);
        Button btn = findViewById(R.id.createAccountPage);
        password=findViewById(R.id.password);
        passwordAgain=findViewById(R.id.password_again);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);

        ArrayList<User> user= (ArrayList<User>) getIntent().getSerializableExtra("array");





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordNotEqual() && userNameTaken()){
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

    @Override
    public boolean passwordNotEqual() {
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

        return true;
    }


    @Override
    public boolean userNameTaken() {

        return true;
    }


}