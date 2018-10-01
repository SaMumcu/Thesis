package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sabihamumcu.tez.R;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_cart:
                        // do something here
                        Intent intent=new Intent(NavigationActivity.this,TopCategoriesActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_profile:
                        // do something here
                        return true;
                    case R.id.navigation_profile2:
                        // do something here
                        return true;
                }
                return false;
            }
        });
    }


}
