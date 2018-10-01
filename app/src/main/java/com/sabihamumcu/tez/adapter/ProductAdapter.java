package com.sabihamumcu.tez.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.activity.DetailActivity;
import com.sabihamumcu.tez.helper.Const;
import com.sabihamumcu.tez.helper.FirebaseDBHelper;
import com.sabihamumcu.tez.helper.SessionManager;
import com.sabihamumcu.tez.model.CategoriesModel;
import com.sabihamumcu.tez.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabis on 1/14/2018.
 */

public class ProductAdapter extends BaseAdapter {


    private Context context;
    private List<Product> productList;
    private LayoutInflater layoutInflater;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.row_categories, null);
        final Product element = productList.get(position);
        FrameLayout btnRow = convertView.findViewById(R.id.btn_row);

        ImageView imageView = convertView.findViewById(R.id.imageView1);
        final ImageView ivLoved = convertView.findViewById(R.id.iv_loved);
        TextView productTitle = convertView.findViewById(R.id.textView1);
        TextView productPrice = convertView.findViewById(R.id.textViewPrice1);
        TextView productPrice2 = convertView.findViewById(R.id.textViewPrice2);
        if (SessionManager.isLoggedIn(context)) {
            ivLoved.setVisibility(View.VISIBLE);
            //begenilmis bir urunse kırmızı kalp
            String userFBKey = SessionManager.getUserDetails(context).get(Const.KEY_FIREBASE);
            String userMail = SessionManager.getUserDetails(context).get(Const.KEY_EMAIL);
            FirebaseDBHelper.addedLovedProduct(productList.get(position), userFBKey, context, ivLoved);
        }
        Glide.with(context).load("http:" + element.getImageLocation()).asBitmap().fitCenter().into(imageView);
        productTitle.setText(element.getTitle());
        if (element.getPrice().contains(" ")) {
            String[] prices = element.getPrice().split(" ");
            productPrice.setText(prices[0]);
            productPrice2.setText(prices[1]);
        } else {
            productPrice2.setText(element.getPrice());
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"You clicked the "+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", productList.get(position));
                context.startActivity(intent);

            }
        });
        ivLoved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivLoved.setColorFilter(context.getResources().getColor(R.color.red));
                FirebaseDBHelper.addLovedProduct(element, SessionManager.getDefaults(context), context);
                //FirebaseDBHelper.removeLovedAddNewProduct(productList.get(position),SessionManager.getUserDetails(context).get(Const.KEY_FIREBASE),SessionManager.getUserDetails(context).get(Const.KEY_EMAIL),context,ivLoved);
            }

        });
        return convertView;
    }
}
