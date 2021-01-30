package com.heshmat.reddittopposts.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Data {
    @Expose
    @SerializedName("is_video")
    private boolean isVideo;
    @Expose
    @SerializedName("children")
    List<Children> children;
    @Expose
    @SerializedName("thumbnail")
    private String thumbnail;
    @Expose
    @SerializedName("author")
    private String author;
    @Expose
    @SerializedName("author_fullname")
    private String authorFullname;
    @Expose
    @SerializedName("num_comments")
    private int numComments;
    @Expose
    @SerializedName("created_utc")
    private double createdUtc;
    @Expose
    @SerializedName("created")
    private double created;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("permalink")
    private String permalink;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("before")
    private String before;
    @Expose
    @SerializedName("after")
    private String after;
    @Expose
    @SerializedName("url_overridden_by_dest")
    private String imgURl;

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorFullname() {
        return authorFullname;
    }

    public void setAuthorFullname(String authorFullname) {
        this.authorFullname = authorFullname;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public double getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(double createdUtc) {
        this.createdUtc = createdUtc;
    }

    public double getCreated() {
        return created;
    }

    public void setCreated(double created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
