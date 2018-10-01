package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.model.Product;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private TextView title,price;
    private ImageView productPicture;
    private String baseUrl="https://www.koton.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_2);

        /*title=(TextView) findViewById(R.id.textView1);
        price=(TextView) findViewById(R.id.textView2);
        productPicture=(ImageView) findViewById(R.id.imageView1);
*/
        Product product= (Product) getIntent().getSerializableExtra("detail");
        WebView browser = (WebView) findViewById(R.id.webview);
        browser.setWebViewClient(new MyWebView());
        WebSettings webSettings=browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        browser.loadUrl(baseUrl+product.getDetailPage());
        /*title.setText(product.getDetailPage());
        price.setText(product.getPrice());
        Glide.with(this).load("http:"+product.getImageLocation()).asBitmap().fitCenter().into(productPicture);*/
    }
    private class MyWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
