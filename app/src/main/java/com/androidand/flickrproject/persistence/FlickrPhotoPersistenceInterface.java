package com.androidand.flickrproject.persistence;

import com.androidand.flickrproject.model.FlickrType;

import java.util.List;

/**
 * Created by Human Booster on 25/10/2016.
 */

public interface FlickrPhotoPersistenceInterface {

    public void save(EasyFlickrObject easyFlickrObject);
    public List<EasyFlickrObject> getHistory();
}
