package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends AppCompatActivity {

    final List<Animal> animals= new ArrayList<Animal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);
        animals.add(new Animal("Cat",R.mipmap.kedi_round));
        animals.add(new Animal("Bird",R.mipmap.bird_round));
        animals.add(new Animal("DoG",R.mipmap.dog1_round));
        animals.add(new Animal("Bear",R.mipmap.bear_round));
        animals.add(new Animal("Lion",R.mipmap.aslan_round));
        final ListView listView = (ListView) findViewById(R.id.listview);
        AnimalAdapter animalAdapter = new AnimalAdapter(this,animals);
        listView.setAdapter(animalAdapter);

    }
}