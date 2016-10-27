package com.androidand.flickrproject.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Human Booster on 25/10/2016.
 */
@Database(name = FlickrDataBaseBis.NAME, version = FlickrDataBaseBis.VERSION)
public class FlickrDataBaseBis {
    public static final String NAME = "FlickrDataBase2"; // we will add the .db extension

    public static final int VERSION = 2;
}
