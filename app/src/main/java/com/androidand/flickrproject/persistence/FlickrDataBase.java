package com.androidand.flickrproject.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Human Booster on 25/10/2016.
 */
@Database(name = FlickrDataBase.NAME, version = FlickrDataBase.VERSION)
public class FlickrDataBase {
    public static final String NAME = "FlickrDataBase"; // we will add the .db extension

    public static final int VERSION = 7;
}
