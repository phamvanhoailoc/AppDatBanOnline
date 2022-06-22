package com.example.appdatbanonline.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.appdatbanonline.Adapter.menuAdapter;
import com.example.appdatbanonline.Adapter.monanAdapter;
import com.example.appdatbanonline.Adapter.nhahangAdapter;
import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Monan;
import com.example.appdatbanonline.model.Nhahang;
import com.example.appdatbanonline.model.Taikhoan;
import com.example.appdatbanonline.model.menu;
import com.example.appdatbanonline.my_interface.clickmonan;
import com.example.appdatbanonline.my_interface.clicknhahang;
import com.example.appdatbanonline.retofit2.APIUtils;
import com.example.appdatbanonline.retofit2.DataClient;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rcv_monan,rcv_nhahang;
    ViewFlipper viewFlipper;
    ListView lvtrangchinh;
    NavigationView navigationView;
    ListView lvmanhinhchinh;
    DrawerLayout drawerLayout;
    String tenmonan ;
    String hinhmonan ;
    String tennhahang;
    String diachi;
    String hinhnhahang;
    Float gia;
    Time giomocua,giodongcua;
    monanAdapter MonanAdapter ;
    nhahangAdapter NhahangAdapter;
    ListView lvmenu;
    menuAdapter MenuAdapter;
    Monan monan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        actionbar();
        actionViewFlipper();
        getdulieumonan();
        getdulieunhahang();
        getdulieumenu();
        CatchonItemListView();
        getdateidtaikhoan();
    }

    private void timkiem(String query) {
        DataClient dataClient = APIUtils.getData();
        Call<List<Nhahang>> callback = dataClient.timkiem(query);
        callback.enqueue(new Callback<List<Nhahang>>() {
            @Override
            public void onResponse(Call<List<Nhahang>> call, Response<List<Nhahang>> response) {
                ArrayList<Nhahang> mangnhahang = (ArrayList<Nhahang>) response.body();
                if (mangnhahang.size()>0){
                    NhahangAdapter = new nhahangAdapter(mangnhahang, getApplicationContext(), new clicknhahang() {
                        @Override
                        public void onclickitemnhahang(Nhahang nhahang) {
                            onclicknhahang(nhahang);
                        }
                    });
                    rcv_nhahang.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),2);
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rcv_nhahang.setLayoutManager(linearLayoutManager);
                    rcv_nhahang.setAdapter(NhahangAdapter);
                    NhahangAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Nhahang>> call, Throwable t) {

            }
        });
    }

    private void getdateidtaikhoan() {


    }


    private void CatchonItemListView() {
        lvmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this,donhang.class);
                        Intent intent5 = getIntent();
                        ArrayList<Taikhoan> listtaikhoan = intent5.getParcelableArrayListExtra("mangtaikhoang");
                        intent1.putExtra("mangidtaikhoan",listtaikhoan);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this,yeuthich.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this,account.class);
                        Intent intent4 = getIntent();
                        ArrayList<Taikhoan> arraylisttaikhoan = intent4.getParcelableArrayListExtra("mangtaikhoang");
                        intent3.putExtra("mangaccount",arraylisttaikhoan);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });

    }

    private void getdulieumenu() {
        ArrayList<menu> menuArrayList = new ArrayList<menu>();
        menuArrayList.add(new menu(R.drawable.ic_baseline_home_24,"Trang chủ"));
        menuArrayList.add(new menu(R.drawable.ic_baseline_shopping_cart_24,"Lịch sử"));
        menuArrayList.add(new menu(R.drawable.ic_baseline_love_24,"Yêu thích"));
        menuArrayList.add(new menu(R.drawable.ic_baseline_account_circle_24,"Cá nhân"));
        MenuAdapter = new menuAdapter(this,0,menuArrayList);
        lvmenu.setAdapter(MenuAdapter);

    }

    private void getdulieunhahang() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Nhahang>> callback = dataClient.Dsnhahang(tennhahang,diachi,gia,giomocua,giodongcua,hinhnhahang);
        callback.enqueue(new Callback<List<Nhahang>>() {
            @Override
            public void onResponse(Call<List<Nhahang>> call, Response<List<Nhahang>> response) {
                ArrayList<Nhahang> nhahangs = (ArrayList<Nhahang>) response.body();
                NhahangAdapter = new nhahangAdapter(nhahangs, getApplicationContext(), new clicknhahang() {
                    @Override
                    public void onclickitemnhahang(Nhahang nhahang) {
                        onclicknhahang(nhahang);
                    }
                });
                rcv_nhahang.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),2);
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcv_nhahang.setLayoutManager(linearLayoutManager);
                rcv_nhahang.setAdapter(NhahangAdapter);
                NhahangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Nhahang>> call, Throwable t) {

            }
        });
    }

    private void getdulieumonan() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Monan>> callback = dataClient.Dsmonan(tenmonan,hinhmonan);
        callback.enqueue(new Callback<List<Monan>>() {
            @Override
            public void onResponse(Call<List<Monan>> call, Response<List<Monan>> response) {
                ArrayList<Monan> monans = (ArrayList<Monan>) response.body();
                MonanAdapter = new monanAdapter(monans, getApplicationContext(), new clickmonan() {
                    @Override
                    public void onClickitemmonan(Monan monan) {
                        onClickmonan(monan);
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcv_monan.setLayoutManager(linearLayoutManager);
                rcv_monan.setAdapter(MonanAdapter);
                MonanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Monan>> call, Throwable t) {

            }
        });
    }
    private void onClickmonan(Monan monan){
        Intent intent2 = getIntent();
        ArrayList<Taikhoan> arraylisttaikhoan = intent2.getParcelableArrayListExtra("mangtaikhoang");
        Intent intent = new Intent(this,ds_nhahang.class);
        intent.putExtra("idtaikhoan",arraylisttaikhoan);
        intent.putExtra("monan",monan);
        startActivity(intent);
    }
    private void onclicknhahang(Nhahang nhahang){
        Intent intent2 = getIntent();
        ArrayList<Taikhoan> arraylisttaikhoan = intent2.getParcelableArrayListExtra("mangtaikhoang");
        Intent intent = new Intent(this,chitietnhahang.class);
        intent.putExtra("idtaikhoan",arraylisttaikhoan);
        intent.putExtra("nhahang",nhahang);
        startActivity(intent);
    }

    private void actionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://pasgo.vn/Upload/anh-slide-show/bep-oc-sai-gon---du-mon-ngon-tu-oc-118484781578.jpg");
        mangquangcao.add("https://pasgo.vn/Upload/anh-slide-show/sushi-house---tinh-hoa-am-thuc-nhat-ban-114210241581.jpg");
        mangquangcao.add("https://pasgo.vn/Upload/anh-slide-show/pho-79---am-thuc-doc-dao-trong-khong-gian-sang-trong-167295001589.jpg");
        for(int i = 0; i<mangquangcao.size(); i++){
            ImageView imageView = new ImageView((getApplicationContext()));
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhxa() {
        toolbar = (Toolbar) findViewById(R.id.tbmhc);
        viewFlipper = (ViewFlipper) findViewById(R.id.vlp);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        rcv_monan = (RecyclerView) findViewById(R.id.rcv_monan);
        rcv_nhahang= (RecyclerView) findViewById(R.id.rcv_nhahang);
        lvmenu = (ListView) findViewById(R.id.lvmenu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                timkiem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}