package com.androidand.flickrproject.business;

import android.content.Context;

import com.androidand.flickrproject.persistence.EasyFlickrObject;
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
    public void saveHistory(EasyFlickrObject easyFlickrObject) {
        flickrPhotoPersistence.saveHistory(easyFlickrObject);
    }

    @Override
    public List<EasyFlickrObject> getHistory() {
        List<EasyFlickrObject> list=flickrPhotoPersistence.getHistory();
        return list ;
    }
}
