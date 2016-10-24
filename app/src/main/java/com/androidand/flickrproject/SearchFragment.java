package com.androidand.flickrproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidand.flickrproject.business.EasyFlickrObject;
import com.androidand.flickrproject.services.FlickrService;
import com.androidand.flickrproject.services.FlickrServiceListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener, FlickrServiceListener, AdapterView.OnItemClickListener {


    List<EasyFlickrObject> list;
    FlickrAdapter flick;
    FlickrService flickrService;
    ListView listView;
    boolean bound = false;
    Context context;
    boolean mDualPane;
    Bundle savedInstanceState;

    @Override
    public void onStart() {
        super.onStart();
        context = getActivity();
        Intent intent = new Intent(context, FlickrService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Log.e("test", "test");
        listView = (ListView) view.findViewById(R.id.list);
        flick = new FlickrAdapter(getActivity());
        listView.setAdapter(flick);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);
        this.savedInstanceState=savedInstanceState;


        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bound) {
            getActivity().unbindService(mConnection);
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
            flickrService.setFlickrServiceListener(SearchFragment.this);
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
        flick.setList(easyList);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        View detailsFrame = getActivity().findViewById(R.id.fragment_detail);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        String title=  ((EasyFlickrObject)flick.getItem(position)).getTitle();
        String url=  ((EasyFlickrObject)flick.getItem(position)).getUrl();
        if (mDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            listView.setItemChecked(position, true);
            TextView textView;
            ImageView imageView;
            textView = (TextView) detailsFrame.findViewById(R.id.text_detail);
            imageView = (ImageView) detailsFrame.findViewById(R.id.image_detail);
            Picasso.with(getContext())
                    .load(url)
                    .resize(400, 400)
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(imageView);
            textView.setText(title);

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.

            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }







}
