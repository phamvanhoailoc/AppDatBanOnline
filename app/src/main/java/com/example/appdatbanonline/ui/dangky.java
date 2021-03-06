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
import com.google.android.material.textfield.TextInputLayout;

import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dangky extends AppCompatActivity {
    TextInputLayout tilsdtdk,tilmatkhaudk,tilmatkhaudklai;
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
                    taikhoan = tilsdtdk.getEditText().getText().toString();
                    matkhau = tilmatkhaudk.getEditText().getText().toString();
                    matkhaureturn = tilmatkhaudklai.getEditText().getText().toString();
                        if(taikhoan.length() > 8 && matkhau.length() > 0 && matkhaureturn.length() > 0){
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
                                Toast.makeText(dangky.this,"H??y 2 m???t kh???u gi???ng nhau",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                                Toast.makeText(dangky.this,"S??? ??i???n tho???i kh??ng h???p l???",Toast.LENGTH_SHORT).show();
                        }
                }
            });
    }
    private void anhxa() {
        tilsdtdk = findViewById(R.id.tilsdtdk);
        tilmatkhaudk = findViewById(R.id.tilmatkhaudk);
        tilmatkhaudklai = findViewById(R.id.tilmatkhaudklai);
        btndangky = findViewById(R.id.btndangky);
    }
}