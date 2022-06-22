package com.example.appdatbanonline.retofit2;


import com.example.appdatbanonline.model.Chitietnhahang;
import com.example.appdatbanonline.model.Datban;
import com.example.appdatbanonline.model.Monan;
import com.example.appdatbanonline.model.Nhahang;
import com.example.appdatbanonline.model.Taikhoan;

import java.sql.Time;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient {
    @FormUrlEncoded
    @POST("login.php")
    Call<List<Taikhoan>> Logindata(@Field("sdt") String sdt,
                                   @Field("matkhau") String matkhau);
    @FormUrlEncoded
    @POST("insert.php")
    Call<String> InsertData(@Field("sdt") String sdt
                            ,@Field("matkhau") String matkhau);
    @FormUrlEncoded
    @POST("monan.php")
    Call<List<Monan>> Dsmonan(
            @Field("tenmonan") String tenmonan
            ,@Field("hinhmonan") String hinhmonan);
    @FormUrlEncoded
    @POST("nhahang.php")
    Call<List<Nhahang>> Dsnhahang(
            @Field("tennhahang") String tennhahang
            ,@Field("diachi") String diachi
            ,@Field("gia") Float gia
            ,@Field("giomocua") Time giomocua
            ,@Field("giodongcua") Time giodongcua
            ,@Field("hinhnhahang") String hinhnhahang);
    @FormUrlEncoded
    @POST("update.php")
    Call<String> Updatetk(
            @Field("id") String idtaikhoan
            ,@Field("matkhau") String matkhau);
    @FormUrlEncoded
    @POST("dsnhahang.php")
    Call<List<Nhahang>> nhahang(
            @Field("idmonan") String idmonan);
    @FormUrlEncoded
    @POST("chitietnhahang.php")
    Call<List<Chitietnhahang>> dschitietnhahang(
            @Field("idnhahang") String idnhahang);
    @FormUrlEncoded
    @POST("datban.php")
    Call<List<Datban>> datban(
            @Field("idtaikhoan") String idtaikhoan
            );
    @FormUrlEncoded
    @POST("insertdatban.php")
    Call<String> InsertDatban(@Field("idnhahang") String idnhahang
            ,@Field("idtaikhoan") String idtaikhoan
            ,@Field("ngayden") String ngayden
            ,@Field("gioden") String gioden
            ,@Field("nguoilon") String nguoilon
            ,@Field("treem") String treem
            ,@Field("ghichu") String ghichu);
    @FormUrlEncoded
    @POST("deletedatban.php")
    Call<String> deletedatban(
            @Field("iddatban") String iddatban
    );
    @FormUrlEncoded
    @POST("searchnhahang.php")
    Call<List<Nhahang>> timkiem(
            @Field("tennhahang") String tennhahang);
}
