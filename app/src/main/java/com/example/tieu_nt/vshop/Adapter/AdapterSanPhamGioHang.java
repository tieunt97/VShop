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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.CapNhatSoLuongSanPhamGioHang;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.Presenter.GioHang.XoaSanPhamGioHang;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.TrangChu.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

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
    private XoaSanPhamGioHang xoaSanPhamGioHang;
    private CapNhatSoLuongSanPhamGioHang capNhatSoLuongSanPhamGioHang;


    public AdapterSanPhamGioHang(Context context, List<SanPham> dsSanPham, PresenterLogicGioHang presenterLogicGioHang,
                                 XoaSanPhamGioHang xoaSanPhamGioHang, CapNhatSoLuongSanPhamGioHang capNhatSoLuongSanPhamGioHang) {
        this.context = context;
        this.dsSanPham = dsSanPham;
        this.presenterLogicGioHang = presenterLogicGioHang;
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
        if(this.presenterLogicGioHang != null){
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
                                xoaSanPhamGioHang.xoaSanPhamGioHang(sanPham);
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
                                capNhatSoLuongSanPhamGioHang.capNhatSoLuongSanPhamGioHang(position, true);
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
                            capNhatSoLuongSanPhamGioHang.capNhatSoLuongSanPhamGioHang(position, false);
                        }
                    }
                }
            });
        }else{
            Picasso.get().load(sanPham.getHinhSanPham()).into(holder.imgHinhSP);
            holder.linearGioHang.setVisibility(View.GONE);
            holder.linearChiTiet.setVisibility(View.VISIBLE);
            holder.tvSoLuong.setText(String.valueOf(sanPham.getSoLuong()));
        }
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relaSanPham;
        ImageView imgHinhSP;
        TextView tvTenSP, tvGiaSP, tvGiamGia, tvSoSP, tvSoLuong;
        ImageButton imgXoa, imgCong, imgTru;
        LinearLayout linearGioHang, linearChiTiet;
        public ViewHolder(View itemView) {
            super(itemView);
            relaSanPham = (RelativeLayout) itemView.findViewById(R.id.relaSanPham);
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
