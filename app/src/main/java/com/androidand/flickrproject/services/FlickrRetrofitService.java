package com.androidand.flickrproject.services;

import com.androidand.flickrproject.business.FlickrPhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Human Booster on 17/10/2016.
 */

public interface FlickrRetrofitService {
    @GET("services/rest/?method=flickr.photos.search&safe_search=1&per_page=20&format=json&nojsoncallback=20&api_key=f9d6c61f3ca7d33587ba1e0cf2cdf87b")
    Call<FlickrPhotoResponse> getPhotos(@Query("tags") String query);
}
