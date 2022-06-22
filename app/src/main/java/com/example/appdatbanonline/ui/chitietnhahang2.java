package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Chitietnhahang;
import com.example.appdatbanonline.model.Nhahang;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chitietnhahang2 extends AppCompatActivity {
    ImageView imgnhahang1,imgmenunhahang1,imgbando1;
    TextView tvtennhahang1,tvdiachi1,tvsdt1,tvgia1,tvgiomocua1,tvgiodongcua1,tvgioithieu1,tvluuy1;
    Button btndatban1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietnhahang2);
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
                Picasso.get().load(chitietnhahangs.get(0).getHinhanhnhahang()).into(imgnhahang1);
                Picasso.get().load(chitietnhahangs.get(0).getMenu()).into(imgmenunhahang1);
                Picasso.get().load(chitietnhahangs.get(0).getHinhbando()).into(imgbando1);
                tvtennhahang1.setText(chitietnhahangs.get(0).getTennhahang());
                tvdiachi1.setText(chitietnhahangs.get(0).getDiachi());
                tvsdt1.setText("Số điện thoại: "+chitietnhahangs.get(0).getSdt());
                tvgia1.setText("Giá chỉ từ : "+chitietnhahangs.get(0).getGia()+" VND");
                tvgiomocua1.setText("Giờ mở cửa: "+chitietnhahangs.get(0).getGiomoicua());
                tvgiodongcua1.setText("Giờ đóng cửa: "+chitietnhahangs.get(0).getGiodongcua());
                tvgioithieu1.setText(chitietnhahangs.get(0).getGioithieu());
                tvluuy1.setText(chitietnhahangs.get(0).getLuuy());
                btndatban1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent2 = getIntent();
                        Intent intent = new Intent(chitietnhahang2.this,datban1.class);
                        ArrayList<Taikhoan> taikhoans = intent2.getParcelableArrayListExtra("mangtaikhoan1");
                        intent.putExtra("mangtaikhoan2",taikhoans);
                        intent.putExtra("mangchitietnhahang1",chitietnhahangs);
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
        imgnhahang1 = (ImageView) findViewById(R.id.imgnhahang1);
        imgmenunhahang1 = (ImageView) findViewById(R.id.imgmenunhahang1);
        imgbando1 = (ImageView) findViewById(R.id.imgbando1);
        tvtennhahang1 = (TextView) findViewById(R.id.tvtennhahang1);
        tvdiachi1 = (TextView) findViewById(R.id.tvdiachi1);
        tvsdt1 = (TextView) findViewById(R.id.tvsdt1);
        tvgia1 = (TextView) findViewById(R.id.tvgia1);
        tvgiomocua1 = (TextView) findViewById(R.id.tvgiomocua1);
        tvgiodongcua1 = (TextView) findViewById(R.id.tvgiodongcua1);
        tvgioithieu1 = (TextView) findViewById(R.id.tvgioithieu1);
        tvluuy1 = (TextView) findViewById(R.id.tvluuy1);
        btndatban1 = (Button) findViewById(R.id.btndatban1);
    }
}