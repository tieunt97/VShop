package com.example.tieu_nt.vshop.View.TrangChu;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Adapter.AdapterSanPhamGioHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.CapNhatSoLuongSanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.Presenter.GioHang.XoaSanPham;
import com.example.tieu_nt.vshop.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class GioHangActivity extends AppCompatActivity implements ViewHienThiSanPhamGioHang, CapNhatSoLuongSanPham,
        XoaSanPham {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView tvDiaChi, tvGiaTongSP, tvPhiGiaoHang, tvTongTienTT, tvThongBao, tvSoSanPham;
    private Button btnThanhToan;
    private RelativeLayout relaGioHang;
    private List<SanPham> dsSanPham;
    private PresenterLogicGioHang presenterLogicGioHang;
    private AdapterSanPhamGioHang adapterSanPhamGioHang;
    private RecyclerView.LayoutManager layoutManager;
    private NumberFormat numberFormat = new DecimalFormat("###,###");
    private int phiGiaoHang = 65000, giaTong = 0, soSP = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        presenterLogicGioHang = new PresenterLogicGioHang(this, this);
        presenterLogicGioHang.layDSSanPhamGioHang();

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChuActivity.nguoiDung == null){
                    Toast.makeText(GioHangActivity.this, "Bạn cần đăng nhập để thanh toán", Toast.LENGTH_SHORT).show();
                }else{
                    Intent iThanhToan = new Intent(GioHangActivity.this, XacNhanMuaHangActivity.class);
                    iThanhToan.putExtra("phiShip", phiGiaoHang);
                    startActivity(iThanhToan);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerGioHang);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        tvGiaTongSP = (TextView) findViewById(R.id.tvGiaTongSP);
        tvPhiGiaoHang = (TextView) findViewById(R.id.tvPhiGiaoHang);
        tvTongTienTT = (TextView) findViewById(R.id.tvTongTienTT);
        tvThongBao = (TextView) findViewById(R.id.tvThongBao);
        tvSoSanPham = (TextView) findViewById(R.id.tvSoSanPham);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        relaGioHang = (RelativeLayout) findViewById(R.id.relaGioHang);
    }

    @Override
    public void hienThiSanPhamGioHang(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        if(this.dsSanPham.size() == 0){
            toolbar.setTitle("Giỏ hàng của tôi");
            tvThongBao.setVisibility(View.VISIBLE);
            relaGioHang.setVisibility(View.GONE);
        }else{
            tvThongBao.setVisibility(View.GONE);
            relaGioHang.setVisibility(View.VISIBLE);
            adapterSanPhamGioHang = new AdapterSanPhamGioHang(this, this.dsSanPham,
                    this, this);
            recyclerView.setAdapter(adapterSanPhamGioHang);
            adapterSanPhamGioHang.notifyDataSetChanged();

            for(SanPham sp: dsSanPham){
                giaTong += sp.getGiaChuan()*sp.getSoLuong();
                soSP += sp.getSoLuong();
                Log.d("SOLUONGTONKHO", sp.getSoLuongTonKho() + "");
            }
            setContent();
        }
    }

    private void setContent(){
        toolbar.setTitle("Giỏ hàng của tôi(" + soSP + ")");
        tvSoSanPham.setText("Tạm tính (" + soSP + " sản phẩm)");
        tvGiaTongSP.setText(numberFormat.format(giaTong).toString() + " đ");
        tvPhiGiaoHang.setText(numberFormat.format(phiGiaoHang).toString() + " đ");
        tvTongTienTT.setText(numberFormat.format(giaTong + phiGiaoHang).toString()  + " đ");
    }

    @Override
    public void xoaSanPham(SanPham sanPham) {
        int tongXoa = sanPham.getSoLuong();
        soSP -= tongXoa;
        giaTong -= tongXoa*sanPham.getGiaChuan();
        if(dsSanPham.size() == 0){
            toolbar.setTitle("Giỏ hàng của tôi");
            tvThongBao.setVisibility(View.VISIBLE);
            relaGioHang.setVisibility(View.GONE);
        }else{
            setContent();
        }
    }

    @Override
    public void capNhatSoLuongSanPham(int positon, boolean them) {
        SanPham sanPham = dsSanPham.get(positon);
        if(them) {
            soSP += 1;
            giaTong += sanPham.getGiaChuan();
            setContent();
        }else{
            soSP -= 1;
            giaTong -= sanPham.getGiaChuan();
            setContent();
        }
    }

    @Override
    public void capNhatThatBai(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("soSP", soSP);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
