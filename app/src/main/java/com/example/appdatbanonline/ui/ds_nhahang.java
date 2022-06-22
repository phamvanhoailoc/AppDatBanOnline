package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdatbanonline.Adapter.nhahangAdapter;
import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Monan;
import com.example.appdatbanonline.model.Nhahang;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.my_interface.clicknhahang;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ds_nhahang extends AppCompatActivity {
    nhahangAdapter NhahangAdapter;
    RecyclerView rcvdsnhahang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_nhahang);
        anhxa();
        Intent intent = getIntent();
        Monan monan = intent.getParcelableExtra("monan");
        getdatanhahang(monan.getIdmonan());

    }



    private void getdatanhahang(String idmonan){
        DataClient dataClient = APIUtils.getData();
        Call<List<Nhahang>> callback = dataClient.nhahang(idmonan);
        callback.enqueue(new Callback<List<Nhahang>>() {
            @Override
            public void onResponse(Call<List<Nhahang>> call, Response<List<Nhahang>> response) {
                ArrayList<Nhahang> nhahangs = (ArrayList<Nhahang>) response.body();
                NhahangAdapter = new nhahangAdapter(nhahangs, getApplicationContext(), new clicknhahang() {
                    @Override
                    public void onclickitemnhahang(Nhahang nhahang) {
                        onclicknhahang(nhahang);
                    }
                });
                rcvdsnhahang.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),1);
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcvdsnhahang.setLayoutManager(linearLayoutManager);
                rcvdsnhahang.setAdapter(NhahangAdapter);
                NhahangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Nhahang>> call, Throwable t) {

            }
        });
    }
    private void onclicknhahang(Nhahang nhahang){
        Intent intent2 = getIntent();
        ArrayList<Taikhoan> taikhoans = intent2.getParcelableArrayListExtra("idtaikhoan");
        Intent intent = new Intent(this,chitietnhahang2.class);
        intent.putExtra("mangtaikhoan1",taikhoans);
        intent.putExtra("nhahang",nhahang);
        startActivity(intent);
    }
    private void anhxa(){
        rcvdsnhahang = (RecyclerView) findViewById(R.id.rcv_dsnhahang);
    }
}