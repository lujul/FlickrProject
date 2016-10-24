package com.androidand.flickrproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        if (intent.getExtras() != null) {
            String title = intent.getExtras().getString("title");
            String url = intent.getExtras().getString("url");
            TextView textView;
            ImageView imageView;
            textView = (TextView) view.findViewById(R.id.text_detail);
            imageView = (ImageView) view.findViewById(R.id.image_detail);
            Picasso.with(getContext())
                    .load(url)
                    .resize(400, 400)
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(imageView);
            textView.setText(title);
        }

        return view;
    }

}
