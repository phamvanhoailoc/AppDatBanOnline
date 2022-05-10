package com.example.appdatbanonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Monan;
import com.example.appdatbanonline.my_interface.clickmonan;
import com.example.appdatbanonline.ui.MainActivity;
import com.example.appdatbanonline.ui.ds_nhahang;
import com.squareup.picasso.Picasso;

import java.util.List;

public class monanAdapter extends RecyclerView.Adapter<monanAdapter.monanViewHolder>{
     List<Monan> mmonan;
     Context context;
    clickmonan Clickmonan;

    public monanAdapter(List<Monan> mmonan, Context context, clickmonan Clickmonan) {
        this.mmonan = mmonan;
        this.context = context;
        this.Clickmonan = Clickmonan;
    }

//    public void setMmonan(List<Monan> mmonan) {
//        this.mmonan = mmonan;
//        notifyDataSetChanged();
//    }



    @NonNull
    @Override
    public monanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.dong_mon_an,null);
        monanViewHolder vh = new monanViewHolder(itemview);
        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull monanViewHolder holder, int position) {
        Monan monan = mmonan.get(position);
        Picasso.get().load(monan.getHinhmonan())
                .into(holder.imgmonan);
        holder.tvmonan.setText(monan.getTenmonan());
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickmonan.onClickitemmonan(monan);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mmonan.size();
    }

    public class monanViewHolder extends RecyclerView.ViewHolder{
       public ImageView imgmonan;
       public TextView tvmonan;
       public LinearLayout layout_item;

        public monanViewHolder(@NonNull View itemView) {
            super(itemView);
            imgmonan = itemView.findViewById(R.id.imgmonan);
            tvmonan = itemView.findViewById(R.id.tvmonan);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }
}
