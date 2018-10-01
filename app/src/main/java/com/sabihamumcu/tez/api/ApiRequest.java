package com.sabihamumcu.tez.api;


import android.util.Log;

import com.sabihamumcu.tez.helper.ipv4Helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sabis on 2/6/2018.
 */

public class ApiRequest {

    //static String ip = ipv4Helper.getInstance().getLocalAddress();
    public static String BASEURL = "http:/" + "/192.168.1.33" + ":8082/categories/";

    // Get Retrofit instance
    private static Retrofit getRefrofitInstance() {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return rf;
    }

    // Return Api Service
    public static ApiInterface getApiService() {
        ApiInterface api = getRefrofitInstance().create(ApiInterface.class);
        return api;
    }

}
