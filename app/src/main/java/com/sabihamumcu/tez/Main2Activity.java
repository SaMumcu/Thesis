package com.sabihamumcu.tez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.model.CategoriesModel;
import com.sabihamumcu.tez.model.Category;
import com.sabihamumcu.tez.model.Product;
import com.sabihamumcu.tez.model.SubCategories;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = (TextView) findViewById(R.id.productTitle);

        Bundle extras = getIntent().getExtras();
        /*if (extras != null) {
            String notification = (String) extras.get("result");
            Toast.makeText(getApplicationContext(), notification, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "noo", Toast.LENGTH_SHORT).show();
        }*/
        if (extras != null) {
            String dataTitle = (String) extras.get("productTitle");
            List<Product> dataProductDetail = ((Category) extras.get("productDetail")).getAltUrun();
            tv.setText(dataProductDetail.get(0).getTitle());

        }
    }
}
