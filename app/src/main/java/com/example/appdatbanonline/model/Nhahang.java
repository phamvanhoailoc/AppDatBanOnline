package com.example.appdatbanonline.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Nhahang implements Parcelable {

@SerializedName("Idnhahang")
@Expose
private String idnhahang;
@SerializedName("Tennhahang")
@Expose
private String tennhahang;
@SerializedName("Diachinhahang")
@Expose
private String diachinhahang;
@SerializedName("Gia")
@Expose
private String gia;
@SerializedName("Giomocua")
@Expose
private String giomocua;
@SerializedName("Giodongcua")
@Expose
private String giodongcua;
@SerializedName("Hinhanhnhahang")
@Expose
private String hinhanhnhahang;
@SerializedName("Idmonan")
@Expose
private String idmonan;

    protected Nhahang(Parcel in) {
        idnhahang = in.readString();
        tennhahang = in.readString();
        diachinhahang = in.readString();
        gia = in.readString();
        giomocua = in.readString();
        giodongcua = in.readString();
        hinhanhnhahang = in.readString();
        idmonan = in.readString();
    }

    public static final Creator<Nhahang> CREATOR = new Creator<Nhahang>() {
        @Override
        public Nhahang createFromParcel(Parcel in) {
            return new Nhahang(in);
        }

        @Override
        public Nhahang[] newArray(int size) {
            return new Nhahang[size];
        }
    };

    public String getIdnhahang() {
return idnhahang;
}

public void setIdnhahang(String idnhahang) {
this.idnhahang = idnhahang;
}

public String getTennhahang() {
return tennhahang;
}

public void setTennhahang(String tennhahang) {
this.tennhahang = tennhahang;
}

public String getDiachinhahang() {
return diachinhahang;
}

public void setDiachinhahang(String diachinhahang) {
this.diachinhahang = diachinhahang;
}

public String getGia() {
return gia;
}

public void setGia(String gia) {
this.gia = gia;
}

public String getGiomocua() {
return giomocua;
}

public void setGiomocua(String giomocua) {
this.giomocua = giomocua;
}

public String getGiodongcua() {
return giodongcua;
}

public void setGiodongcua(String giodongcua) {
this.giodongcua = giodongcua;
}

public String getHinhanhnhahang() {
return hinhanhnhahang;
}

public void setHinhanhnhahang(String hinhanhnhahang) {
this.hinhanhnhahang = hinhanhnhahang;
}

public String getIdmonan() {
return idmonan;
}

public void setIdmonan(String idmonan) {
this.idmonan = idmonan;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idnhahang);
        parcel.writeString(tennhahang);
        parcel.writeString(diachinhahang);
        parcel.writeString(gia);
        parcel.writeString(giomocua);
        parcel.writeString(giodongcua);
        parcel.writeString(hinhanhnhahang);
        parcel.writeString(idmonan);
    }
}