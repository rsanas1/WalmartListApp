package com.example.rishi.walmartlistapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rishi on 9/7/2017.
 */

public class Product {

    @SerializedName("productId")
    private String productId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("longDescription")
    private String longDescription;

    @SerializedName("price")
    private String price;

    @SerializedName("productImage")
    private String productImage;

    @SerializedName("reviewRating")
    private float reviewRating;

    @SerializedName("reviewCount")
    private int reviewCount;

    @SerializedName("inStock")
    private boolean inStock;


    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getPrice() {
        return price;
    }

    public String getProductImage() {
        return productImage;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }

    @Override
    public String toString() {
        return productName;
    }
}
