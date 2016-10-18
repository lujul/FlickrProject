package com.androidand.flickrproject.services;

import com.androidand.flickrproject.business.EasyFlickrObject;

import java.util.List;

/**
 * Created by Human Booster on 17/10/2016.
 */

public interface FlickrServiceListener {
    public void onResponse(List<EasyFlickrObject> easyList);
}
