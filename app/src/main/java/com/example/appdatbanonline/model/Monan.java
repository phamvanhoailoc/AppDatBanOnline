package com.example.appdatbanonline.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Monan implements Parcelable {

@SerializedName("Idmonan")
@Expose
private String idmonan;
@SerializedName("Tenmonan")
@Expose
private String tenmonan;
@SerializedName("Hinhmonan")
@Expose
private String hinhmonan;

    protected Monan(Parcel in) {
        idmonan = in.readString();
        tenmonan = in.readString();
        hinhmonan = in.readString();
    }

    public static final Creator<Monan> CREATOR = new Creator<Monan>() {
        @Override
        public Monan createFromParcel(Parcel in) {
            return new Monan(in);
        }

        @Override
        public Monan[] newArray(int size) {
            return new Monan[size];
        }
    };

    public String getIdmonan() {
return idmonan;
}

public void setIdmonan(String idmonan) {
this.idmonan = idmonan;
}

public String getTenmonan() {
return tenmonan;
}

public void setTenmonan(String tenmonan) {
this.tenmonan = tenmonan;
}

public String getHinhmonan() {
return hinhmonan;
}

public void setHinhmonan(String hinhmonan) {
this.hinhmonan = hinhmonan;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idmonan);
        parcel.writeString(tenmonan);
        parcel.writeString(hinhmonan);
    }
}