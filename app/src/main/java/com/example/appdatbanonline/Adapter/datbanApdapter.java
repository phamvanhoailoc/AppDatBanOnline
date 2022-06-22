package com.example.appdatbanonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.Datban;
import com.example.appdatbanonline.ui.donhang;

import java.util.ArrayList;

public class datbanApdapter extends BaseAdapter {
    ArrayList<Datban> datbanArrayList;
    donhang context;

    public datbanApdapter(ArrayList<Datban> datbanArrayList, donhang context) {
        this.datbanArrayList = datbanArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datbanArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return datbanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView tvtennhahang,tvsdt,tvngayden,tvgioden,tvnguoilon,tvtreem;
        ImageView imgdelete;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dong_dat_ban,null);
        viewHolder.tvtennhahang = view.findViewById(R.id.tvtennhahang);
        viewHolder.tvsdt = view.findViewById(R.id.tvsdt);
        viewHolder.tvngayden = view.findViewById(R.id.tvngayden);
        viewHolder.tvgioden = view.findViewById(R.id.tvgioden);
        viewHolder.tvnguoilon = view.findViewById(R.id.tvnguoilon);
        viewHolder.tvtreem = view.findViewById(R.id.tvtreem);
        viewHolder.imgdelete = view.findViewById(R.id.imgdelete);
        view.setTag(viewHolder);
        viewHolder = (ViewHolder) view.getTag();
        Datban datban = (Datban) getItem(i);
        viewHolder.tvtennhahang.setText("Tên nhà hàng: "+datban.getTennhahang());
        viewHolder.tvsdt.setText("Số điện thoại: "+datban.getSdt());
        viewHolder.tvngayden.setText("Ngày đến: "+datban.getNgayden());
        viewHolder.tvgioden.setText("Giờ đến: "+datban.getGioden());
        viewHolder.tvnguoilon.setText("Số lượng người lớn: "+datban.getNguoilon());
        viewHolder.tvtreem.setText("Số lượng trẻ em: "+datban.getTreem());
        viewHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.dialogdelete(datban.getIddatban());
            }
        });
        return view;
    }
}
