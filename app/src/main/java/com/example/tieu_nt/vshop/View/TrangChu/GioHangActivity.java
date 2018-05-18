package com.example.tieu_nt.vshop.View.TrangChu;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Adapter.AdapterSanPhamGioHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class GioHangActivity extends AppCompatActivity implements ViewHienThiSanPhamGioHang{
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView tvDiaChi, tvGiaTongSP, tvPhiGiaoHang, tvTongTienTT, tvThongBao;
    private Button btnThanhToan;
    private RelativeLayout relaGioHang;
    private List<SanPham> dsSanPham;
    private PresenterLogicGioHang presenterLogicGioHang;
    private AdapterSanPhamGioHang adapterSanPhamGioHang;
    private RecyclerView.LayoutManager layoutManager;


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
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        relaGioHang = (RelativeLayout) findViewById(R.id.relaGioHang);
    }

    @Override
    public void hienThiSanPhamGioHang(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        if(this.dsSanPham.size() == 0){
            tvThongBao.setVisibility(View.VISIBLE);
            relaGioHang.setVisibility(View.GONE);
        }else{
            adapterSanPhamGioHang = new AdapterSanPhamGioHang(this, dsSanPham, presenterLogicGioHang);
            recyclerView.setAdapter(adapterSanPhamGioHang);
            adapterSanPhamGioHang.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
