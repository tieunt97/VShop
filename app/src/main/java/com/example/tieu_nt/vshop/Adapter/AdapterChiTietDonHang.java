package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadHinhSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterChiTietDonHang extends RecyclerView.Adapter<AdapterChiTietDonHang.ViewHolder> {
    private Context context;
    private List<SanPham> dsSanPham;
    private NumberFormat numberFormat = new DecimalFormat("###,###");

    public AdapterChiTietDonHang(Context context, List<SanPham>dsSanPham){
        this.context = context;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_item_giohang, parent, false);
        AdapterChiTietDonHang.ViewHolder viewHolder = new AdapterChiTietDonHang.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SanPham sanPham = dsSanPham.get(position);
        holder.tvTenSP.setText(sanPham.getTenSanPham());
        holder.tvGiaSP.setText(numberFormat.format(sanPham.getGiaChuan()).toString() + " đ");
//        byte[] hinh = sanPham.getHinhSPGioHang();
//        holder.imgHinhSP.setImageBitmap(BitmapFactory.decodeByteArray(hinh, 0, hinh.length));

        Log.d("Image", sanPham.getHinhSanPham());
        Picasso.get().load(sanPham.getHinhSanPham()).into(holder.imgHinhSP);
        holder.tvSoSP.setText(String.valueOf(sanPham.getSoLuong()));

        holder.imgXoa.setVisibility(View.GONE);
        holder.imgTru.setVisibility(View.GONE);
        holder.imgCong.setVisibility(View.GONE);
        holder.tvSoLuong.setVisibility(View.VISIBLE);
//        holder.tvSoLuong.setText("Số lượng mua: ");
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        TextView tvTenSP, tvGiaSP, tvGiamGia, tvSoSP, tvSoLuong;
        ImageButton imgXoa, imgCong, imgTru;
        LinearLayout linearGioHang, linearChiTiet;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            tvTenSP = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvGiaSP = (TextView) itemView.findViewById(R.id.tvGiaSP);
            tvGiamGia = (TextView) itemView.findViewById(R.id.tvGiamGia);
            tvSoSP = (TextView) itemView.findViewById(R.id.tvSoSanPham);
            tvSoLuong = (TextView) itemView.findViewById(R.id.tvSoLuong);
            imgXoa = (ImageButton) itemView.findViewById(R.id.imgXoa);
            imgCong = (ImageButton) itemView.findViewById(R.id.imgCong);
            imgTru = (ImageButton) itemView.findViewById(R.id.imgTru);
            linearGioHang = (LinearLayout) itemView.findViewById(R.id.linearGioHang);
            linearChiTiet = (LinearLayout) itemView.findViewById(R.id.linearChiTiet);
        }
    }



}
