package com.heshmat.reddittopposts.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;

public class Data implements Parcelable {
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
    private Bitmap thumbBitmap;
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

    public Bitmap getThumbBitmap() {
        return thumbBitmap;
    }

    public void setThumbBitmap(Bitmap thumbBitmap) {
        this.thumbBitmap = thumbBitmap;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isVideo ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.children);
        dest.writeString(this.thumbnail);
        dest.writeString(this.author);
        dest.writeString(this.authorFullname);
        dest.writeInt(this.numComments);
        dest.writeDouble(this.createdUtc);
        dest.writeDouble(this.created);
        dest.writeString(this.title);
        dest.writeString(this.permalink);
        dest.writeString(this.name);
        dest.writeString(this.before);
        dest.writeString(this.after);
        dest.writeString(this.imgURl);
    }

    protected Data(Parcel in) {
        this.isVideo = in.readByte() != 0;
        this.children = in.createTypedArrayList(Children.CREATOR);
        this.thumbnail = in.readString();
        this.author = in.readString();
        this.authorFullname = in.readString();
        this.numComments = in.readInt();
        this.createdUtc = in.readDouble();
        this.created = in.readDouble();
        this.title = in.readString();
        this.permalink = in.readString();
        this.name = in.readString();
        this.before = in.readString();
        this.after = in.readString();
        this.imgURl = in.readString();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public Data(boolean isVideo, String thumbnail, String author, String authorFullname, int numComments, double createdUtc, double created, String title, String permalink, String name, String before, String after, String imgURl) {
        this.isVideo = isVideo;
        this.thumbnail = thumbnail;
        this.author = author;
        this.authorFullname = authorFullname;
        this.numComments = numComments;
        this.createdUtc = createdUtc;
        this.created = created;
        this.title = title;
        this.permalink = permalink;
        this.name = name;
        this.before = before;
        this.after = after;
        this.imgURl = imgURl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return author.equals(data.author) &&
                authorFullname.equals(data.authorFullname) &&
                title.equals(data.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, authorFullname, title);
    }
}
