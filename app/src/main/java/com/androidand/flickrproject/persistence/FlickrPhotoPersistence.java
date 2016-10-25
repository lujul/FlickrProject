package com.androidand.flickrproject.persistence;

import android.content.Context;
import android.util.Log;

import com.androidand.flickrproject.model.FlickrType;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Human Booster on 24/10/2016.
 */

public class FlickrPhotoPersistence implements FlickrPhotoPersistenceInterface {

    private Context context;
    public FlickrPhotoPersistence(Context context) {
        FlowManager.init(new FlowConfig.Builder(context)
                .openDatabasesOnInit(true).build());
    }

    @Override
    public void save(EasyFlickrObject easyFlickrObject) {
        try {
            easyFlickrObject.save();
        } catch (Exception e) {
            Log.w("SaveFlickrPhoto", e.toString());
        }
    }

    @Override
    public List<EasyFlickrObject> getHistory() {
        return SQLite.select()
                .from(EasyFlickrObject.class)
                .where(EasyFlickrObject_Table.type.like(FlickrType.HISTORY + "%"))
                .queryList();
    }


}
