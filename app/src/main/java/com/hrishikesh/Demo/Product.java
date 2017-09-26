package com.hrishikesh.Demo;

/**
 * Created by j on 26-09-2017.
 */

public class Product {
    private int id;
    private String name;
    private String url;
    private double price;

    public Product() {
    }

    public Product(int id, String name,String url ,double price) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

