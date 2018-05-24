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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
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

public class DanhSachYeuThichActivity extends MainActivity implements ViewHienThiDanhSachSanPham,
        ILoadMore{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView, recyclerSanPhamYeuThich;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterMenu adapterMenu;
    private AdapterSanPham adapterSanPham;
    private PresenterLogicSanPham presenterLogicSanPham;
    private TrangSanPham trangSanPham;
    private List<SanPham> dsSanPham;
    private TextView tvHoTen;
    private LoadMoreScroll loadMoreScroll;
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

        if(TrangChuActivity.nguoiDung != null){
            tvHoTen.setText(TrangChuActivity.nguoiDung.getTenNguoiDung());
        }else{
            tvHoTen.setText("Bạn chưa đăng nhập");
        }

        duongDan = "";

        adapterMenu = new AdapterMenu(DanhSachYeuThichActivity.this, drawerLayout, 2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham(duongDan);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterLogicSanPham.layDanhSachSanPham(duongDan);
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
    }

    @Override
    public void hienThiDanhSachSanPham(TrangSanPham trangSanPham) {
        this.trangSanPham = trangSanPham;
        this.dsSanPham = this.trangSanPham.getDsSanPham();
        layoutManager = new LinearLayoutManager(this);
        recyclerSanPhamYeuThich.setLayoutManager(layoutManager);
        adapterSanPham = new AdapterSanPham(this, dsSanPham, R.layout.custom_layout_sanpham_list);
        recyclerSanPhamYeuThich.setAdapter(adapterSanPham);
        loadMoreScroll = new LoadMoreScroll(layoutManager, this);
        loadMoreScroll.setTrangCuoi(trangSanPham.isTrangCuoi());
        loadMoreScroll.setDuongDan(trangSanPham.getNextPage());
        recyclerSanPhamYeuThich.addOnScrollListener(loadMoreScroll);
        recyclerSanPhamYeuThich.post(new Runnable() {
            @Override
            public void run() {
                adapterSanPham.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        trangSanPham = presenterLogicSanPham.layDanhSachSanPhamLoadMore(duongDan);
        loadMoreScroll.setTrangCuoi(trangSanPham.isTrangCuoi());
        loadMoreScroll.setDuongDan(trangSanPham.getNextPage());
        if(trangSanPham.getDsSanPham().size() > 0){
            this.dsSanPham.addAll(trangSanPham.getDsSanPham());
            recyclerSanPhamYeuThich.post(new Runnable() {
                @Override
                public void run() {
                    adapterSanPham.notifyDataSetChanged();
                }
            });
        }
    }
}