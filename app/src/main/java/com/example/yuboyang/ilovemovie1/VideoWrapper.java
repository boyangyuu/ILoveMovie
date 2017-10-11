package com.example.yuboyang.ilovemovie1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by boyangyu on 8/20/2017.
 */

public class VideoWrapper {

    @SerializedName("results")
    private List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}
