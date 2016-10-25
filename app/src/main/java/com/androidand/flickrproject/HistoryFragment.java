package com.androidand.flickrproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidand.flickrproject.business.FlickrManager;
import com.androidand.flickrproject.persistence.EasyFlickrObject;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements  AdapterView.OnItemClickListener {


    private FlickrAdapter flick;
    private ListView listView;
    private FlickrManager flickrManager;
    List<EasyFlickrObject> historyList;
    boolean bound = false;
    Context context;
    boolean mDualPane;
    Bundle savedInstanceState;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        // Inflate the layout for this fragment
        listView = (ListView) view.findViewById(R.id.history_list);
        flick = new FlickrAdapter(getActivity());
        listView.setAdapter(flick);
        context = getActivity();
        historyList = findHistoryList();
        if(historyList!=null){
            flick.setList(historyList);
            listView.setOnItemClickListener(this);
        }

        this.savedInstanceState=savedInstanceState;
        return view;
    }

    private List<EasyFlickrObject> findHistoryList (){

        flickrManager = new FlickrManager(context);
        historyList = flickrManager.getHistory();
        return historyList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        View detailsFrame = getActivity().findViewById(R.id.fragment_detail);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        if (mDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            listView.setItemChecked(position, true);
            EasyFlickrObject easyFlickrObject = (EasyFlickrObject) flick.getItem(position);
            String title= easyFlickrObject.getName();
            String url=  easyFlickrObject.getUrl();
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
            EasyFlickrObject easyFlickrObject =(EasyFlickrObject)flick.getItem(position);
           intent.putExtra("easyObject", easyFlickrObject);
            startActivity(intent);
        }

    }


}
