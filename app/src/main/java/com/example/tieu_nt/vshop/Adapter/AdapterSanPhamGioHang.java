package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class AdapterSanPhamGioHang extends RecyclerView.Adapter<AdapterSanPhamGioHang.ViewHolder>{
    private Context context;
    private List<SanPham> dsSanPham;


    public AdapterSanPhamGioHang(Context context, List<SanPham> dsSanPham) {
        this.context = context;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_item_giohang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SanPham sanPham = dsSanPham.get(position);
        Picasso.get().load(sanPham.getHinhSanPham()).into(holder.imgHinhSP);
        holder.tvTenSP.setText(sanPham.getTenSanPham());
        holder.tvGiaSP.setText(sanPham.getGiaChuan());
        holder.tvGiaSP.setText("");
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        TextView tvTenSP, tvGiaSP, tvGiamGia, tvSoSP;
        ImageButton imgXoa, imgCong, imgTru;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            tvTenSP = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvGiaSP = (TextView) itemView.findViewById(R.id.tvGiaSP);
            tvGiamGia = (TextView) itemView.findViewById(R.id.tvGiamGia);
            tvSoSP = (TextView) itemView.findViewById(R.id.tvSoSanPham);
            imgXoa = (ImageButton) itemView.findViewById(R.id.imgXoa);
            imgCong = (ImageButton) itemView.findViewById(R.id.imgCong);
            imgTru = (ImageButton) itemView.findViewById(R.id.imgTru);
        }
    }
}
