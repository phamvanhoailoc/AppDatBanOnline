package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdatbanonline.Adapter.datbanApdapter;
import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Datban;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class donhang extends AppCompatActivity {
    TextView tvthongbaorong;
    ListView lvphieudatcho;
    Button btnxoa,btncapnhat;
    datbanApdapter DatbanApdapter;
    ImageView imgdelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang);
        anhxa();
        innit();
    }
    private void innit() {
        Intent intent = getIntent();
        ArrayList<Taikhoan> taikhoanArrayList = intent.getParcelableArrayListExtra("mangidtaikhoan");
        getdatataikhoan(taikhoanArrayList.get(0).getId());
    }

    public void getdatataikhoan(String id) {
        DataClient dataClient = APIUtils.getData();
        Call<List<Datban>> callback = dataClient.datban(id);
        callback.enqueue(new Callback<List<Datban>>() {
            @Override
            public void onResponse(Call<List<Datban>> call, Response<List<Datban>> response) {
                ArrayList<Datban> datbans = (ArrayList<Datban>) response.body();
                DatbanApdapter = new datbanApdapter(datbans,donhang.this);
                lvphieudatcho.setAdapter(DatbanApdapter);
                if (datbans.size() <= 0){
                    tvthongbaorong.setVisibility(View.VISIBLE);
                    lvphieudatcho.setVisibility(View.INVISIBLE);
                    DatbanApdapter.notifyDataSetChanged();
                }else {
                    tvthongbaorong.setVisibility(View.INVISIBLE);
                    lvphieudatcho.setVisibility(View.VISIBLE);
                    DatbanApdapter.notifyDataSetChanged();
                }
                DatbanApdapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<List<Datban>> call, Throwable t) {

            }
        });
    }
    public void dialogdelete(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có đồng ý xóa không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataClient dataClient1 = APIUtils.getData();
                Call<String> callbacks = dataClient1.deletedatban(id);
                callbacks.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String resual = response.body();
                        if (resual.equals("ok")){
                            Toast.makeText(donhang.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    private void anhxa(){
        tvthongbaorong = (TextView) findViewById(R.id.tvthongbaorong);
        lvphieudatcho = (ListView) findViewById(R.id.lvphieudatcho);
//        btnxoa = (Button) findViewById(R.id.btnxoa);
        imgdelete = (ImageView) findViewById(R.id.imgdelete);
//        btncapnhat = (Button) findViewById(R.id.btncapnhat);
    }
}