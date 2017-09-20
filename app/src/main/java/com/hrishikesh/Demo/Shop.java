package com.hrishikesh.Demo;

/**
 * Created by j on 30-08-2017.
 */
public class Shop {
    private int id;
    private String name;
    private String address;
    private String url;
    private double lat;
    private double lng;
    public Shop()
    {
    }
    public Shop(int id,String name,String address, String url,double lat,double lng)
    {
        this.id=id;
        this.name=name;
        this.address=address;
        this.url=url;
        this.lat=lat;
        this.lng=lng;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(String address) {
        this.address = address;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setLat(double lat) {
        this.lat=lat;
    }
    public void setLng(double lng) {
        this.lng=lng;
    }
    public int getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }
}