package com.androidand.flickrproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        FlickrAdapter flick = new FlickrAdapter(this);
        ArrayList list = new ArrayList<String>();
        list.add("toto");
        list.add("titi");
        flick.setList(list);
        listView.setAdapter(flick);
    }
}
