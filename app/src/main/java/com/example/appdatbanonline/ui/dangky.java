package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;

import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dangky extends AppCompatActivity {
    EditText edttaikhoan,edtmatkhau,edtmatkhauruturn;
    Button btndangky;
    String taikhoan;
    String matkhau;
    String matkhaureturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        anhxa();

            btndangky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taikhoan = edttaikhoan.getText().toString();
                    matkhau = edtmatkhauruturn.getText().toString();
                    matkhaureturn = edtmatkhau.getText().toString();
                        if(taikhoan.length() > 0 && matkhau.length() > 0 && matkhaureturn.length() > 0){
                            if (matkhau.equals(matkhaureturn)){
                                DataClient insertdata = APIUtils.getData();
                                retrofit2.Call<String> callback = insertdata.InsertData(taikhoan,matkhau);
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        String result = response.body();
                                        if (result.equals("secces")){
                                            Toast.makeText(dangky.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                            }else {
                                Toast.makeText(dangky.this,"hay nhap mat khau trung nhau",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                                Toast.makeText(dangky.this,"hay nhap du thong tin",Toast.LENGTH_SHORT).show();
                        }
                }
            });
    }
    private void anhxa() {
        edttaikhoan = findViewById(R.id.edtusername);
        edtmatkhau = findViewById(R.id.edtpassword);
        edtmatkhauruturn = findViewById(R.id.edtreturnpassword);
        btndangky = findViewById(R.id.btndangky);
    }
}