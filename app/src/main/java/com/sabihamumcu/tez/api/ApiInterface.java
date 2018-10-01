package com.sabihamumcu.tez.api;

import com.sabihamumcu.tez.model.Category;
import com.sabihamumcu.tez.model.Product;
import com.sabihamumcu.tez.model.SearchCriteria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sabis on 2/5/2018.
 */

public interface ApiInterface {

    @GET("first")
    Call<Category> getData();

    @GET("last")
    Call<Category> getLastData();

    @GET("allCategories")
    Call<List<String>> getCategories();

    @POST("get")
    Call<List<Product>> postSearchTitle(@Body SearchCriteria title);

    @POST("productsInCategory")
    Call<Category> postCategoryTitle(@Body SearchCriteria categoryTitle);

    @POST("subCategoriesInCategory")
    Call<List<String>> getSubCategories(@Body SearchCriteria categoryTitle);

}
