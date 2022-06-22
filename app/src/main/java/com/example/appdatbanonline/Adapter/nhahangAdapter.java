package com.example.appdatbanonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Nhahang;
import com.example.appdatbanonline.my_interface.clickmonan;
import com.example.appdatbanonline.my_interface.clicknhahang;
import com.example.appdatbanonline.ui.yeuthich;
import com.squareup.picasso.Picasso;

import java.util.List;

public class nhahangAdapter extends RecyclerView.Adapter<nhahangAdapter.nhahangViewHolder>{
    List<Nhahang> mnhahang;
    Context context;
    clicknhahang clicknhahang;

    public nhahangAdapter(List<Nhahang> mnhahang, Context context,clicknhahang clicknhahang) {
        this.mnhahang = mnhahang;
        this.context = context;
        this.clicknhahang = clicknhahang;
    }

    @NonNull
    @Override
    public nhahangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_nha_hang,null);
        nhahangViewHolder vh = new nhahangViewHolder(itemview);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull nhahangViewHolder holder, int position) {
        Nhahang nhahang = mnhahang.get(position);
        Picasso.get().load(nhahang.getHinhanhnhahang())
                .into(holder.imgnhahang);
        holder.tvtennhahang.setText(nhahang.getTennhahang());
        holder.tvdiachi.setText(nhahang.getDiachinhahang());
        holder.tvgia.setText("Giá: "+nhahang.getGia()+" VND");
        holder.tvgiomocua.setText("Giờ mở cửa: "+nhahang.getGiomocua());
        holder.tvgiodongcua.setText("Giờ đóng cửa: "+nhahang.getGiodongcua());
        holder.layout_itemnhahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknhahang.onclickitemnhahang(nhahang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mnhahang.size();
    }

    public class nhahangViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgnhahang,imgtim;
        public TextView tvtennhahang,tvdiachi,tvgia,tvgiomocua,tvgiodongcua;
        public LinearLayout layout_itemnhahang;
        public nhahangViewHolder(@NonNull View itemView) {
            super(itemView);
            imgnhahang = itemView.findViewById(R.id.imgnhahang);
            tvtennhahang = itemView.findViewById(R.id.tvtennhahang);
            tvdiachi = itemView.findViewById(R.id.tvdiachi);
            tvgia = itemView.findViewById(R.id.tvgia);
            tvgiomocua = itemView.findViewById(R.id.tvgiomocua);
            tvgiodongcua = itemView.findViewById(R.id.tvgiodongcua);
            layout_itemnhahang = itemView.findViewById(R.id.layout_itemnhahang);
            imgtim = itemView.findViewById(R.id.imgtim);
            imgtim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgtim.setImageResource(R.drawable.ic_baseline_favorite_24);
                    Toast.makeText(context,"Thich",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
