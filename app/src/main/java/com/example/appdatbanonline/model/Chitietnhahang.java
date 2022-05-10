package com.example.appdatbanonline.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Chitietnhahang implements Parcelable {

@SerializedName("Idchitietnhahang")
@Expose
private String idchitietnhahang;
@SerializedName("Tennhahang")
@Expose
private String tennhahang;
@SerializedName("Diachi")
@Expose
private String diachi;
@SerializedName("Sdt")
@Expose
private String sdt;
@SerializedName("Gia")
@Expose
private String gia;
@SerializedName("Giomoicua")
@Expose
private String giomoicua;
@SerializedName("Giodongcua")
@Expose
private String giodongcua;
@SerializedName("Gioithieu")
@Expose
private String gioithieu;
@SerializedName("Menu")
@Expose
private String menu;
@SerializedName("Luuy")
@Expose
private String luuy;
@SerializedName("Hinhanhnhahang")
@Expose
private String hinhanhnhahang;
@SerializedName("Hinhbando")
@Expose
private String hinhbando;
@SerializedName("Idnhahang")
@Expose
private String idnhahang;

    protected Chitietnhahang(Parcel in) {
        idchitietnhahang = in.readString();
        tennhahang = in.readString();
        diachi = in.readString();
        sdt = in.readString();
        gia = in.readString();
        giomoicua = in.readString();
        giodongcua = in.readString();
        gioithieu = in.readString();
        menu = in.readString();
        luuy = in.readString();
        hinhanhnhahang = in.readString();
        hinhbando = in.readString();
        idnhahang = in.readString();
    }

    public static final Creator<Chitietnhahang> CREATOR = new Creator<Chitietnhahang>() {
        @Override
        public Chitietnhahang createFromParcel(Parcel in) {
            return new Chitietnhahang(in);
        }

        @Override
        public Chitietnhahang[] newArray(int size) {
            return new Chitietnhahang[size];
        }
    };

    public String getIdchitietnhahang() {
return idchitietnhahang;
}

public void setIdchitietnhahang(String idchitietnhahang) {
this.idchitietnhahang = idchitietnhahang;
}

public String getTennhahang() {
return tennhahang;
}

public void setTennhahang(String tennhahang) {
this.tennhahang = tennhahang;
}

public String getDiachi() {
return diachi;
}

public void setDiachi(String diachi) {
this.diachi = diachi;
}

public String getSdt() {
return sdt;
}

public void setSdt(String sdt) {
this.sdt = sdt;
}

public String getGia() {
return gia;
}

public void setGia(String gia) {
this.gia = gia;
}

public String getGiomoicua() {
return giomoicua;
}

public void setGiomoicua(String giomoicua) {
this.giomoicua = giomoicua;
}

public String getGiodongcua() {
return giodongcua;
}

public void setGiodongcua(String giodongcua) {
this.giodongcua = giodongcua;
}

public String getGioithieu() {
return gioithieu;
}

public void setGioithieu(String gioithieu) {
this.gioithieu = gioithieu;
}

public String getMenu() {
return menu;
}

public void setMenu(String menu) {
this.menu = menu;
}

public String getLuuy() {
return luuy;
}

public void setLuuy(String luuy) {
this.luuy = luuy;
}

public String getHinhanhnhahang() {
return hinhanhnhahang;
}

public void setHinhanhnhahang(String hinhanhnhahang) {
this.hinhanhnhahang = hinhanhnhahang;
}

public String getHinhbando() {
return hinhbando;
}

public void setHinhbando(String hinhbando) {
this.hinhbando = hinhbando;
}

public String getIdnhahang() {
return idnhahang;
}

public void setIdnhahang(String idnhahang) {
this.idnhahang = idnhahang;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idchitietnhahang);
        parcel.writeString(tennhahang);
        parcel.writeString(diachi);
        parcel.writeString(sdt);
        parcel.writeString(gia);
        parcel.writeString(giomoicua);
        parcel.writeString(giodongcua);
        parcel.writeString(gioithieu);
        parcel.writeString(menu);
        parcel.writeString(luuy);
        parcel.writeString(hinhanhnhahang);
        parcel.writeString(hinhbando);
        parcel.writeString(idnhahang);
    }
}