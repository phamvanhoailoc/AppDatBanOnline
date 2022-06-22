package com.example.appdatbanonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Chitietnhahang;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class datban extends AppCompatActivity {
    EditText edtngay,edtgio,edtghichu;
    Button btndatbanngay,btngln,btnslnl,btntnl,btngte,btnslte,btntte;
    String ngay,gio,nguoilon,treem,ghichu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datban);
        anhxa();
        getslnguoilon();
        getsltreem();
        getonclickdate();
        getonclicktime();
        init();
    }
    public void init() {
        Intent intent = getIntent();
        ArrayList<Chitietnhahang> chitietnhahangs = intent.getParcelableArrayListExtra("mangchitietnhahang");
        String idnhahang = chitietnhahangs.get(0).getIdnhahang();
        Intent intent2 = getIntent();
        ArrayList<Taikhoan> taikhoans = intent2.getParcelableArrayListExtra("mangtaikhoan");
        String idtaikhoan = taikhoans.get(0).getId();
        btndatbanngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngay = edtngay.getText().toString();
                gio = edtgio.getText().toString();
                nguoilon = btnslnl.getText().toString();
                treem = btnslte.getText().toString();
                ghichu = edtghichu.getText().toString();
                if (ngay.length() > 0 && gio.length() > 0){
                    DataClient insertdata = APIUtils.getData();
                    retrofit2.Call<String> callback = insertdata.InsertDatban(idnhahang,idtaikhoan,ngay,gio,nguoilon,treem,ghichu);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("secces")){
                                Toast.makeText(datban.this,"Đặt bàn thành công",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(datban.this,"Hãy nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    public void getonclicktime() {
        edtgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int gio = calendar.get(Calendar.HOUR_OF_DAY);
                int phut = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(datban.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(0,0,0,i,i1);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        edtgio.setText(simpleDateFormat.format(calendar.getTime()));
                        String time = edtgio.getText().toString();
                    }
                },gio,phut,true);
                timePickerDialog.show();
            }
        });
    }

    public void getonclickdate() {
        edtngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(datban.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        edtngay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }

    public void getsltreem() {
        int sl = Integer.parseInt(btnslte.getText().toString()) ;
        if (sl >= 10){
            btntte.setVisibility(View.INVISIBLE);
            btngte.setVisibility(View.VISIBLE);
        }else if (sl < 1){
            btntte.setVisibility(View.VISIBLE);
            btngte.setVisibility(View.INVISIBLE);
        }else {
            btntte.setVisibility(View.VISIBLE);
            btngte.setVisibility(View.VISIBLE);
        }
        btntte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslte.getText().toString())+1;
                if (slmoinhat > 8){
                    btntte.setVisibility(View.INVISIBLE);
                    btngte.setVisibility(View.VISIBLE);
                    btnslte.setText(String.valueOf(slmoinhat));
                }else {
                    btntte.setVisibility(View.VISIBLE);
                    btngte.setVisibility(View.VISIBLE);
                    btnslte.setText(String.valueOf(slmoinhat));
                }
            }
        });
        btngte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslte.getText().toString())-1;
                if (slmoinhat < 1){
                    btntte.setVisibility(View.VISIBLE);
                    btngte.setVisibility(View.INVISIBLE);
                    btnslte.setText(String.valueOf(slmoinhat));
                }else {
                    btntte.setVisibility(View.VISIBLE);
                    btngte.setVisibility(View.VISIBLE);
                    btnslte.setText(String.valueOf(slmoinhat));
                }
            }
        });
    }

    public void getslnguoilon() {
        int sl = Integer.parseInt(btnslnl.getText().toString()) ;
        if (sl >= 10){
            btntnl.setVisibility(View.INVISIBLE);
            btngln.setVisibility(View.VISIBLE);
        }else if(sl <= 2){
            btntnl.setVisibility(View.VISIBLE);
            btngln.setVisibility(View.INVISIBLE);
        }else {
            btntnl.setVisibility(View.VISIBLE);
            btngln.setVisibility(View.VISIBLE);
        }
        btntnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslnl.getText().toString())+1;
                if (slmoinhat > 8){
                    btntnl.setVisibility(View.INVISIBLE);
                    btngln.setVisibility(View.VISIBLE);
                    btnslnl.setText(String.valueOf(slmoinhat));
                }else {
                    btntnl.setVisibility(View.VISIBLE);
                    btngln.setVisibility(View.VISIBLE);
                    btnslnl.setText(String.valueOf(slmoinhat));
                }
            }
        });
        btngln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslnl.getText().toString())-1;
                if (slmoinhat < 2){
                    btntnl.setVisibility(View.VISIBLE);
                    btngln.setVisibility(View.INVISIBLE);
                    btnslnl.setText(String.valueOf(slmoinhat));
                    btnslnl.getText().toString();
                }else {
                    btntnl.setVisibility(View.VISIBLE);
                    btngln.setVisibility(View.VISIBLE);
                    btnslnl.setText(String.valueOf(slmoinhat));
                    btnslnl.getText().toString();
                }
            }
        });
    }

    private void anhxa() {
        edtngay = (EditText) findViewById(R.id.edtngay);
        edtgio = (EditText) findViewById(R.id.edtgio);
        edtghichu = (EditText) findViewById(R.id.edtghichu);
        btndatbanngay = (Button) findViewById(R.id.btndatbanngay);
        btngln = (Button) findViewById(R.id.btngln);
        btnslnl = (Button) findViewById(R.id.btnslnl);
        btntnl = (Button) findViewById(R.id.btntnl);
        btngte = (Button) findViewById(R.id.btngte);
        btnslte = (Button) findViewById(R.id.btnslte);
        btntte = (Button) findViewById(R.id.btntte);

    }
}