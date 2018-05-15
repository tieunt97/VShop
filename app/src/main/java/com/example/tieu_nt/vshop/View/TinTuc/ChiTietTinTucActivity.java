package com.example.tieu_nt.vshop.View.TinTuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.TinTuc;
import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class ChiTietTinTucActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private TextView tvTieuDe, tvNgayDang, tvNoiDung;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiettintuc);
        anhXa();

        setSupportActionBar(toolbar);

        TinTuc tinTuc = (TinTuc) getIntent().getSerializableExtra("tinTuc");
        tvTieuDe.setText(tinTuc.getTieuDe());
        String ngayDang = "Ngày " + tinTuc.getNgayDang()[2] + " Tháng " + tinTuc.getNgayDang()[1] + " Năm" + tinTuc.getNgayDang()[0];
        tvNgayDang.setText(ngayDang);
        tvNoiDung.setText(tinTuc.getNoiDung());
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvNgayDang = (TextView) findViewById(R.id.tvNgayDang);
        tvNoiDung = (TextView) findViewById(R.id.tvNoiDung);
    }
}
