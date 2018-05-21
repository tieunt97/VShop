package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.TrangChu.ChiTietSanPhamActivity;
import com.squareup.picasso.Callback;
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
    private int layout;


    public AdapterSanPham(Context context, List<SanPham> dsSanPham, int layout) {
        this.context = context;
        this.dsSanPham = dsSanPham;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SanPham sanPham = dsSanPham.get(position);
        Picasso.get().load(sanPham.getHinhSanPham()).into(holder.imgHinhSP, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGiaChuan()).toString();
        holder.tvGiaSP.setText(gia + " đ");
        if(sanPham.getDanhGiaTB() == 0 && sanPham.getSoLuotDanhGia() == 0){
            holder.rbDanhGia.setRating((float) 5.0);
        }else{
            holder.rbDanhGia.setRating(sanPham.getDanhGiaTB());
        }
        holder.tvSoDanhGia.setText("(" + String.valueOf(sanPham.getSoLuotDanhGia()) + ")");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("idSanPham", sanPham.getIdSanPham());
                context.startActivity(iChiTietSanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgHinhSP;
        TextView tvTenSanPham, tvGiaSP, tvKhuyenMaiTraGop, tvSoDanhGia;
        RatingBar rbDanhGia;
        ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewSanPham);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvGiaSP = (TextView) itemView.findViewById(R.id.tvGiaSP);
            tvKhuyenMaiTraGop = (TextView) itemView.findViewById(R.id.tvKhuyenMaiTraGop);
            rbDanhGia = (RatingBar) itemView.findViewById(R.id.rbDanhGia);
            tvSoDanhGia = (TextView) itemView.findViewById(R.id.tvSoDanhGia);
        }
    }
}
