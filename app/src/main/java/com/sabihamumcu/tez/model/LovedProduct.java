package com.sabihamumcu.tez.model;

/**
 * Created by sabis on 3/12/2018.
 */

public class LovedProduct {

    Product product;
    String key;
    String productKey;

    public LovedProduct(){

    }

    public LovedProduct(Product product, String key,String productKey) {
        this.product = product;
        this.key = key;
        this.productKey=productKey;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }
}
