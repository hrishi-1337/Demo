package com.hrishikesh.Demo;

import android.app.Application;

/**
 * Created by j on 21-09-2017.
 */

public class LatLng extends Application {

    private double lat;
    private double lng;


    public double getLat() {

        return lat;
    }

    public void setLat(double Lat) {

        lat = Lat;

    }

    public double getLng() {

        return lng;
    }

    public void setLng(double Lng) {

        lng = Lng;
    }
}