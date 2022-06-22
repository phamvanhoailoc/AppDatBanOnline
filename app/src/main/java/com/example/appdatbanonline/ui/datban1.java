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

public class datban1 extends AppCompatActivity {
    EditText edtngay1,edtgio1,edtghichu1;
    Button btndatbanngay1,btngln1,btnslnl1,btntnl1,btngte1,btnslte1,btntte1;
    String ngay1,gio1,nguoilon1,treem1,ghichu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datban1);
        anhxa();
        getslnguoilon();
        getsltreem();
        getonclickdate();
        getonclicktime();
        init();
    }
    public void init() {
        Intent intent = getIntent();
        ArrayList<Chitietnhahang> chitietnhahangs = intent.getParcelableArrayListExtra("mangchitietnhahang1");
        String idnhahang = chitietnhahangs.get(0).getIdnhahang();
        Intent intent2 = getIntent();
        ArrayList<Taikhoan> taikhoans = intent2.getParcelableArrayListExtra("mangtaikhoan2");
        String idtaikhoan = taikhoans.get(0).getId();
        btndatbanngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngay1 = edtngay1.getText().toString();
                gio1 = edtgio1.getText().toString();
                nguoilon1 = btnslnl1.getText().toString();
                treem1 = btnslte1.getText().toString();
                ghichu1 = edtghichu1.getText().toString();
                if (ngay1.length() > 0 && gio1.length() > 0){
                    DataClient insertdata = APIUtils.getData();
                    retrofit2.Call<String> callback = insertdata.InsertDatban(idnhahang,idtaikhoan,ngay1,gio1,nguoilon1,treem1,ghichu1);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("secces")){
                                Toast.makeText(datban1.this,"Đặt bàn thành công",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(datban1.this,"Hãy nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    public void getonclicktime() {
        edtgio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int gio = calendar.get(Calendar.HOUR_OF_DAY);
                int phut = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(datban1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(0,0,0,i,i1);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        edtgio1.setText(simpleDateFormat.format(calendar.getTime()));
                        String time = edtgio1.getText().toString();
                    }
                },gio,phut,true);
                timePickerDialog.show();
            }
        });
    }

    public void getonclickdate() {
        edtngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(datban1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        edtngay1.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }

    public void getsltreem() {
        int sl = Integer.parseInt(btnslte1.getText().toString()) ;
        if (sl >= 10){
            btntte1.setVisibility(View.INVISIBLE);
            btngte1.setVisibility(View.VISIBLE);
        }else if (sl < 1){
            btntte1.setVisibility(View.VISIBLE);
            btngte1.setVisibility(View.INVISIBLE);
        }else {
            btntte1.setVisibility(View.VISIBLE);
            btngte1.setVisibility(View.VISIBLE);
        }
        btntte1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslte1.getText().toString())+1;
                if (slmoinhat > 8){
                    btntte1.setVisibility(View.INVISIBLE);
                    btngte1.setVisibility(View.VISIBLE);
                    btnslte1.setText(String.valueOf(slmoinhat));
                }else {
                    btntte1.setVisibility(View.VISIBLE);
                    btngte1.setVisibility(View.VISIBLE);
                    btnslte1.setText(String.valueOf(slmoinhat));
                }
            }
        });
        btngte1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslte1.getText().toString())-1;
                if (slmoinhat < 1){
                    btntte1.setVisibility(View.VISIBLE);
                    btngte1.setVisibility(View.INVISIBLE);
                    btnslte1.setText(String.valueOf(slmoinhat));
                }else {
                    btntte1.setVisibility(View.VISIBLE);
                    btngte1.setVisibility(View.VISIBLE);
                    btnslte1.setText(String.valueOf(slmoinhat));
                }
            }
        });
    }

    public void getslnguoilon() {
        int sl = Integer.parseInt(btnslnl1.getText().toString()) ;
        if (sl >= 10){
            btntnl1.setVisibility(View.INVISIBLE);
            btngln1.setVisibility(View.VISIBLE);
        }else if(sl <= 2){
            btntnl1.setVisibility(View.VISIBLE);
            btngln1.setVisibility(View.INVISIBLE);
        }else {
            btntnl1.setVisibility(View.VISIBLE);
            btngln1.setVisibility(View.VISIBLE);
        }
        btntnl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslnl1.getText().toString())+1;
                if (slmoinhat > 8){
                    btntnl1.setVisibility(View.INVISIBLE);
                    btngln1.setVisibility(View.VISIBLE);
                    btnslnl1.setText(String.valueOf(slmoinhat));
                }else {
                    btntnl1.setVisibility(View.VISIBLE);
                    btngln1.setVisibility(View.VISIBLE);
                    btnslnl1.setText(String.valueOf(slmoinhat));
                }
            }
        });
        btngln1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnslnl1.getText().toString())-1;
                if (slmoinhat < 2){
                    btntnl1.setVisibility(View.VISIBLE);
                    btngln1.setVisibility(View.INVISIBLE);
                    btnslnl1.setText(String.valueOf(slmoinhat));
                    btnslnl1.getText().toString();
                }else {
                    btntnl1.setVisibility(View.VISIBLE);
                    btngln1.setVisibility(View.VISIBLE);
                    btnslnl1.setText(String.valueOf(slmoinhat));
                    btnslnl1.getText().toString();
                }
            }
        });
    }

    private void anhxa() {
        edtngay1 = (EditText) findViewById(R.id.edtngay1);
        edtgio1 = (EditText) findViewById(R.id.edtgio1);
        edtghichu1 = (EditText) findViewById(R.id.edtghichu1);
        btndatbanngay1 = (Button) findViewById(R.id.btndatbanngay1);
        btngln1 = (Button) findViewById(R.id.btngln1);
        btnslnl1 = (Button) findViewById(R.id.btnslnl1);
        btntnl1 = (Button) findViewById(R.id.btntnl1);
        btngte1 = (Button) findViewById(R.id.btngte1);
        btnslte1 = (Button) findViewById(R.id.btnslte1);
        btntte1 = (Button) findViewById(R.id.btntte1);

    }
}