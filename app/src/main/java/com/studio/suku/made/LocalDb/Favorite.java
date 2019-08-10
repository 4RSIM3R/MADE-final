package com.studio.suku.made.LocalDb;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {

    private String name;
    private String image;
    private Double rate;
    private String overview;
    private String type;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeValue(this.rate);
        dest.writeString(this.overview);
        dest.writeString(this.type);
    }

    public Favorite() {
    }

    private Favorite(Parcel in) {
        this.name = in.readString();
        this.image = in.readString();
        this.rate = (Double) in.readValue(Double.class.getClassLoader());
        this.overview = in.readString();
        this.type = in.readString();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
