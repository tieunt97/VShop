package com.example.tieu_nt.vshop.View.TinTuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
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
import com.example.tieu_nt.vshop.Adapter.AdapterTinTuc;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.TinTuc;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangTinTuc;
import com.example.tieu_nt.vshop.Presenter.TinTuc.PresenterLogicTinTuc;
import com.example.tieu_nt.vshop.Presenter.TinTuc.ViewHienThiDanhSachTinTuc;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.MainActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class TinTucActivity extends MainActivity implements ViewHienThiDanhSachTinTuc, ILoadMore{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView, recyclerViewTinTuc;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterTinTuc adapterTinTuc;
    private AdapterMenu adapterMenu;
    private PresenterLogicTinTuc presenterLogicTinTuc;
    private List<TinTuc> dsTinTuc;
    private TrangTinTuc trangTinTuc;
    private TextView tvHoTen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tintuc_dsyeuthich);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tin tức");

        drawerToggle = new ActionBarDrawerToggle(TinTucActivity.this, drawerLayout, R.string.open, R.string.close){
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

        layoutManager = new LinearLayoutManager(this);

        adapterMenu = new AdapterMenu(TinTucActivity.this, drawerLayout, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

        selectImage();

        presenterLogicTinTuc = new PresenterLogicTinTuc(this);
        presenterLogicTinTuc.layDanhSachTinTuc("");
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewTinTuc = (RecyclerView) findViewById(R.id.recyclerTinTuc);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        tvHoTen = (TextView) findViewById(R.id.tvHoTen);
    }

    @Override
    public void hienThiDanhSachTinTuc(TrangTinTuc trangTinTuc) {
        this.trangTinTuc = trangTinTuc;
        this.dsTinTuc = this.trangTinTuc.getDsTinTuc();
        recyclerViewTinTuc.setLayoutManager(layoutManager);
        adapterTinTuc = new AdapterTinTuc(this, dsTinTuc);
        recyclerViewTinTuc.addOnScrollListener(new LoadMoreScroll(layoutManager, this,
                this.trangTinTuc.isTrangCuoi(), this.trangTinTuc.getNextPage()));
        recyclerViewTinTuc.post(new Runnable() {
            @Override
            public void run() {
                adapterTinTuc.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        trangTinTuc = presenterLogicTinTuc.layDanhSachTinTucLoadMore(duongDan);
        if(trangTinTuc.getDsTinTuc().size() > 0){
            this.dsTinTuc.addAll(trangTinTuc.getDsTinTuc());
            recyclerViewTinTuc.post(new Runnable() {
                @Override
                public void run() {
                    adapterTinTuc.notifyDataSetChanged();
                }
            });
        }
    }
}
