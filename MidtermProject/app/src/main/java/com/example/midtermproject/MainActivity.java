package com.example.midtermproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ArrayList<User> users = new ArrayList<>();
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView sign= findViewById(R.id.createAccount);
        User newUser = new User("ali123","12345","","","",
                "","","","",new ArrayList<Blog>(),false);
        users.add(newUser);
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
                Intent intent = new Intent(MainActivity.this, BlogDesign.class);
                startActivity(intent);
            }
        });

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
    }
}