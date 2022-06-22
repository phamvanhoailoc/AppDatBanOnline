package com.example.appdatbanonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Nhahang;
import com.squareup.picasso.Picasso;

import java.util.List;

public class dsnhahangAdapter  extends RecyclerView.Adapter<dsnhahangAdapter.dsnhahangViewHolder>{
    List<Nhahang> mnhahang;
    Context context;

    public dsnhahangAdapter(List<Nhahang> mnhahang, Context context) {
        this.mnhahang = mnhahang;
        this.context = context;
    }
    @NonNull
    @Override
    public dsnhahangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_nha_hang,null);
        dsnhahangViewHolder vh = new dsnhahangViewHolder(itemview);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull dsnhahangViewHolder holder, int position) {
        Nhahang nhahang = mnhahang.get(position);
        Picasso.get().load(nhahang.getHinhanhnhahang())
                .into(holder.imgnhahang);
        holder.tvtennhahang.setText(nhahang.getTennhahang());
        holder.tvdiachi.setText(nhahang.getDiachinhahang());
        holder.tvgia.setText("Giá: "+nhahang.getGia()+" VND");
        holder.tvgiomocua.setText("Giờ mở cửa: "+nhahang.getGiomocua());
        holder.tvgiodongcua.setText("Giờ đóng cửa: "+nhahang.getGiodongcua());
    }

    @Override
    public int getItemCount() {
        return mnhahang.size();
    }
    public class dsnhahangViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgnhahang;
        public TextView tvtennhahang,tvdiachi,tvgia,tvgiomocua,tvgiodongcua;
        public Button btndatbannow;
        public dsnhahangViewHolder(@NonNull View itemView) {
            super(itemView);
            imgnhahang = itemView.findViewById(R.id.imgnhahang);
            tvtennhahang = itemView.findViewById(R.id.tvtennhahang);
            tvdiachi = itemView.findViewById(R.id.tvdiachi);
            tvgia = itemView.findViewById(R.id.tvgia);
            tvgiomocua = itemView.findViewById(R.id.tvgiomocua);
            tvgiodongcua = itemView.findViewById(R.id.tvgiodongcua);
            btndatbannow = itemView.findViewById(R.id.btndatbannow);
        }
    }
}
