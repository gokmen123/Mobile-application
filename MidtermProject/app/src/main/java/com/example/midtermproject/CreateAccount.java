package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateAccount extends AppCompatActivity implements AddUser {


    EditText password,passwordAgain,username,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        password=findViewById(R.id.password);
        passwordAgain=findViewById(R.id.password_again);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        setContentView(R.layout.activity_create_account);
        Button btn = findViewById(R.id.createAccountPage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(true);
                if(passwordNotEqual() && userNameTaken()){
                    addUser(username.getText().toString(),password.getText().toString(),email.getText().toString());
                    Intent intent = new Intent(CreateAccount.this,MainActivity.class);;
                    Toast.makeText(getApplicationContext(),"Account successfully created",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean passwordNotEqual() {
        password=findViewById(R.id.password);
        passwordAgain=findViewById(R.id.password_again);
        if(!password.getText().toString().equals(passwordAgain.getText().toString())){
            Toast.makeText(getApplicationContext(),"Passwords are not Equal",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void addUser(String userName, String Password, String email) {
//        user.add(new User(userName,Password,"","","",
//                "","",email,"",new ArrayList<Blog>(),false));

    }

    @Override
    public boolean userNameTaken() {

        return true;
    }
}