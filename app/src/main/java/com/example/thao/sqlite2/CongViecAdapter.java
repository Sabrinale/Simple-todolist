package com.example.thao.sqlite2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Thao on 12-Jun-17.
 */

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView txtTen;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTen= (TextView)view.findViewById(R.id.textViewTen);
            holder.imgDelete= (ImageView)view.findViewById(R.id.imageViewDelete);
            holder.imgEdit= (ImageView)view.findViewById(R.id.imageViewEdit);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final CongViec congViec = congViecList.get(i);
        holder.txtTen.setText(congViec.getTenCV());
        // bat su kien delete and edit
        holder.imgEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                context.DialigSuaCOngViec(congViec.getTenCV(), congViec.getIdCV());
                //Toast.makeText(context, "Sua " +congViec.getTenCV(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                context.DialogXoaCV(congViec.getTenCV(), congViec.getIdCV());
                //Toast.makeText(context, "Sua " +congViec.getTenCV(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}
