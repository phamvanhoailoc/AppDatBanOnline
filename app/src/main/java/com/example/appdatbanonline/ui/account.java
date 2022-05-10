package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Nhahang;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class account extends AppCompatActivity {
    Button btndangxuat,btnupdate;
    EditText edttaikhoan,edtmatkhau;
    String matkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        anhxa();
        Init();
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(account.this, dangnhap.class);
                startActivity(intent);
            }
        });
    }

    public void Init() {
        Intent intent = getIntent();
        ArrayList<Taikhoan> taikhoanArrayList = intent.getParcelableArrayListExtra("mangaccount");
        edttaikhoan.setText(taikhoanArrayList.get(0).getSdt());
        edtmatkhau.setText(taikhoanArrayList.get(0).getPassword());
        getdatataikhoan(taikhoanArrayList.get(0).getId());
    }

    private void getdatataikhoan(String id) {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matkhau = edtmatkhau.getText().toString();
                if (matkhau.length() > 0){
                    DataClient updata = APIUtils.getData();
                    retrofit2.Call<String> callback = updata.Updatetk(id,matkhau);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("secces")){
                                Toast.makeText(account.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }


    private void anhxa() {
        btndangxuat = (Button) findViewById(R.id.btndangxuat);
        edttaikhoan = (EditText) findViewById(R.id.edttaikhoan);
        edtmatkhau = (EditText) findViewById(R.id.edtmatkhau);
        btnupdate = (Button) findViewById(R.id.btnupdate);
    }
}