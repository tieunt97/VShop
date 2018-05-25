package com.example.tieu_nt.vshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.CapNhatSoLuongSanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.Presenter.GioHang.XoaSanPham;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.TrangChu.ChiTietSanPhamActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class AdapterSanPhamGioHang extends RecyclerView.Adapter<AdapterSanPhamGioHang.ViewHolder>{
    private Context context;
    private List<SanPham> dsSanPham;
    private NumberFormat numberFormat = new DecimalFormat("###,###");
    private PresenterLogicGioHang presenterLogicGioHang;
    private XoaSanPham xoaSanPhamGioHang;
    private CapNhatSoLuongSanPham capNhatSoLuongSanPhamGioHang;


    public AdapterSanPhamGioHang(Context context, List<SanPham> dsSanPham, XoaSanPham xoaSanPhamGioHang,
                                 CapNhatSoLuongSanPham capNhatSoLuongSanPhamGioHang) {
        this.context = context;
        this.dsSanPham = dsSanPham;
        this.presenterLogicGioHang = new PresenterLogicGioHang(context);
        this.xoaSanPhamGioHang = xoaSanPhamGioHang;
        this.capNhatSoLuongSanPhamGioHang = capNhatSoLuongSanPhamGioHang;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_item_giohang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SanPham sanPham = dsSanPham.get(position);
        holder.tvTenSP.setText(sanPham.getTenSanPham());
        holder.tvGiaSP.setText(numberFormat.format(sanPham.getGiaChuan()).toString() + " đ");
        holder.relaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("idSanPham", sanPham.getIdSanPham());
                context.startActivity(intent);
            }
        });
        holder.rbDanhGia.setRating(sanPham.getDanhGiaTB());
        holder.tvSoDanhGia.setText("(" + sanPham.getSoLuotDanhGia() + ")");
        byte[] hinh = sanPham.getHinhSPGioHang();
        holder.imgHinhSP.setImageBitmap(BitmapFactory.decodeByteArray(hinh, 0, hinh.length));
        holder.tvSoSP.setText(String.valueOf(sanPham.getSoLuong()));
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_thongbao_xacnhan, null);
                Button btnHuy = (Button) view1.findViewById(R.id.btnHuy);
                Button btnDongY = (Button) view1.findViewById(R.id.btnDongY);
                btnDongY.setText("Có xóa");
                TextView tvNoiDung = (TextView) view1.findViewById(R.id.tvNoiDung);
                tvNoiDung.setText("Bạn có chắc muốn xóa sản phẩm này trong giỏ hàng?");

                builder.setView(view1);
                final AlertDialog dialogCloseApp = builder.create();
                dialogCloseApp.show();

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogCloseApp.dismiss();
                    }
                });

                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogCloseApp.dismiss();
                        if(presenterLogicGioHang.xoaSanPhamGioHang(sanPham.getIdSanPham())){
                            dsSanPham.remove(sanPham);
                            notifyDataSetChanged();
                            xoaSanPhamGioHang.xoaSanPham(sanPham);
                        }else{
                            Log.d("XoaSPGioHang", "thất bại");
                        }
                    }
                });
            }
        });
        holder.imgCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = sanPham.getSoLuong();
                int soLuongTonKho = sanPham.getSoLuongTonKho();
                if(soLuong < 5){
                    soLuong += 1;
                    if(soLuongTonKho > 0){
                        soLuongTonKho -= 1;
                        if(presenterLogicGioHang.capNhatSoLuongSPGioHang(sanPham.getIdSanPham(), soLuong, soLuongTonKho)){
                            sanPham.setSoLuong(soLuong);
                            sanPham.setSoLuongTonKho(soLuongTonKho);
                            notifyDataSetChanged();
                            capNhatSoLuongSanPhamGioHang.capNhatSoLuongSanPham(position, true);
                        }
                    }else{
                        capNhatSoLuongSanPhamGioHang.capNhatThatBai("Sản phẩm này đã hết hàng");
                    }
                }else{
                    capNhatSoLuongSanPhamGioHang.capNhatThatBai("Số lượng tối đa cho mỗi sản phẩm là 5 sản phẩm");
                }
            }
        });
        holder.imgTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = sanPham.getSoLuong();
                int soLuongTonKho = sanPham.getSoLuongTonKho();
                if(soLuong > 1){
                    soLuong -= 1;
                    soLuongTonKho += 1;
                    if(presenterLogicGioHang.capNhatSoLuongSPGioHang(sanPham.getIdSanPham(), soLuong, soLuongTonKho)){
                        sanPham.setSoLuong(soLuong);
                        sanPham.setSoLuongTonKho(soLuongTonKho);
                        notifyDataSetChanged();
                        capNhatSoLuongSanPhamGioHang.capNhatSoLuongSanPham(position, false);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relaSanPham;
        ImageView imgHinhSP;
        RatingBar rbDanhGia;
        TextView tvTenSP, tvGiaSP, tvGiamGia, tvSoSP, tvSoLuong, tvSoDanhGia;
        ImageButton imgXoa, imgCong, imgTru;
        LinearLayout linearGioHang;
        public ViewHolder(View itemView) {
            super(itemView);
            relaSanPham = (RelativeLayout) itemView.findViewById(R.id.relaSanPham);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            rbDanhGia = (RatingBar) itemView.findViewById(R.id.rbDanhGia);
            tvTenSP = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvGiaSP = (TextView) itemView.findViewById(R.id.tvGiaSP);
            tvGiamGia = (TextView) itemView.findViewById(R.id.tvGiamGia);
            tvSoSP = (TextView) itemView.findViewById(R.id.tvSoSanPham);
            tvSoLuong = (TextView) itemView.findViewById(R.id.tvSoLuong);
            tvSoDanhGia = (TextView) itemView.findViewById(R.id.tvSoDanhGia);
            imgXoa = (ImageButton) itemView.findViewById(R.id.imgXoa);
            imgCong = (ImageButton) itemView.findViewById(R.id.imgCong);
            imgTru = (ImageButton) itemView.findViewById(R.id.imgTru);
            linearGioHang = (LinearLayout) itemView.findViewById(R.id.linearGioHang);
        }
    }
}
