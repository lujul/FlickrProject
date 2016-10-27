package com.androidand.flickrproject.business;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.androidand.flickrproject.model.FlickrPhotoResponse;
import com.androidand.flickrproject.model.Photo;
import com.androidand.flickrproject.persistence.EasyFlickrObject2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class FlickrService extends Service {

    private Context context;
    private final IBinder binder = new ServiceBinder();
    FlickrRetrofitService service;

    public void setFlickrServiceListener(FlickrServiceListener flickrServiceListener) {
        this.flickrServiceListener = flickrServiceListener;
    }

    private FlickrServiceListener flickrServiceListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FlickrRetrofitService.class);

        return binder;
    }

    public class ServiceBinder extends Binder {
        public FlickrService getService() {
            return FlickrService.this;
        }
    }

    public void flickrToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();

    }

    public List<EasyFlickrObject2> modelConverter(FlickrPhotoResponse flickrPhotoResponse) {
        List<EasyFlickrObject2> easyFlickrObject2List = new ArrayList<EasyFlickrObject2>() {
        };
        List<Photo> photoList = flickrPhotoResponse.getPhotos().getPhoto();
        for (Photo photo : photoList) {
            EasyFlickrObject2 easyFlickrObject2 = new EasyFlickrObject2();
            easyFlickrObject2.setName(photo.getTitle());
            String url = "https://farm" + photo.getFarm() + ".static.flickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
            easyFlickrObject2.setUrl(url);
            easyFlickrObject2List.add(easyFlickrObject2);
        }
        return easyFlickrObject2List;

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void goFlickrPhotoResponse(String query) {

        Call<FlickrPhotoResponse> flickrPhotosResponseCall =
                service.getPhotos(query);
        flickrPhotosResponseCall.enqueue(new Callback<FlickrPhotoResponse>() {

            @Override
            public void onResponse(Call<FlickrPhotoResponse> call, Response<FlickrPhotoResponse> response) {
                String resp = call.request().toString();
                if (call.isExecuted()) {
                    FlickrPhotoResponse flickrPhotoResponse = response.body();
                    flickrServiceListener.onResponse(modelConverter(flickrPhotoResponse));
                    Log.e("onExecuted", resp);
                }
            }

            @Override
            public void onFailure(Call<FlickrPhotoResponse> call, Throwable t) {
                String resp = call.request().toString();
                Log.e("onFailure", resp);
                Toast.makeText(context, resp, Toast.LENGTH_LONG).show();

            }
        });
    }

}
