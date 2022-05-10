package com.example.appdatbanonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdatbanonline.R;
import com.example.appdatbanonline.model.menu;

import java.util.ArrayList;

public class menuAdapter extends ArrayAdapter<menu> {
    Context context;
    public menuAdapter(Context context, int resource, ArrayList<menu> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        menu mn = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_menu, null);
            viewHolder = new ViewHolder();
            viewHolder.tvmenu = (TextView) convertView.findViewById(R.id.tvmenu);
            viewHolder.imgmenu = (ImageView) convertView.findViewById(R.id.imgmenu);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvmenu.setText(mn.getTenmenu());
        viewHolder.imgmenu.setImageResource(mn.getHinhmenu());

        return convertView;
    }
    public class ViewHolder{
        ImageView imgmenu;
        TextView tvmenu;
    }
}
