package com.androidand.flickrproject.business;

import android.content.Context;

import com.androidand.flickrproject.persistence.EasyFlickrObject;
import com.androidand.flickrproject.persistence.FlickrPhotoPersistence;
import com.androidand.flickrproject.model.FlickrType;

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
    public void saveFavory(EasyFlickrObject easyFlickrObject) {
        flickrPhotoPersistence.save(easyFlickrObject);

    }

    @Override
    public List<EasyFlickrObject> getFavory() {
        return flickrPhotoPersistence.getHistory();
    }
}
