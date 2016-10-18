package com.androidand.flickrproject.business;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class FlickrPhotoResponse {

    private Photos photos;
    private String stat;

    public FlickrPhotoResponse() {
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
