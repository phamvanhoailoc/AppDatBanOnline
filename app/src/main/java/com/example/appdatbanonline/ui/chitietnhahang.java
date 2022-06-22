package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdatbanonline.Adapter.dsnhahangAdapter;
import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Chitietnhahang;
import com.example.appdatbanonline.model.Monan;
import com.example.appdatbanonline.model.Nhahang;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chitietnhahang extends AppCompatActivity {
    ImageView imgnhahang,imgmenunhahang,imgbando;
    TextView tvtennhahang,tvdiachi,tvsdt,tvgia,tvgiomocua,tvgiodongcua,tvgioithieu,tvluuy;
    Button btndatban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietnhahang);
        anhxa();
        Intent intent = getIntent();
        Nhahang nhahang = intent.getParcelableExtra("nhahang");
        getdatanhahang(nhahang.getIdnhahang());
    }
    private void getdatanhahang(String idnhahang) {
        DataClient dataClient = APIUtils.getData();
        Call<List<Chitietnhahang>> callback = dataClient.dschitietnhahang(idnhahang);
        callback.enqueue(new Callback<List<Chitietnhahang>>() {
            @Override
            public void onResponse(Call<List<Chitietnhahang>> call, Response<List<Chitietnhahang>> response) {
                ArrayList<Chitietnhahang> chitietnhahangs = (ArrayList<Chitietnhahang>) response.body();
                Picasso.get().load(chitietnhahangs.get(0).getHinhanhnhahang()).into(imgnhahang);
                Picasso.get().load(chitietnhahangs.get(0).getMenu()).into(imgmenunhahang);
                Picasso.get().load(chitietnhahangs.get(0).getHinhbando()).into(imgbando);
                tvtennhahang.setText(chitietnhahangs.get(0).getTennhahang());
                tvdiachi.setText(chitietnhahangs.get(0).getDiachi());
                tvsdt.setText("Số điện thoại: "+chitietnhahangs.get(0).getSdt());
                tvgia.setText("Giá chỉ từ : "+chitietnhahangs.get(0).getGia()+" VND");
                tvgiomocua.setText("Giờ mở cửa: "+chitietnhahangs.get(0).getGiomoicua());
                tvgiodongcua.setText("Giờ đóng cửa: "+chitietnhahangs.get(0).getGiodongcua());
                tvgioithieu.setText(chitietnhahangs.get(0).getGioithieu());
                tvluuy.setText(chitietnhahangs.get(0).getLuuy());
                btndatban.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent2 = getIntent();
                        Intent intent = new Intent(chitietnhahang.this,datban.class);
                        ArrayList<Taikhoan> taikhoans = intent2.getParcelableArrayListExtra("idtaikhoan");
                        intent.putExtra("mangtaikhoan",taikhoans);
                        intent.putExtra("mangchitietnhahang",chitietnhahangs);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Chitietnhahang>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        imgnhahang = (ImageView) findViewById(R.id.imgnhahang);
        imgmenunhahang = (ImageView) findViewById(R.id.imgmenunhahang);
        imgbando = (ImageView) findViewById(R.id.imgbando);
        tvtennhahang = (TextView) findViewById(R.id.tvtennhahang);
        tvdiachi = (TextView) findViewById(R.id.tvdiachi);
        tvsdt = (TextView) findViewById(R.id.tvsdt);
        tvgia = (TextView) findViewById(R.id.tvgia);
        tvgiomocua = (TextView) findViewById(R.id.tvgiomocua);
        tvgiodongcua = (TextView) findViewById(R.id.tvgiodongcua);
        tvgioithieu = (TextView) findViewById(R.id.tvgioithieu);
        tvluuy = (TextView) findViewById(R.id.tvluuy);
        btndatban = (Button) findViewById(R.id.btndatban);
    }
}