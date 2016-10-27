package com.androidand.flickrproject.business;

import android.content.Context;

import com.androidand.flickrproject.persistence.EasyFlickrObject2;
import com.androidand.flickrproject.persistence.FlickrPhotoPersistence;

import java.util.List;

/**
 * Created by Human Booster on 25/10/2016.
 */

public class FlickrManager implements FlickrManagerInterface {

    private FlickrPhotoPersistence flickrPhotoPersistence;
    private Context context;
    public FlickrManager(Context context){
        this.context=context;
        flickrPhotoPersistence= new FlickrPhotoPersistence(context);
    }

    @Override
    public void saveHistory(EasyFlickrObject2 easyFlickrObject2, long lat, long lng) {
        flickrPhotoPersistence.saveHistory(easyFlickrObject2,lat,lng);
    }

    @Override
    public List<EasyFlickrObject2> getHistory() {
        List<EasyFlickrObject2> list=flickrPhotoPersistence.getHistory();
        return list ;
    }
}
