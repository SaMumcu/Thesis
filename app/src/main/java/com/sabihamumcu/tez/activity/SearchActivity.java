package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.adapter.ProductAdapter;
import com.sabihamumcu.tez.api.ApiInterface;
import com.sabihamumcu.tez.api.ApiRequest;
import com.sabihamumcu.tez.model.Product;
import com.sabihamumcu.tez.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    ProductAdapter mProductAdapter;
    ArrayList<String> categoriesList = new ArrayList<>();
    GridView gridView;
    List<Product> products;
    ImageView ivSearch;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ivSearch = findViewById(R.id.iv_search);
        etSearch = findViewById(R.id.et_search);
        gridView = findViewById(R.id.gv_search);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText().toString() != "") {

                    ApiInterface apiInterface = ApiRequest.getApiService();
                    String searchCriteria = etSearch.getText().toString();
                    SearchCriteria searching = new SearchCriteria(searchCriteria);
                    final Call<List<Product>> productList = apiInterface.postSearchTitle(searching);
                    productList.enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                            if (response.isSuccessful()) {

                                products = response.body();
                                setData();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {

                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Aramak istediğiniz ürünün adını giriniz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData() {
        List<Product> productListesi = new ArrayList<Product>(products);

        mProductAdapter = new ProductAdapter(this, productListesi);
        gridView.setAdapter(mProductAdapter);
    }

}
