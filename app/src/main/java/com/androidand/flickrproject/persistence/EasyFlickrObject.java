package com.androidand.flickrproject.persistence;

import com.androidand.flickrproject.model.FlickrType;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Human Booster on 17/10/2016.
 */

@Table(database = FlickDataBase.class)
public class EasyFlickrObject extends BaseModel {
    public EasyFlickrObject(){
    }

    @Column
    @PrimaryKey(autoincrement = true)
    long id; // package-private recommended, not required

    @Column
    @Unique
    String url;

    @Column
    String name;

    @Column
    FlickrType type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlickrType getType() {
        return type;
    }

    public void setType(FlickrType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
