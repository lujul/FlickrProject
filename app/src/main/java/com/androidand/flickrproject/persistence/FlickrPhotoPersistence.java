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
    List<EasyFlickrObject2> list;

    public FlickrPhotoPersistence(Context context) {
        FlowManager.init(new FlowConfig.Builder(context)
                .openDatabasesOnInit(true).build());
    }

    @Override
    public void saveHistory(EasyFlickrObject2 easyFlickrObject2, long lat, long lng) {
        easyFlickrObject2.setType(FlickrType.HISTORY);

        try {
            easyFlickrObject2.save();
            Log.e("Saved Element", easyFlickrObject2.getName());

        } catch (Exception e) {
            Log.e("SaveFlickrPhoto", e.toString());
        }

    }

    @Override
    public List<EasyFlickrObject2> getHistory() {
        list = SQLite.select()
                .from(EasyFlickrObject2.class)
                .where(EasyFlickrObject2_Table.type.like(FlickrType.HISTORY.toString()))
                .queryList();
        return list;
    }


}
