package com.heshmat.reddittopposts.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditResponse {
//https://www.reddit.com/top.json?after=t3_l78uct&limit=1
    @Expose
    @SerializedName("data")
    private Data data;
    @Expose
    @SerializedName("kind")
    private String kind;


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
