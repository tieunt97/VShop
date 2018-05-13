package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.vshop.R;

import java.util.ArrayList;

public class AdapterDonHang extends RecyclerView.Adapter<AdapterDonHang.ViewHolder>{
    private Context context;
    private ArrayList<String> listDH;

    public AdapterDonHang(Context context, ArrayList<String> listDH) {
        this.context = context;
        this.listDH = listDH;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_donhang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listDH.size();
    }

}
