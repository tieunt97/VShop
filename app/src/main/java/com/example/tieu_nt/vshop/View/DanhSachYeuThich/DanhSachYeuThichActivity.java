package com.example.tieu_nt.vshop.View.DanhSachYeuThich;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Model.DangNhap;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.DanhSachYeuThich.PresenterLogicLaySPYeuThich;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.MainActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class DanhSachYeuThichActivity extends MainActivity implements ViewHienThiSanPhamYeuThich{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView, recyclerSanPhamYeuThich;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterMenu adapterMenu;
    private AdapterSanPham adapterSanPham;
    private PresenterLogicLaySPYeuThich presenterLogicSanPham;
    private List<SanPham> dsSanPham;
    private TextView tvHoTen, tvThongBao;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String duongDan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tintuc_dsyeuthich);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Danh sách yêu thích");

        selectImage();
        duongDan = TrangChuActivity.SERVER + "/api/likes/customer?api_token=" + DangNhap.getInstance().getNguoiDung().getToken();
        drawerToggle = new ActionBarDrawerToggle(DanhSachYeuThichActivity.this, drawerLayout, R.string.open, R.string.close){
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

        adapterMenu = new AdapterMenu(DanhSachYeuThichActivity.this, drawerLayout, 2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

        tvThongBao.setVisibility(View.VISIBLE);

        presenterLogicSanPham = new PresenterLogicLaySPYeuThich(this);
        presenterLogicSanPham.layDSSanPhamYT(duongDan);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterLogicSanPham.layDSSanPhamYT(duongDan);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerSanPhamYeuThich = (RecyclerView) findViewById(R.id.recyclerTinTuc);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        tvHoTen = (TextView) findViewById(R.id.tvHoTen);
        tvThongBao = (TextView) findViewById(R.id.tvThongBao);
    }

    @Override
    public void hienThiSanPhamYeuThich(List<SanPham> dsSanPham) {
        tvThongBao.setVisibility(View.GONE);
        this.dsSanPham = dsSanPham;
        adapterSanPham = new AdapterSanPham(this, this.dsSanPham, R.layout.custom_layout_sanpham_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerSanPhamYeuThich.setLayoutManager(layoutManager);
        recyclerSanPhamYeuThich.setAdapter(adapterSanPham);
        adapterSanPham.notifyDataSetChanged();
    }
}