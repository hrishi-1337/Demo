package com.hrishikesh.Demo;

/**
 * Created by j on 30-08-2017.
 */
public class Shop {
    private int Id;
    private String Name;
    private String Address;
    private String Url;
    private double Lat;
    private double Lng;
    public Shop()
    {
    }
    public Shop(int id,String name,String address, String url,double lat,double lng)
    {
        this.Id=id;
        this.Name=name;
        this.Address=address;
        this.Url=url;
        this.Lat=lat;
        this.Lng=lng;
    }
    public void setId(int id) {
        this.Id = id;
    }
    public void setName(String name) {
        this.Name = name;
    }


    public void setAddress(String address) {
        this.Address = address;
    }
    public void setUrl(String url) {
        this.Url = url;
    }
    public void setLat(double lat) {
        this.Lat=lat;
    }
    public void setLng(double lng) {
        this.Lng=lng;
    }
    public int getId() {
        return Id;
    }
    public String getAddress() {
        return Address;
    }
    public String getName() {
        return Name;
    }

    public String getUrl() {
        return Url;
    }
    public double getLat() {
        return Lat;
    }
    public double getLng() {
        return Lng;
    }
}