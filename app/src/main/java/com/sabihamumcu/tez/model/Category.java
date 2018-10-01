package com.sabihamumcu.tez.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sabis on 2/5/2018.
 */

public class Category {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("altUrun")
    @Expose
    private List<Product> altUrun = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getAltUrun() {
        return altUrun;
    }

    public void setAltUrun(List<Product> altUrun) {
        this.altUrun = altUrun;
    }
}
