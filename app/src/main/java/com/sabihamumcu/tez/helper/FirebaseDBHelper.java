package com.sabihamumcu.tez.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.activity.LoginActivity;
import com.sabihamumcu.tez.adapter.LovedProductAdapter;
import com.sabihamumcu.tez.adapter.ProductAdapter;
import com.sabihamumcu.tez.model.LovedProduct;
import com.sabihamumcu.tez.model.Product;
import com.sabihamumcu.tez.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sabis on 3/11/2018.
 */

public class FirebaseDBHelper {

    static boolean isRegisteredUser=false;
    public static void addUser(final String mail, final String password, final Activity activity) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
        final DatabaseReference dbRefUser = database.getReference();
        String key = dbRefUser.child("user").push().getKey();
        User userData = new User(key, mail, password);
        dbRefUser.child("user").child(key).setValue(userData);
        Helper.displayMessageToast(activity, "Kaydınız yapıldı.");

    }

    public static void isUser(final String mail, final String password, final Activity activity) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
        DatabaseReference dbRefUser = database.getReference();
        DatabaseReference dbUser = dbRefUser.child("user");
        dbUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                boolean addedUser = false;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue(User.class);
                    if (user.getMail().equals(mail) && user.getPassword().equals(password)) {
                        Helper.displayMessageToast(activity, "Kullanıcı bulundu.");
                        addedUser = true;
                        SessionManager.setDefaults(user.getKey(), mail, activity);
                        Intent intent = new Intent(activity, LoginActivity.class);
                        activity.startActivity(intent);
                    }
                }
                if (!addedUser) {
                    Helper.displayMessageToast(activity, "Kullanıcı bulunamadı. Tekrar deneyiniz.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public static void addLovedProduct(Product product, String mail,Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
        DatabaseReference dbRefUser = database.getReference();
        String keyLovedProduct = dbRefUser.child("lovedProduct").push().getKey();
        HashMap<String,String> hm= SessionManager.getUserDetails(context);
        LovedProduct lovedProduct=new LovedProduct(product,hm.get(Const.KEY_FIREBASE),keyLovedProduct);
        dbRefUser.child("lovedProducts").child(keyLovedProduct).setValue(lovedProduct);

    }

    public static void addedLovedProduct(final Product product, final String key, final Context context, final ImageView imageView){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
        DatabaseReference dbRefUser = database.getReference();
        DatabaseReference dbProduct = dbRefUser.child("lovedProducts");
        dbProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    LovedProduct lovedProduct=dataSnapshot1.getValue(LovedProduct.class);
                    if(lovedProduct!=null){
                        if(lovedProduct.getKey().equals(key) && lovedProduct.getProduct().getPrice().equals(product.getPrice()) &&
                                lovedProduct.getProduct().getImageLocation().equals(product.getImageLocation()) &&
                                lovedProduct.getProduct().getDetailPage().equals(product.getDetailPage()) &&
                                lovedProduct.getProduct().getTitle().equals(product.getTitle())){
                            imageView.setColorFilter(context.getResources().getColor(R.color.red));
                            System.out.println(lovedProduct.getProduct().getTitle());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void getLovedProduct(final Context context, final String key, final GridView gridView){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
        DatabaseReference dbRefUser = database.getReference();
        DatabaseReference dbProduct = dbRefUser.child("lovedProducts");
        dbProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<LovedProduct> productList=new ArrayList<LovedProduct>();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    LovedProduct lovedProduct=dataSnapshot1.getValue(LovedProduct.class);
                    if(lovedProduct!=null) {
                        if(lovedProduct.getKey().equals(key)){
                            productList.add(lovedProduct);
                        }
                    }
                }
                if(productList.size()!=0){
                    LovedProductAdapter mProductAdapter=new LovedProductAdapter(context, productList);
                    gridView.setAdapter(mProductAdapter);
                }
                else{

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void removeLovedAddNewProduct(final Product product, final String key, final String email, final Context context, final ImageView imageView){

        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
        final DatabaseReference dbRefUser = database.getReference();
        final DatabaseReference dbProduct = dbRefUser.child("lovedProducts");
        dbProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isLovedProduct = false;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    LovedProduct lovedProduct=dataSnapshot1.getValue(LovedProduct.class);
                    if(lovedProduct.getKey().equals(key) && lovedProduct.getProduct().equals(product)){
                        imageView.setColorFilter(context.getResources().getColor(R.color.black));
                        isLovedProduct=true;
                        dbProduct.child(key).removeValue();
                    }
                }
                if(!isLovedProduct){
                    addLovedProduct(product,email,context);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void removeProduct(final Product product, final String key, final String productKey){
        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
        final DatabaseReference dbRefUser = database.getReference();
        final DatabaseReference dbProduct = dbRefUser.child("lovedProducts");
        dbProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isLovedProduct = false;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    LovedProduct lovedProduct=dataSnapshot1.getValue(LovedProduct.class);
                    if(lovedProduct.getKey().equals(key) && lovedProduct.getProduct().getTitle().equals(product.getTitle()) &&
                            lovedProduct.getProduct().getImageLocation().equals(product.getImageLocation())){
                        dbProduct.child(productKey).setValue(null);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
