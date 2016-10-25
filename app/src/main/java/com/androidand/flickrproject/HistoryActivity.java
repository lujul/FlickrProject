package com.androidand.flickrproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity {

    //Defining Views
    private FloatingActionButton mShowSnackbarButton;
    public DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        drawerCreator();
        menuButtonsActions();
    }

    private void menuButtonsActions(){

        //Drawer Buttons
        Button button_a = (Button) findViewById(R.id.button_a);
        button_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", "Click on history");
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        Button button_b = (Button) findViewById(R.id.button_b);
        button_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity
                        .class);
                startActivity(intent);
            }
        });

        Button button_c = (Button) findViewById(R.id.button_c);
        button_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent intent = new Intent(HistoryActivity.this, MapActivity
           //             .class);
           //     startActivity(intent);
            }
        });

    }
    private void drawerCreator(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mShowSnackbarButton = (FloatingActionButton) findViewById(R.id.fab);
        mShowSnackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }else{
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }
}
