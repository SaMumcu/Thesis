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
import com.sabihamumcu.tez.model.LovedProduct;
import com.sabihamumcu.tez.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabis on 1/14/2018.
 */

public class LovedProductAdapter extends BaseAdapter {


    private Context context;
    private List<LovedProduct> productList;
    private LayoutInflater layoutInflater;

    public LovedProductAdapter(Context context, List<LovedProduct> productList) {
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
        final LovedProduct element = productList.get(position);
        FrameLayout btnRow = convertView.findViewById(R.id.btn_row);

        ImageView imageView = convertView.findViewById(R.id.imageView1);
        final ImageView ivLoved = convertView.findViewById(R.id.iv_loved);
        TextView productTitle = convertView.findViewById(R.id.textView1);
        TextView productPrice = convertView.findViewById(R.id.textViewPrice1);
        TextView productPrice2 = convertView.findViewById(R.id.textViewPrice2);

            ivLoved.setVisibility(View.VISIBLE);
            //begenilmis bir urunse kırmızı kalp
            String userFBKey = SessionManager.getUserDetails(context).get(Const.KEY_FIREBASE);
            String userMail = SessionManager.getUserDetails(context).get(Const.KEY_EMAIL);
            FirebaseDBHelper.addedLovedProduct(productList.get(position).getProduct(), userFBKey, context, ivLoved);

        Glide.with(context).load("http:" + element.getProduct().getImageLocation()).asBitmap().fitCenter().into(imageView);
        productTitle.setText(element.getProduct().getTitle());
        if (element.getProduct().getPrice().contains(" ")) {
            String[] prices = element.getProduct().getPrice().split(" ");
            productPrice.setText(prices[0]);
            productPrice2.setText(prices[1]);
        } else {
            productPrice2.setText(element.getProduct().getPrice());
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"You clicked the "+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", productList.get(position).getProduct());
                context.startActivity(intent);

            }
        });
        ivLoved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String activityname = context.getClass().getName();
                FirebaseDBHelper.removeProduct(productList.get(position).getProduct(),SessionManager.getUserDetails(context).get(Const.KEY_FIREBASE),productList.get(position).getProductKey());
                //FirebaseDBHelper.removeLovedAddNewProduct(productList.get(position),SessionManager.getUserDetails(context).get(Const.KEY_FIREBASE),SessionManager.getUserDetails(context).get(Const.KEY_EMAIL),context,ivLoved);

            }
        });
        return convertView;
    }
}
