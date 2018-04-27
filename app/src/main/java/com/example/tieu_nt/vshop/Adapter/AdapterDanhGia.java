package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.R;

import java.util.List;

/**
 * Created by tieu_nt on 4/20/2018.
 */

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolder>{
    private Context context;
    private List<DanhGia> dsDanhGia;


    public AdapterDanhGia(Context context, List<DanhGia> dsDanhGia) {
        this.context = context;
        this.dsDanhGia = dsDanhGia;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_danhgiasanpham, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DanhGia danhGia = dsDanhGia.get(position);
        holder.tvTieuDe.setText(danhGia.getTieuDe());
        holder.tvTenKhachHang.setText(danhGia.getTenKhachHang());
        holder.tvThoiGian.setText(danhGia.getThoiGian());
        holder.tvNoiDung.setText(danhGia.getNoiDung());
        holder.tvSoLuotThich.setText(String.valueOf(danhGia.getSoLuotThich()));
        holder.rbDanhGia.setRating(danhGia.getSoSao());
    }

    @Override
    public int getItemCount() {
        return dsDanhGia.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTieuDe, tvTenKhachHang, tvThoiGian, tvNoiDung, tvSoLuotThich;
        RatingBar rbDanhGia;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTieuDe = (TextView) itemView.findViewById(R.id.tvTieuDe);
            tvTenKhachHang = (TextView) itemView.findViewById(R.id.tvTenKhachHang);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
            tvNoiDung = (TextView) itemView.findViewById(R.id.tvNoiDung);
            tvSoLuotThich = (TextView) itemView.findViewById(R.id.tvSoLuotThich);
            rbDanhGia = (RatingBar) itemView.findViewById(R.id.rbDanhGia);
        }
    }
}
