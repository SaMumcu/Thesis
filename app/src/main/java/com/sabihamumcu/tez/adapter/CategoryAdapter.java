package com.sabihamumcu.tez.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.activity.ProductActivity;
import com.sabihamumcu.tez.model.Product;

import java.util.List;

/**
 * Created by sabis on 3/5/2018.
 */

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    List<String> categoryList;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<String> categoryList) {
        this.context = context;
        this.categoryList=categoryList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.row_category,null);

        FrameLayout btnRow=convertView.findViewById(R.id.btn_row);

        TextView categoryTitle=convertView.findViewById(R.id.tvTitle);
        String[] array=categoryList.get(position).split("/");
        String category=array[3];
        categoryTitle.setText(category);

        btnRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ProductActivity.class);
                intent.putExtra("altKategori", categoryList.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        return convertView;
    }
}
