package com.example.banganhbui.interview.view.model;

public class Result {
    private  String url;
    private String name;
    private String address;
    private String distance;

    public Result(String name, String address, String distance, String url) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
