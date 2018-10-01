package com.sabihamumcu.tez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sabihamumcu.tez.api.ApiInterface;
import com.sabihamumcu.tez.api.ApiRequest;
import com.sabihamumcu.tez.helper.NotificationData;
import com.sabihamumcu.tez.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvIcerik;
    private NotificationData mNotificationData;
    private EditText searchTitle;
    private Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvIcerik=(TextView) findViewById(R.id.icerik);

        searchTitle=(EditText) findViewById(R.id.et_searchTitle);
        search=(Button) findViewById(R.id.btn_getir);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchTitle.getText().toString().equals(null)){
                    postTitle(searchTitle.getText().toString());
                }
            }
        });
    }

    public void postTitle(String searchTitle){

        ApiInterface apiInterface = ApiRequest.getApiService();

        /*Call<List<Product>> postTitleList=apiInterface.postSearchTitle(searchTitle);
        postTitleList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    List<Product> productList=response.body();


                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i=getIntent();
        if(i!=null){
            String baslik=i.getStringExtra("title");
            String icerik=i.getStringExtra("body");
            tvIcerik.setText(baslik+"\n"+icerik);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String baslik=intent.getStringExtra("title");
        String icerik=intent.getStringExtra("body");
        tvIcerik.setText(baslik+"\n"+icerik);
    }
}
