package com.androidand.flickrproject.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Human Booster on 24/10/2016.
 */

@Database(name = FlickDataBase.NAME, version = FlickDataBase.VERSION)
public class FlickDataBase {

    public static final String NAME = "FlickDataBase"; // we will add the .db extension

    public static final int VERSION = 4;
}
