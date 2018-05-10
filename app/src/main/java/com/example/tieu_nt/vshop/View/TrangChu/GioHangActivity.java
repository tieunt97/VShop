package com.example.tieu_nt.vshop.View.TrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class GioHangActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView tvDiaChi, tvGiaTongSP, tvPhiGiaoHang, tvTongTienTT;
    private Button btnThanhToan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        anhXa();

        setSupportActionBar(toolbar);
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerGioHang);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        tvGiaTongSP = (TextView) findViewById(R.id.tvGiaTongSP);
        tvPhiGiaoHang = (TextView) findViewById(R.id.tvPhiGiaoHang);
        tvTongTienTT = (TextView) findViewById(R.id.tvTongTienTT);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
    }
}
