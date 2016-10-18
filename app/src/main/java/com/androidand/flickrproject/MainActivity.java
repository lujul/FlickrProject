package com.androidand.flickrproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.androidand.flickrproject.business.EasyFlickrObject;
import com.androidand.flickrproject.services.FlickrService;
import com.androidand.flickrproject.services.FlickrServiceListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FlickrServiceListener{

    ArrayList list;
    FlickrAdapter flick;
    FlickrService flickrService;
    boolean bound = false;
    Context context;

    @Override
    protected void onStart() {
        super.onStart();
        context = this;
        Intent intent = new Intent(this, FlickrService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("test","test");
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
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            FlickrService.ServiceBinder binder = (FlickrService.ServiceBinder) service;
            flickrService = binder.getService();
            flickrService.setContext(context);
            flickrService.setFlickrServiceListener(MainActivity.this);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    @Override
    public void onClick(View v) {
        flickrService.goFlickrPhotoResponse("banane");

    }

    @Override
    public void onResponse(List<EasyFlickrObject> easyList) {
        
        list=new ArrayList();
        for( EasyFlickrObject easyFlickrObject : easyList){
            list.add(easyFlickrObject.getTitle());
        }
        flick.setList(list);

    }
}
