package com.example.yuboyang.ilovemovie1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuboyang on 10/10/17.
 */

public class ReviewWrapper {
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @SerializedName("results")
    List<Review> reviews;

}
