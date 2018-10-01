package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.sabihamumcu.tez.R;

import java.util.ArrayList;
import java.util.List;

public class TopCategoriesActivity extends AppCompatActivity {

    private CardView kadin, erkek, cocuk, bebek, aksesuar, firsatlar, trends;
    private LinearLayout llNav;
    private LinearLayout llr1, llr2, llr3, llr4, llr5, llr6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_categories);

        kadin = (CardView) findViewById(R.id.cvKadin);
        erkek = (CardView) findViewById(R.id.cvErkek);
        cocuk = (CardView) findViewById(R.id.cvCocuk);
        bebek = (CardView) findViewById(R.id.cvBebek);
        aksesuar = (CardView) findViewById(R.id.cvAksesuar);
        firsatlar = (CardView) findViewById(R.id.cvFirsatlar);

        llNav = (LinearLayout) findViewById(R.id.row_nav);
        llr1 = (LinearLayout) llNav.findViewById(R.id.categories);
        llr2 = (LinearLayout) llNav.findViewById(R.id.newProducts);
        llr3 = (LinearLayout) llNav.findViewById(R.id.search);
        //llr4=(LinearLayout) llNav.findViewById(R.id.favs);
        //llr5 = (LinearLayout) llNav.findViewById(R.id.following);
        llr6 = (LinearLayout) llNav.findViewById(R.id.profile);

        List<LinearLayout> llList = new ArrayList<>();
        llList.add(llr1);
        llList.add(llr2);
        llList.add(llr3);
        //llList.add(llr4);
        //llList.add(llr5);
        llList.add(llr6);

        clearClicks();
        onClick();
        onMenuClick();

    }

    private void clearClicks() {
        llr2.setBackgroundColor(getResources().getColor(R.color.white));
        llr3.setBackgroundColor(getResources().getColor(R.color.white));
        //llr4.setBackgroundColor(getResources().getColor(R.color.white));
        //llr5.setBackgroundColor(getResources().getColor(R.color.white));
        llr6.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearClicks();
        onClick();
    }

    private void onClick() {
        llr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr3.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(TopCategoriesActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        llr6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr6.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(TopCategoriesActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        llr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr2.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(TopCategoriesActivity.this, NewProductsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void onMenuClick() {
        kadin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopCategoriesActivity.this, SubCategoriesActivity.class);
                intent.putExtra("kategori", "KADIN");
                startActivity(intent);
            }
        });
        erkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopCategoriesActivity.this, SubCategoriesActivity.class);
                intent.putExtra("kategori", "ERKEK");
                startActivity(intent);
            }
        });
        cocuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopCategoriesActivity.this, SubCategoriesActivity.class);
                intent.putExtra("kategori", "ÇOCUK");
                startActivity(intent);
            }
        });
        bebek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopCategoriesActivity.this, SubCategoriesActivity.class);
                intent.putExtra("kategori", "BEBEK");
                startActivity(intent);
            }
        });
        aksesuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopCategoriesActivity.this, SubCategoriesActivity.class);
                intent.putExtra("kategori", "AKSESUAR");
                startActivity(intent);
            }
        });
        firsatlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopCategoriesActivity.this, SubCategoriesActivity.class);
                intent.putExtra("kategori", "FIRSATLAR");
                startActivity(intent);
            }
        });

    }
}
