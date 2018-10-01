package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.adapter.ProductAdapter;
import com.sabihamumcu.tez.api.ApiInterface;
import com.sabihamumcu.tez.api.ApiRequest;
import com.sabihamumcu.tez.helper.SessionManager;
import com.sabihamumcu.tez.model.Category;
import com.sabihamumcu.tez.model.Product;
import com.sabihamumcu.tez.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ProductAdapter mProductAdapter;
    ArrayList<String> categoriesList = new ArrayList<>();
    GridView gridView;
    List<Product> productList;
    ImageView ivSearch;
    private SwipeRefreshLayout swipeView;
    String category;
    ApiInterface apiInterface;
    String altKategori;
    TextView tvTitle;
    ImageView ivLoved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Bundle extras = getIntent().getExtras();
        altKategori = extras.getString("altKategori");

        ivSearch = (ImageView) findViewById(R.id.iv_search);
        gridView = (GridView) findViewById(R.id.gv_categories);
        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        String[] array = altKategori.split("/");
        String category = array[3];
        tvTitle.setText(category);
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
                Color.RED, Color.MAGENTA);
        swipeView.setDistanceToTriggerSync(20);// in dips
        swipeView.setSize(SwipeRefreshLayout.DEFAULT);

        swipeView.post(new Runnable() {
            @Override
            public void run() {
                //getCategories();
                swipeView.setRefreshing(true);
                loadData();
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private void getCategories(){
        apiInterface = ApiRequest.getApiService();
        Call<List<String>> categories = apiInterface.getCategories();
        categories.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> categories=response.body();
                    for(int i=0;i<categories.size();i++){
                        String[] array=categories.get(i).split("/");
                        category=array[3];

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
                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }
*/
    private void setData() {
        List<Product> products = new ArrayList<Product>(productList);
        mProductAdapter = new ProductAdapter(this, products);
        gridView.setAdapter(mProductAdapter);

    }

    private void loadData() {
        ApiInterface apiInterface = ApiRequest.getApiService();
        SearchCriteria searching = new SearchCriteria(altKategori);
        Call<Category> baseModelCall = apiInterface.postCategoryTitle(searching);
        baseModelCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAltUrun().size() != 0) {
                        productList = response.body().getAltUrun();
                        mProductAdapter = new ProductAdapter(getApplicationContext(), productList);
                        gridView.setAdapter(mProductAdapter);
                        swipeView.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                swipeView.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
