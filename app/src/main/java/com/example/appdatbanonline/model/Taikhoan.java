package com.example.appdatbanonline.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Taikhoan implements Parcelable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Sdt")
    @Expose
    private String sdt;
    @SerializedName("Password")
    @Expose
    private String password;

    protected Taikhoan(Parcel in) {
        id = in.readString();
        sdt = in.readString();
        password = in.readString();
    }

    public static final Creator<Taikhoan> CREATOR = new Creator<Taikhoan>() {
        @Override
        public Taikhoan createFromParcel(Parcel in) {
            return new Taikhoan(in);
        }

        @Override
        public Taikhoan[] newArray(int size) {
            return new Taikhoan[size];
        }
    };

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getSdt() {
    return sdt;
    }

    public void setSdt(String sdt) {
    this.sdt = sdt;
    }

    public String getPassword() {
    return password;
    }

    public void setPassword(String password) {
    this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(sdt);
        parcel.writeString(password);
    }
}