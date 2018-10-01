package com.sabihamumcu.tez.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.adapter.CategoryAdapter;
import com.sabihamumcu.tez.api.ApiInterface;
import com.sabihamumcu.tez.api.ApiRequest;
import com.sabihamumcu.tez.model.Product;
import com.sabihamumcu.tez.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoriesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView tv;
    CategoryAdapter categoryAdapter;
    ArrayList<String> categoriesList = new ArrayList<>();
    GridView gridView;
    List<Product> productList;
    private SwipeRefreshLayout swipeView;
    String category;
    ApiInterface apiInterface;
    List<String> categoryList;
    String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Bundle extras = getIntent().getExtras();
        kategori = extras.getString("kategori");

        tv = (TextView) findViewById(R.id.tv_title);
        tv.setText(kategori);

        gridView = (GridView) findViewById(R.id.gv_categories);
        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
                Color.RED, Color.MAGENTA);
        swipeView.setDistanceToTriggerSync(20);// in dips
        swipeView.setSize(SwipeRefreshLayout.DEFAULT);

        swipeView.post(new Runnable() {
            @Override
            public void run() {
                swipeView.setRefreshing(true);
                getCategories();
            }
        });
    }

    private void getCategories() {
        List<String> categoryList = new ArrayList<>();
        Log.i("IPV4",ApiRequest.BASEURL);
        ApiInterface apiInterface = ApiRequest.getApiService();
        SearchCriteria searching = new SearchCriteria(kategori);
        Call<List<String>> subCategories = apiInterface.getSubCategories(searching);
        subCategories.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().equals(null)) {
                        List<String> categories = response.body();
                        categoryAdapter = new CategoryAdapter(getApplicationContext(), categories);
                        gridView.setAdapter(categoryAdapter);
                        swipeView.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                swipeView.setRefreshing(false);
            }
        });

        /*Call<List<String>> categories = apiInterface.getCategories();
        categories.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()!=0){
                        List<String> categories=response.body();
                        for(int i=0;i<categories.size();i++){
                            String[] kategori=categories.get(i).split("/");
                        }
                        categoryAdapter=new CategoryAdapter(getApplicationContext(),categories);
                        gridView.setAdapter(categoryAdapter);
                        swipeView.setRefreshing(false);
                    }
                    *//*for(int i=0;i<categories.size();i++){
                        String[] array=categories.get(i).split("/");
                        //category=array[3];

                        String searchCriteria=categories.get(i);
                        SearchCriteria searching=new SearchCriteria(searchCriteria);
                        final Call<Category> productList = apiInterface.postCategoryTitle(searching);
                        productList.enqueue(new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {
                                if(response.isSuccessful()){
                                    Category category1=response.body();
                                    String price=category1.getAltUrun().get(0).getPrice();
                                }
                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {

                            }
                        });
                    }*//*
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                swipeView.setRefreshing(false);
            }
        });*/
    }

    @Override
    public void onRefresh() {
        getCategories();
    }
}
