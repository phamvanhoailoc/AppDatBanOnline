package com.example.appdatbanonline.retofit2;

public class APIUtils {
    public static final String Base_Url = "https://phamvanhoailoc.000webhostapp.com/Server/";
    public static DataClient getData(){
        return Retofitclient.getClient(Base_Url).create(DataClient.class);
    }
}
