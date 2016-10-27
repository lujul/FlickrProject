package com.androidand.flickrproject.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Human Booster on 26/10/2016.
 */

@Database(name = FlickrDBase.NAME, version = FlickrDBase.VERSION)
public class FlickrDBase {
    public static final String NAME = "FlickrDBase"; // we will add the .db extension

    public static final int VERSION = 2;
}
