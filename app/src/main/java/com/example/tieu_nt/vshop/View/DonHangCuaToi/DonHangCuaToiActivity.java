package com.example.tieu_nt.vshop.View.DonHangCuaToi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Adapter.ViewPagerAdapterDonHangCuaToi;
import com.example.tieu_nt.vshop.Model.DangNhap;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.MainActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class DonHangCuaToiActivity extends MainActivity{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView, recyclerViewTinTuc;
    private AdapterMenu adapterMenu;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapterDonHangCuaToi viewPagerAdapter;
    private TextView tvHoTen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donhangcuatoi);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(DonHangCuaToiActivity.this, drawerLayout, R.string.open, R.string.close){
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

        adapterMenu = new AdapterMenu(DonHangCuaToiActivity.this, drawerLayout, 3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

        viewPagerAdapter = new ViewPagerAdapterDonHangCuaToi(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        selectImage();
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewTinTuc = (RecyclerView) findViewById(R.id.recyclerTinTuc);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPagerDonHangCuaToi);
        tvHoTen = (TextView) findViewById(R.id.tvHoTen);
    }
}
