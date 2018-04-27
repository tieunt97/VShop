package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Model.DrawerItem;
import com.example.tieu_nt.vshop.Presenter.ItemClickListener;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.CaiDat.CaiDatActivity;
import com.example.tieu_nt.vshop.View.DanhSachYeuThich.DanhSachYeuThichActivity;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.DonHangCuaToiActivity;
import com.example.tieu_nt.vshop.View.TinTuc.TinTucActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.RecyclerViewHolder>{
    private List<DrawerItem> dsItems;
    private Context context;
    private DrawerLayout drawerLayout;
    private int position;
    private String tenItems[] = {"Trang chủ", "Tin tức", "Danh sách yêu thích","Đơn hàng của tôi", "Cài đặt",
            "Trung tâm hỗ trợ", "Giới thiệu VShop", "Đăng xuất"};
    private int[] hinhItems = {R.drawable.home, R.drawable.newspaper, R.drawable.like, R.drawable.clipboards,
            R.drawable.settings, R.drawable.mail, R.drawable.info, R.drawable.logout};

    public AdapterMenu(Context context, DrawerLayout drawerLayout, int position){
        this.context = context;
        this.dsItems = new ArrayList<>();
        this.drawerLayout = drawerLayout;
        this.position = position;

        int length = tenItems.length;
//        if (khachHang != null) tenItems[length - 1] = "Đăng xuất";
//        else tenItems[length - 1] = "Đăng nhập";

        for (int i = 0; i < length; i++){
            dsItems.add(new DrawerItem(hinhItems[i], tenItems[i]));
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_drawer_layout, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final int viTri = this.position;
        if (viTri == position){
            holder.textView.setTextColor(context.getResources().getColor(R.color.colorRed));
            holder.imgNext.setImageResource(R.drawable.next);
        }
        holder.textView.setText(dsItems.get(position).getTenItem());
        holder.imgItem.setImageResource(dsItems.get(position).getIcon());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(viTri == position){
                    drawerLayout.closeDrawers();
                }else{
                    switch (position){
                        case 0:
                            Intent iTrangChu = new Intent(context, TrangChuActivity.class);
                            context.startActivity(iTrangChu);
                            break;
                        case 1:
                            Intent iTinTuc = new Intent(context, TinTucActivity.class);
                            context.startActivity(iTinTuc);
                            break;
                        case 2:
                            Intent iDSYeuThich = new Intent(context, DanhSachYeuThichActivity.class);
                            context.startActivity(iDSYeuThich);
                            break;
                        case 3:
                            Intent iDonHangCuaToi = new Intent(context, DonHangCuaToiActivity.class);
                            context.startActivity(iDonHangCuaToi);
                            break;
                        case 4:
                            Intent iCaiDat = new Intent(context, CaiDatActivity.class);
                            context.startActivity(iCaiDat);
                            break;
                        case 5:
                            Intent iTrungTamHoTro = new Intent(context, TrangChuActivity.class);
                            context.startActivity(iTrungTamHoTro);
                            break;
                        case 6:
                            Intent iGioiThieu = new Intent(context, TrangChuActivity.class);
                            context.startActivity(iGioiThieu);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView textView;
        ImageView imgItem, imgNext;
        private ItemClickListener itemClickListener;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvTenItem);
            imgItem = itemView.findViewById(R.id.imgItem);
            imgNext = itemView.findViewById(R.id.imgNext);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }
    }
}
