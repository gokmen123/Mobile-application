package com.example.midtermproject;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ArrayList<User> users = new ArrayList<>();
    TextView title;
    EditText username,password;
    CheckBox remember ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WELCOME");

        TextView sign= findViewById(R.id.createAccount);
        User newUser = new User("ali123","12345","","","",
                "","","","",new ArrayList<Blog>(),false);
        users.add(newUser);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        remember = findViewById(R.id.rememberMes);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(MainActivity.this,CreateAccount.class);

                intent1.putExtra("array",users);
                Log.d("arrayMain", users.toString());
                startActivityForResult(intent1,1);
//
            }
        });

        Button btn = findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkLogIn(username.getText().toString(),password.getText().toString())){
                    User active = getUser(username.getText().toString(),password.getText().toString());
                    SharedPreferences pref=getSharedPreferences("apppref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    if(remember.isChecked()){
                        editor.putString("username",username.getText().toString());
                        editor.putString("password",password.getText().toString());
                        active.setRememberMe(true);
                    }
                    else{
                        editor.remove("username");
                        editor.remove("password");
                        active.setRememberMe(false);
                    }
                    editor.putBoolean("rememberMe",remember.isChecked());
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, BlogDesign.class);
                    Toast.makeText(getApplicationContext(),"Successfully Logged In Welcome",Toast.LENGTH_LONG).show();

                    intent.putExtra("activeUser",active);
                    startActivityForResult(intent,2);

                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password!",Toast.LENGTH_LONG).show();
                }
            }
        });
        SharedPreferences pref=getSharedPreferences("apppref",Context.MODE_PRIVATE);
        String usernames= pref.getString("username","");
        password.setText(pref.getString("password",""));
        username.setText(usernames);
        remember.setChecked(pref.getBoolean("rememberMe",false));


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("newArray", "inside");
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                users= (ArrayList<User>) data.getSerializableExtra("arrayChanged");
                Log.d("onMain", "onActivityResult: ");
                for(int i=0;i<users.size();i++){
                    Log.d("newArray", users.get(i).getUsername());
                }
            }
        }
        if(requestCode==2){
            if(resultCode==RESULT_OK){
                User changedUser = (User) data.getSerializableExtra("active");
                for(int i=0;i<users.size();i++){
                    if(TextUtils.equals(users.get(i).getUsername().toString(),changedUser.getUsername())){
                        Log.d("UserLOGOUT",users.get(i).toString());
                        users.remove(i);
                        users.add(changedUser);

                    }
                }
            }
        }
    }
    public boolean checkLogIn(String usernames,String passwords){
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        if(TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(username.getText().toString())){
            Toast.makeText(getApplicationContext(),"Username or Password is empty!",Toast.LENGTH_LONG).show();
            return false;
        }

        for(int i=0;i<users.size();i++){
            if(TextUtils.equals(usernames,users.get(i).getUsername()) && TextUtils.equals(passwords,users.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }
    public User getUser(String usernames,String passwords){
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        for(int i=0;i<users.size();i++){
            if(TextUtils.equals(usernames,users.get(i).getUsername()) && TextUtils.equals(passwords,users.get(i).getPassword())){
                return users.get(i);
            }
        }
        return null;
    }
}