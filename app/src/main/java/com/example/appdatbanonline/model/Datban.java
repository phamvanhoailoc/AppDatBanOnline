package com.example.appdatbanonline.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datban {

@SerializedName("Tennhahang")
@Expose
private String tennhahang;
@SerializedName("Sdt")
@Expose
private String sdt;
@SerializedName("Iddatban")
@Expose
private String iddatban;
@SerializedName("Ngayden")
@Expose
private String ngayden;
@SerializedName("Gioden")
@Expose
private String gioden;
@SerializedName("Nguoilon")
@Expose
private String nguoilon;
@SerializedName("Treem")
@Expose
private String treem;

public String getTennhahang() {
return tennhahang;
}

public void setTennhahang(String tennhahang) {
this.tennhahang = tennhahang;
}

public String getSdt() {
return sdt;
}

public void setSdt(String sdt) {
this.sdt = sdt;
}

public String getIddatban() {
return iddatban;
}

public void setIddatban(String iddatban) {
this.iddatban = iddatban;
}

public String getNgayden() {
return ngayden;
}

public void setNgayden(String ngayden) {
this.ngayden = ngayden;
}

public String getGioden() {
return gioden;
}

public void setGioden(String gioden) {
this.gioden = gioden;
}

public String getNguoilon() {
return nguoilon;
}

public void setNguoilon(String nguoilon) {
this.nguoilon = nguoilon;
}

public String getTreem() {
return treem;
}

public void setTreem(String treem) {
this.treem = treem;
}

}