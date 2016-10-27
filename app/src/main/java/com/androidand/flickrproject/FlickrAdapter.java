package com.androidand.flickrproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidand.flickrproject.persistence.EasyFlickrObject2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class FlickrAdapter extends BaseAdapter {

    private Context context;
    private List<EasyFlickrObject2> list;

    public FlickrAdapter(Context context) {
        this.context = context;
        list = new ArrayList<EasyFlickrObject2>();
    }

    public void setList(List<EasyFlickrObject2> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        }
        TextView title;
        ImageView imageView;
        title =(TextView) convertView.findViewById(R.id.text);
        imageView = (ImageView) convertView.findViewById(R.id.icon);
        Log.e("onExecuted",list.get(position).getUrl() );
        String url=list.get(position).getUrl();
        Picasso.with(context)
                .load(url)
                .resize(100, 100)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(imageView);
        title.setText(list.get(position).getName());
        return convertView;
    }
}
