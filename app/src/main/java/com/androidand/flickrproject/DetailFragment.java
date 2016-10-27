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

import com.androidand.flickrproject.persistence.EasyFlickrObject2;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private MaterialFavoriteButton favoriteButton;
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        favoriteButtonAction(view);
        Intent intent = getActivity().getIntent();
        if (intent.getExtras() != null) {
            EasyFlickrObject2 easyFlickrObject2 = (EasyFlickrObject2) intent.getSerializableExtra("easyObject");
            String title = easyFlickrObject2.getName();
            String url = easyFlickrObject2.getUrl();
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
    private void favoriteButtonAction(View view){

        MaterialFavoriteButton materialFavoriteButtonNice =
                (MaterialFavoriteButton) view.findViewById(R.id.favorite_nice);
     //   materialFavoriteButtonNice.setFavorite(true, false);
        materialFavoriteButtonNice.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                        if (favorite) {
                            Log.e("favorite", "yes");
                        } else {
                            Log.e("favorite", "no");
                        }
                    }
                });
    }
}
