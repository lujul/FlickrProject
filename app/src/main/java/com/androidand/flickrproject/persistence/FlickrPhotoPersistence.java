package com.androidand.flickrproject.persistence;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
    List<EasyFlickrObject> list;
    public FlickrPhotoPersistence(Context context) {
        FlowManager.init(new FlowConfig.Builder(context)
                .openDatabasesOnInit(true).build());
    }

    @Override
    public void saveHistory(EasyFlickrObject easyFlickrObject) {
        easyFlickrObject.setType(FlickrType.HISTORY);
        try {
            easyFlickrObject.save();
          Log.e("Saved Element",easyFlickrObject.getName());

        } catch (Exception e) {
            Log.e("SaveFlickrPhoto", e.toString());
        }
    }



    @Override
    public List<EasyFlickrObject> getHistory() {
      list=SQLite.select()
                .from(EasyFlickrObject.class)
                .where(EasyFlickrObject_Table.type.like(FlickrType.HISTORY.toString()))
                .queryList();
        return list;
    }


}
