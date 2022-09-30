package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnArrayAdapter;
    Button btnCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnArrayAdapter=findViewById(R.id.ArrayAdapter);
        btnArrayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, activity_array_adapter.class);
                startActivity(intent);
            }
        });
        btnCustomAdapter=findViewById(R.id.CustomAdapter);
        btnCustomAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTwo=new Intent(MainActivity.this,CustomAdapter.class);
                startActivity(intentTwo);
            }
        });

    }
}