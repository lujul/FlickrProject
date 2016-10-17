package com.androidand.flickrproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class FlickrAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public FlickrAdapter(Context context) {
        this.context = context;
        list = new ArrayList();
    }

    public void setList(List list) {
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
        title =(TextView) convertView.findViewById(R.id.text);
        title.setText(list.get(position));
        return convertView;
    }
}
