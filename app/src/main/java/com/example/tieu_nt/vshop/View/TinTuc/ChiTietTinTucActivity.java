package com.example.tieu_nt.vshop.View.TinTuc;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.TinTuc;
import com.example.tieu_nt.vshop.R;

import java.text.SimpleDateFormat;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class ChiTietTinTucActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private TextView tvTieuDe, tvNgayDang, tvNoiDung;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiettintuc);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        TinTuc tinTuc = (TinTuc) getIntent().getSerializableExtra("tinTuc");
        tvTieuDe.setText(tinTuc.getTieuDe());
        String thoiGian = "Ngày " + sdf.format(tinTuc.getThoiGian()).toString();
        tvNgayDang.setText(thoiGian);
        tvNoiDung.setText(tinTuc.getNoiDung());
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
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvNgayDang = (TextView) findViewById(R.id.tvNgayDang);
        tvNoiDung = (TextView) findViewById(R.id.tvNoiDung);
    }
}
