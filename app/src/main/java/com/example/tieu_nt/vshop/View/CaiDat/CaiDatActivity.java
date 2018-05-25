package com.example.tieu_nt.vshop.View.CaiDat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Model.DangNhap;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.MainActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class CaiDatActivity extends MainActivity implements View.OnClickListener{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterMenu adapterMenu;
    private TextView tvCapNhatDiaChi, tvDoiMatKhau, tvHoTen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_caidat);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(CaiDatActivity.this, drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                trangChu.setTranslationX(slideOffset * drawerView.getWidth());
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        if(DangNhap.getInstance().getNguoiDung() != null){
            tvHoTen.setText(DangNhap.getInstance().getNguoiDung().getTenNguoiDung());
        }else{
            tvHoTen.setText("Bạn chưa đăng nhập");
        }

        adapterMenu = new AdapterMenu(CaiDatActivity.this, drawerLayout, 4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);
        setActions();
        selectImage();
    }

    private void anhXa() {
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        tvCapNhatDiaChi =  (TextView) findViewById(R.id.tvCapNhatThongTin);
        tvDoiMatKhau =  (TextView) findViewById(R.id.tvDoiMatKhau);
        tvHoTen = (TextView) findViewById(R.id.tvHoTen);
    }

    private void setActions(){
        tvCapNhatDiaChi.setOnClickListener(this);
        tvDoiMatKhau.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvCapNhatThongTin:
                Intent iDiaChi = new Intent(CaiDatActivity.this, CapNhatThongTinActivity.class);
                startActivity(iDiaChi);
                break;
            case R.id.tvDoiMatKhau:
                Intent iDoiMatKhau = new Intent(CaiDatActivity.this, DoiMatKhauActivity.class);
                startActivity(iDoiMatKhau);
                break;
        }
    }
}
