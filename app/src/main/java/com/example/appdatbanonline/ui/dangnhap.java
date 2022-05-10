package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dangnhap extends AppCompatActivity {
    EditText edtsdt,edtmk;
    Button btndangnhap,btndangky;
    String taikhoan;
    String matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        anhxa();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dangnhap.this,dangky.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taikhoan = edtsdt.getText().toString();
                matkhau = edtmk.getText().toString();
                if (taikhoan.length() > 0 && matkhau.length() > 0){
                    DataClient dataClient = APIUtils.getData();
                    Call<List<Taikhoan>> callback = dataClient.Logindata(taikhoan,matkhau);
                    callback.enqueue(new Callback<List<Taikhoan>>() {
                        @Override
                        public void onResponse(Call<List<Taikhoan>> call, Response<List<Taikhoan>> response) {
                            ArrayList<Taikhoan> mangtaikhoan = (ArrayList<Taikhoan>)  response.body();
                            if (mangtaikhoan.size() > 0){
                                Intent intent = new Intent(dangnhap.this,MainActivity.class);
                                intent.putExtra("mangtaikhoang",mangtaikhoan);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Taikhoan>> call, Throwable t) {
                            Toast.makeText(dangnhap.this, "khong co tai khoan nay", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void anhxa() {
        btndangnhap = findViewById(R.id.btnlogin);
        btndangky = findViewById(R.id.btndangky);
        edtsdt = findViewById(R.id.edtusername);
        edtmk = findViewById(R.id.edtpassword);
    }
}