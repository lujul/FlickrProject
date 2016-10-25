package com.androidand.flickrproject.business;

import com.androidand.flickrproject.persistence.EasyFlickrObject;

import java.util.List;

/**
 * Created by Human Booster on 17/10/2016.
 */

public interface FlickrServiceListener {
    public void onResponse(List<EasyFlickrObject> easyList);
}
