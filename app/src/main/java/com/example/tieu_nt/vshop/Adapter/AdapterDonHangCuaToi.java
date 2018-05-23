package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tieu_nt on 5/15/2018.
 */

public class AdapterDonHangCuaToi extends RecyclerView.Adapter<AdapterDonHangCuaToi.ViewHolder>{
    private Context context;
    private List<DonHang> dsDonHang;
    private NumberFormat numberFormat = new DecimalFormat("###,###");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public AdapterDonHangCuaToi(Context context, List<DonHang> dsDonHang) {
        this.context = context;
        this.dsDonHang = dsDonHang;
    }

    @Override
    public AdapterDonHangCuaToi.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_donhang_quanlydonhang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterDonHangCuaToi.ViewHolder holder, int position) {
        DonHang donHang = dsDonHang.get(position);
        holder.tvNgayPhatSinh.setText(sdf.format(donHang.getNgayDat()).toString());
        holder.tvDiaChi.setText(donHang.getDiaChi());
        int tongTien = donHang.getPhiShip();
        for(SanPham sp: donHang.getDsSanPham()){
            tongTien += sp.getGiaChuan()*sp.getSoLuong();
        }
        holder.tvTongTienTT.setText(numberFormat.format(tongTien).toString() + " đ");
        holder.tvPhiGiaoHang.setText(numberFormat.format(donHang.getPhiShip()).toString() + " đ");
        switch (donHang.getTrangThai()){
            case DonHang.TRANGTHAI_DADATHANG:
                holder.tvTrangThai.setText("Đã đặt hàng");
                break;
            case DonHang.TRANGTHAI_DANGGIAOHANG:
                holder.tvTrangThai.setText("Đang giao hàng");
                break;
            case DonHang.TRANGTHAI_DANHANHANG:
                holder.tvTrangThai.setText("Đã nhận hàng");
                break;
            case DonHang.TRANGTHAI_HUYDONHANG:
                holder.tvTrangThai.setText("Đơn hàng bị hủy");
                break;
            case DonHang.TRANGTHAI_COHANGTRA:
                holder.tvTrangThai.setText("Có hàng trả về");
                break;
            default:
                holder.tvTrangThai.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dsDonHang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgayPhatSinh, tvDiaChi, tvTongTienTT, tvPhiGiaoHang, tvTrangThai;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNgayPhatSinh = (TextView) itemView.findViewById(R.id.tvNgayPhatSinh);
            tvDiaChi = (TextView) itemView.findViewById(R.id.tvDiaChi);
            tvTongTienTT = (TextView) itemView.findViewById(R.id.tvTongTienTT);
            tvPhiGiaoHang = (TextView) itemView.findViewById(R.id.tvPhiGiaoHang);
            tvTrangThai = (TextView) itemView.findViewById(R.id.tvTrangThai);
        }
    }
}
