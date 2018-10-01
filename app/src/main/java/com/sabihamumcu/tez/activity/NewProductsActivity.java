package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.adapter.ProductAdapter;
import com.sabihamumcu.tez.api.ApiInterface;
import com.sabihamumcu.tez.api.ApiRequest;
import com.sabihamumcu.tez.model.Category;
import com.sabihamumcu.tez.model.Product;
import com.sabihamumcu.tez.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProductsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ProductAdapter mProductAdapter;
    ArrayList<String> categoriesList = new ArrayList<>();
    GridView gridView;
    List<Product> productList;
    private ImageView ivSearch;
    private SwipeRefreshLayout swipeView;
    ApiInterface apiInterface;
    String altKategori;
    private TextView tvTitle;
    private ImageView ivLoved;
    private LinearLayout llNav;
    private LinearLayout llr1, llr2, llr3, llr4, llr5, llr6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_products);

        ivSearch = (ImageView) findViewById(R.id.iv_search);
        gridView = (GridView) findViewById(R.id.gv_categories);
        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_view);

        llNav = (LinearLayout) findViewById(R.id.row_nav);
        llr1 = (LinearLayout) llNav.findViewById(R.id.categories);
        llr2 = (LinearLayout) llNav.findViewById(R.id.newProducts);
        llr2.setBackgroundColor(getResources().getColor(R.color.lightGray));
        llr3 = (LinearLayout) llNav.findViewById(R.id.search);
        //llr4=(LinearLayout) llNav.findViewById(R.id.favs);
        //llr5 = (LinearLayout) llNav.findViewById(R.id.following);
        llr6 = (LinearLayout) llNav.findViewById(R.id.profile);

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
                Intent intent = new Intent(NewProductsActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        ApiInterface apiInterface = ApiRequest.getApiService();
        SearchCriteria searching = new SearchCriteria("yeniGelenler");
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
    protected void onResume() {
        super.onResume();
        clearClicks();
        onClick();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private void clearClicks() {
        llr1.setBackgroundColor(getResources().getColor(R.color.white));
        llr3.setBackgroundColor(getResources().getColor(R.color.white));
        //llr4.setBackgroundColor(getResources().getColor(R.color.white));
        //llr5.setBackgroundColor(getResources().getColor(R.color.white));
        llr6.setBackgroundColor(getResources().getColor(R.color.white));
    }
    private void onClick() {
        llr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr3.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(NewProductsActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        llr6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr6.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(NewProductsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        llr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr1.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(NewProductsActivity.this, TopCategoriesActivity.class);
                startActivity(intent);
            }
        });

    }
}
