package com.androidand.flickrproject;

import android.Manifest;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidand.flickrproject.business.FlickrManager;
import com.androidand.flickrproject.persistence.EasyFlickrObject2;
import com.androidand.flickrproject.business.FlickrService;
import com.androidand.flickrproject.business.FlickrServiceListener;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import com.squareup.picasso.Picasso;

import java.util.List;

import hugo.weaving.DebugLog;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements LocationListener,View.OnClickListener, FlickrServiceListener, AdapterView.OnItemClickListener, GoogleApiClient.ConnectionCallbacks {

    FlickrAdapter flick;
    FlickrService flickrService;
    ListView listView;
    boolean bound = false;
    Context context;
    boolean mDualPane;
    Bundle savedInstanceState;
    FlickrManager flickrManager;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    private LocationRequest mLocationRequest;
    private long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private long FASTEST_INTERVAL = 5000; /* 5 secs */
    public GoogleMap mMap;


    @Override
    public void onStart() {
        super.onStart();
        context = getActivity();
        Intent intent = new Intent(context, FlickrService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    7);
        } else {
            Log.e("connexion", "onStart");
            // permission has been granted, continue as usual

            connectClient();
            // Log.e("location", Location.)
        }
    }

    protected void connectClient() {
        // Connect the client.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .enableAutoManage(getActivity() /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                                @Override
                                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                    Log.e("connexion", "failed");
                                }
                            }
                    )
                    .addConnectionCallbacks(this).addApi(LocationServices.API).build();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.e("onConnected", "ok");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e("oncp", "ok");

            //mMap = new GoogleMap();
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {
                Log.e("mLastLocation", "ok");

                Toast.makeText(getActivity(), "Latitude:" + mLastLocation.getLatitude() + ", Longitude:" + mLastLocation.getLongitude(), Toast.LENGTH_LONG).show();
                startLocationUpdates();
            } else {
                Log.e("mLastLocation", "pas ok");
            }
        } else {
            Log.e("google permission", "pas ok");
            // Permission was denied or request was cancelled
        }

    }
    protected void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, this);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        // Report to the UI that the location was updated
        Log.e("onLocatedChanged","yes");
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("onConnexionSuspended","");
        connectClient();
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Log.e("test", "test");
        listView = (ListView) view.findViewById(R.id.list);
        flick = new FlickrAdapter(getActivity());
        listView.setAdapter(flick);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);
        this.savedInstanceState = savedInstanceState;

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bound) {
            getActivity().unbindService(mConnection);
            bound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            FlickrService.ServiceBinder binder = (FlickrService.ServiceBinder) service;
            flickrService = binder.getService();
            flickrService.setContext(context);
            flickrService.setFlickrServiceListener(SearchFragment.this);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    @DebugLog
    @Override
    public void onClick(View v) {
        flickrService.goFlickrPhotoResponse("banane");
    }

    @Override
    public void onResponse(List<EasyFlickrObject2> easyList) {
        flick.setList(easyList);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        View detailsFrame = getActivity().findViewById(R.id.fragment_detail);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        EasyFlickrObject2 easyFlickrObject2 = (EasyFlickrObject2) flick.getItem(position);
        flickrManager = new FlickrManager(context);

        flickrManager.saveHistory(easyFlickrObject2, Long.MAX_VALUE, Long.MAX_VALUE);
        String title = easyFlickrObject2.getName();
        String url = easyFlickrObject2.getUrl();
        if (mDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            listView.setItemChecked(position, true);
            TextView textView;
            ImageView imageView;
            textView = (TextView) detailsFrame.findViewById(R.id.text_detail);
            imageView = (ImageView) detailsFrame.findViewById(R.id.image_detail);
            Picasso.with(getContext())
                    .load(url)
                    .resize(400, 400)
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(imageView);
            textView.setText(title);

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("easyObject", easyFlickrObject2);
            //intent.putExtra("title", title);
            //intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 7) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Log.e("google permission", "ok");
                mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                        .enableAutoManage(getActivity() /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                                    @Override
                                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                                    }
                                }
                        ).addApi(LocationServices.API).build();

            }
            // We can now safely use the API we requested access to
            //     Location myLocation =
            //         LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            else {
                Log.e("google permission", "pas ok");
                // Permission was denied or request was cancelled
            }
        }
    }


}
