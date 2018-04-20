package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by tieu_nt on 4/19/2018.
 */

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHolder>{
    private Context context;
    private List<SanPham> dsSanPham;


    public AdapterSanPham(Context context, List<SanPham> dsSanPham) {
        this.context = context;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_sanpham, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SanPham sanPham = dsSanPham.get(position);
        Picasso.get().load(sanPham.getHinhSanPham()).into(holder.imgHinhSP);
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGiaChuan()).toString();
        holder.tvGiaSP.setText(gia + " đ");
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        TextView tvTenSanPham, tvGiaSP, tvKhuyenMaiTraGop;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvGiaSP = (TextView) itemView.findViewById(R.id.tvGiaSP);
            tvKhuyenMaiTraGop = (TextView) itemView.findViewById(R.id.tvKhuyenMaiTraGop);
        }
    }
}
