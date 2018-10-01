package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.helper.Const;
import com.sabihamumcu.tez.helper.FirebaseDBHelper;
import com.sabihamumcu.tez.helper.SessionManager;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private LinearLayout llNav;
    private LinearLayout llr1, llr2, llr3, llr4, llr5, llr6;
    private RelativeLayout rlLoginPart, rlProfilPart;
    private TextView tvMail, tvLogout;
    private SwipeRefreshLayout swipeView;
    private GridView gridView;
    private Switch notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rlLoginPart = (RelativeLayout) findViewById(R.id.loginPart);
        rlProfilPart = (RelativeLayout) findViewById(R.id.profilPart);
        tvMail = (TextView) findViewById(R.id.tv_email);
        tvLogout = (TextView) findViewById(R.id.tv_cikis);
        gridView = (GridView) findViewById(R.id.gv_categories);
        notification = (Switch) findViewById(R.id.sw_notification);

        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
                Color.RED, Color.MAGENTA);
        swipeView.setDistanceToTriggerSync(20);// in dips
        swipeView.setSize(SwipeRefreshLayout.DEFAULT);

        if (SessionManager.isLoggedIn(getApplicationContext())) {
            String mail = SessionManager.getDefaults(getApplicationContext());
            rlLoginPart.setVisibility(View.INVISIBLE);
            rlProfilPart.setVisibility(View.VISIBLE);
            tvMail.setText(mail);
            if (SessionManager.isChecked(getApplicationContext())) {
                notification.setChecked(true);
            }
            tvLogout.setVisibility(View.VISIBLE);
            tvLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionManager.logoutUser(getApplicationContext());
                    rlProfilPart.setVisibility(View.INVISIBLE);
                    tvLogout.setVisibility(View.INVISIBLE);
                    rlLoginPart.setVisibility(View.VISIBLE);

                }
            });
            swipeView.post(new Runnable() {
                @Override
                public void run() {
                    //getCategories();
                    swipeView.setRefreshing(true);
                    loadData();
                    swipeView.setRefreshing(false);
                }
            });
            notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        notification.setChecked(true);
                        SessionManager.setCheck(getApplicationContext());
                        final FirebaseMessaging messaging = FirebaseMessaging.getInstance();
                        messaging.subscribeToTopic("news");
                    } else {
                        notification.setChecked(false);
                        SessionManager.unCheck(getApplicationContext());
                        final FirebaseMessaging messaging = FirebaseMessaging.getInstance();
                        messaging.unsubscribeFromTopic("news");
                    }
                }
            });
        }
        llNav = (LinearLayout) findViewById(R.id.row_nav);
        llr1 = (LinearLayout) llNav.findViewById(R.id.categories);
        llr2 = (LinearLayout) llNav.findViewById(R.id.newProducts);
        llr3 = (LinearLayout) llNav.findViewById(R.id.search);
        //llr4 = (LinearLayout) llNav.findViewById(R.id.favs);
        //llr5 = (LinearLayout) llNav.findViewById(R.id.following);
        llr6 = (LinearLayout) llNav.findViewById(R.id.profile);
        llr6.setBackgroundColor(getResources().getColor(R.color.lightGray));


        clearClicks();
        onClick();
    }

    private void clearClicks() {
        llr1.setBackgroundColor(getResources().getColor(R.color.white));
        llr2.setBackgroundColor(getResources().getColor(R.color.white));
        llr3.setBackgroundColor(getResources().getColor(R.color.white));
        //llr4.setBackgroundColor(getResources().getColor(R.color.white));
        //llr5.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearClicks();
        onClick();
        if (SessionManager.isChecked(getApplicationContext())) {
            notification.setChecked(true);
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, BLoginActivity.class);
        startActivity(intent);
    }

    private void onClick() {
        llr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr1.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(LoginActivity.this, TopCategoriesActivity.class);
                startActivity(intent);
            }
        });
        llr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr3.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        llr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llr2.setBackgroundColor(getResources().getColor(R.color.lightGray));
                Intent intent = new Intent(LoginActivity.this, NewProductsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
        swipeView.setRefreshing(false);
    }

    private void loadData() {
        FirebaseDBHelper.getLovedProduct(getApplicationContext(), SessionManager.getUserDetails(getApplicationContext()).get(Const.KEY_FIREBASE), gridView);
    }


}
