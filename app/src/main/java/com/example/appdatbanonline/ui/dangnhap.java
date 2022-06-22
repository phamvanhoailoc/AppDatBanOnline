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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dangnhap extends AppCompatActivity {
    Button btndangnhap,btndangky;
    String taikhoan;
    String matkhau;
    TextInputLayout tilmatkhau,tilsdt;

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
                taikhoan = tilsdt.getEditText().getText().toString();
                matkhau = tilmatkhau.getEditText().getText().toString();
                if (taikhoan.length() > 8 && matkhau.length() > 0){
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
                            Toast.makeText(dangnhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(dangnhap.this, "Số điện thoại không phù hợp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void anhxa() {
        btndangnhap = findViewById(R.id.btnlogin);
        btndangky = findViewById(R.id.btndangky);
        tilsdt = findViewById(R.id.tilsdt);
        tilmatkhau = findViewById(R.id.edtpassword);
    }
}