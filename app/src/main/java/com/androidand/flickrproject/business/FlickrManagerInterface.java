package com.androidand.flickrproject.business;

import com.androidand.flickrproject.persistence.EasyFlickrObject;

import java.util.List;

/**
 * Created by Human Booster on 25/10/2016.
 */

public interface FlickrManagerInterface {

    public void saveFavory(EasyFlickrObject easyFlickrObject);
    public List<EasyFlickrObject> getFavory();


}
