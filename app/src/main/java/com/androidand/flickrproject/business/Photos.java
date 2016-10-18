package com.androidand.flickrproject.business;

import java.util.List;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class Photos {
    private String page;
    private String pages;
    private String perpages;
    private String total;
    private List<Photo> photo;
    public Photos() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPerpages() {
        return perpages;
    }

    public void setPerpages(String perpages) {
        this.perpages = perpages;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}
