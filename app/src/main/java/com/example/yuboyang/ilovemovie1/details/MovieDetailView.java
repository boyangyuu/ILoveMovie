package com.example.yuboyang.ilovemovie1.details;

import com.example.yuboyang.ilovemovie1.Review;
import com.example.yuboyang.ilovemovie1.Video;

import java.util.List;

/**
 * Created by yuboyang on 10/10/17.
 */

public interface MovieDetailView {
    void showTrailers(List<Video> videos);
    void showReviews(List<Review> reviews);
}
