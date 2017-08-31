package com.hrishikesh.Demo;

/**
 * Created by j on 30-08-2017.
 */
public class Shop {
    private int id;
    private String name;
    private String address;
    private String url;
    public Shop()
    {
    }
    public Shop(int id,String name,String address, String url)
    {
        this.id=id;
        this.name=name;
        this.address=address;
        this.url=url;
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
    public int getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }


}