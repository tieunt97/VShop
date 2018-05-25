package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Presenter.Shiper.PresenterDonHangShiper;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.ChiTietDonHangActivity;
import com.example.tieu_nt.vshop.View.Shiper.ChiTietSanPhamActivity;
import com.example.tieu_nt.vshop.View.Shiper.ShiperActivity;

import java.util.ArrayList;

public class AdapterNhanDonHang extends RecyclerView.Adapter<AdapterNhanDonHang.ViewHolder> {
    private Context context;
    private ArrayList<DonHang> listDH;
    private PresenterDonHangShiper presenterDonHangShiper;
    private int role = -1;

    public AdapterNhanDonHang(Context context, ArrayList<DonHang> listDH, PresenterDonHangShiper presenterDonHangShiper, int role) {
        this.context = context;
        this.listDH = listDH;
        this.presenterDonHangShiper = presenterDonHangShiper;
        this.role = role;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvTenKhachHang;
        TextView tvSoDienThoai;
        TextView tvDiaChi;
        TextView tvTenSanPham;
        TextView tvPhiShip;
        TextView tvTongTien;
        Button btnXemThongTin;
        Button btnNhanDonHang;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTenKhachHang = itemView.findViewById(R.id.tvTenKhachHang);
            tvSoDienThoai = itemView.findViewById(R.id.tvSoDienThoai);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            cardView = itemView.findViewById(R.id.cardView);
            tvPhiShip = itemView.findViewById(R.id.tvPhiShip);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            btnNhanDonHang = itemView.findViewById(R.id.btnNhanDonHang);
            btnXemThongTin = itemView.findViewById(R.id.btnXemThongTin);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_donhang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DonHang donHang = listDH.get(position);

        holder.tvTenKhachHang.setText(donHang.getKhachHang().getTenNguoiDung());
        holder.tvSoDienThoai.setText(donHang.getKhachHang().getSoDT());
        holder.tvDiaChi.setText(donHang.getDiaChi());
        holder.tvPhiShip.setText(donHang.getPhiShip()+"");
        holder.tvTongTien.setText(donHang.getTongTienTT()+"");

        holder.btnNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == 0) {
                    //nhận đơn hàng
                    presenterDonHangShiper.nhanDonHang(ShiperActivity.ORDER_RECIEVE, donHang.getIdHoaDon());
                }else if (role == 1){
                    //giao hàng
                    presenterDonHangShiper.giaoHang(ShiperActivity.ORDER_SHIPED, donHang.getIdHoaDon());
                }
            }
        });
        holder.btnXemThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("donhang", donHang);
                context.startActivity(intent);
//                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

        if (role == 0){
            //chờ giao
            holder.btnNhanDonHang.setText("Nhận đơn hàng");
        }else if (role == 1){
            //đang giao
            holder.btnNhanDonHang.setText("Giao hàng");
        }else if (role == 2){
            //lịch sử
            holder.btnNhanDonHang.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listDH.size();
    }

}
