package com.example.midtermproject;;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class HomeScreen extends Fragment {
    ImageButton img;
    boolean onClicked=false;

    TextView author,date,thoughts,title;
    ListView listView;
    ArrayList <String> getList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home_screen, container, false);
       /* TextView textView = view.findViewById(R.id.home_thought);
        author=view.findViewById(R.id.author);
        date=view.findViewById(R.id.date);
        thoughts=view.findViewById(R.id.home_thought);
        title=view.findViewById(R.id.home_title);
        listView = view.findViewById(R.id.home_listView);
        arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.listview_blog,getList);

        textView.setMovementMethod(new ScrollingMovementMethod());*/

        /*img=view.findViewById(R.id.like);
        img.setBackgroundResource(R.drawable.like);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageClick();
            }
        });*/
        return view;
    }
    public void ImageClick() {


        onClicked =!onClicked ;
        if(onClicked){
            img.setBackgroundResource(R.drawable.dislike);
        }
        else{
            img.setBackgroundResource(R.drawable.like);
        }

    }
}