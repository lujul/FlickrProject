package com.androidand.flickrproject.persistence;

import java.util.List;

/**
 * Created by Human Booster on 25/10/2016.
 */

public interface FlickrPhotoPersistenceInterface {

    public void saveHistory(EasyFlickrObject2 easyFlickrObject2, long lat, long lng);
    public List<EasyFlickrObject2> getHistory();
}
