package com.androidand.flickrproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList list;
    FlickrAdapter flick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        flick = new FlickrAdapter(this);
        ArrayList list = new ArrayList<String>();
        list.add("toto");
        list.add("titi");
        flick.setList(list);
        listView.setAdapter(flick);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        list = new ArrayList();
        list.add("loulou");
        list.add("lulu");
        flick.setList(list);


    }
}
