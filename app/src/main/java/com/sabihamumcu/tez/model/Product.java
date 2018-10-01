package com.sabihamumcu.tez.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sabis on 2/6/2018.
 */

public class Product implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("imageLocation")
    @Expose
    private String imageLocation;
    @SerializedName("detailPage")
    @Expose
    private String detailPage;

    public String getDetailPage() {
        return detailPage;
    }

    public void setDetailPage(String detailPage) {
        this.detailPage = detailPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

}
