package com.androidand.flickrproject.persistence;

import com.androidand.flickrproject.model.FlickrType;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by Human Booster on 17/10/2016.
 */

@Table(database = FlickrDataBase.class)
public class EasyFlickrObject extends BaseModel implements Serializable{
    public EasyFlickrObject(){
    }

    @Column
    @PrimaryKey(autoincrement = true)
    long id; // package-private recommended, not required

    @Column
    String url;

    @Column
    String name;

    @Column
    FlickrType type;

    @Column
    Double lat;

    @Column
    Double lng;

    @Column
    int count;

    @Column
    String search;

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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
