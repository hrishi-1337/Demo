package com.hrishikesh.Demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by j on 21-09-2017.
 */

public class MainActivity  extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    LocationRequest mLocationRequest;
    //double lat = 19.167405;
    //double lng = 72.879781;
    double lat,lng;
    boolean firstRun;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        firstRun = prefs.getBoolean("FIRSTRUN", true);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,this);
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            //if(!chosen1) {
            lat = mLastLocation.getLatitude();
            lng = mLastLocation.getLongitude();
            ((LatLng) this.getApplication()).setLat(lat);
            ((LatLng) this.getApplication()).setLng(lng);
            //}
            //LatLng loc = new LatLng(lat, lng);
            //Toast.makeText(this, lat + " or " + lng, Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (firstRun)
                    {
                        // Code to run once
                        for(int i =1 ;i <=10 ;i++) {
                            db.addShop(new Shop(i, "Dockers", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/storefront27.jpg?alt=media&token=4ea27811-4e06-4bf5-8b96-44a583040088", 19.172151, 72.873733));
                            db.addShop(new Shop(i, "Dunkin Donuts", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/dunkin.jpg?alt=media&token=f63c6d5f-b074-47ae-a8ca-6f7974ddbd47", 19.174745, 72.835120));
                            db.addShop(new Shop(i, "Pizza Parlor", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/download.jpg?alt=media&token=1eb9a9bb-495d-416b-9a84-3699214149b9", 19.122123, 72.827567));
                            db.addShop(new Shop(i, "Town Bakers", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/download%20(1).jpg?alt=media&token=01b16918-272f-4a9f-9607-61f559d30360", 19.151315, 77.573661));
                        }
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);

                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("FIRSTRUN", false);
                        editor.commit();
                    }
                    else {
                        Intent intent = new Intent(MainActivity.this, GridActivity.class);
                        //intent.putExtra("lat",lat);
                        //intent.putExtra("lng",lng);
                        startActivity(intent);
                    }
                }
            }, 2000);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
