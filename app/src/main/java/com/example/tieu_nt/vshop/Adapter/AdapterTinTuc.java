package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.TinTuc;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.TinTuc.ChiTietTinTucActivity;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class AdapterTinTuc extends RecyclerView.Adapter<AdapterTinTuc.ViewHolder>{
    private Context context;
    private List<TinTuc> dsTinTuc;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy (hh:mm)");

    public AdapterTinTuc(Context context, List<TinTuc> dsTinTuc) {
        this.context = context;
        this.dsTinTuc = dsTinTuc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_tintuc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TinTuc tinTuc = dsTinTuc.get(position);
        holder.tvThoiGian.setText(sdf.format(tinTuc.getThoiGian()).toString());
        holder.tvTinTuc.setText(tinTuc.getTieuDe());
        holder.relaTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iChiTietTinTuc = new Intent(context, ChiTietTinTucActivity.class);
                iChiTietTinTuc.putExtra("tinTuc", tinTuc);
                context.startActivity(iChiTietTinTuc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsTinTuc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relaTinTuc;
        TextView tvThoiGian, tvTinTuc;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            relaTinTuc = (RelativeLayout) itemView.findViewById(R.id.relaTinTuc);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
            tvTinTuc = (TextView) itemView.findViewById(R.id.tvTenTinTuc);
        }
    }
}
