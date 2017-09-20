package com.hrishikesh.Demo;

import android.app.Application;
/**
 * Created by j on 20-09-2017.
 */

public class Persist extends Application {

    private double lat;
    private double lng;


    public double getLat() {

        return lat;
    }

    public void setLat(double aLat) {

        lat = aLat;

    }

    public double getLng() {

        return lng;
    }

    public void setLng(double aLng) {

        lng = aLng;
    }

}