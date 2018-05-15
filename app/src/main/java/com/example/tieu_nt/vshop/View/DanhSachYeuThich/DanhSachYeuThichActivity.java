package com.example.tieu_nt.vshop.View.DanhSachYeuThich;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Model.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class DanhSachYeuThichActivity extends AppCompatActivity implements ViewHienThiDanhSachSanPham,
        ILoadMore{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView, recyclerSanPhamYeuThich;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterMenu adapterMenu;
    private AdapterSanPham adapterSanPham;
    private CircleImageView imgInfo;
    private PresenterLogicSanPham presenterLogicSanPham;
    private List<SanPham> dsSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tintuc_dsyeuthich);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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

        adapterMenu = new AdapterMenu(DanhSachYeuThichActivity.this, drawerLayout, 2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("");
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerSanPhamYeuThich = (RecyclerView) findViewById(R.id.recyclerTinTuc);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
    }

    @Override
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        layoutManager = new LinearLayoutManager(this);
        recyclerSanPhamYeuThich.setLayoutManager(layoutManager);
        adapterSanPham = new AdapterSanPham(this, dsSanPham, R.layout.custom_layout_sanpham_list);
        recyclerSanPhamYeuThich.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        recyclerSanPhamYeuThich.post(new Runnable() {
            @Override
            public void run() {
                adapterSanPham.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        List<SanPham> sanPhamLoadMore = presenterLogicSanPham.layDanhSachSanPhamLoadMore(duongDan);
        if(sanPhamLoadMore.size() > 0){
            this.dsSanPham.addAll(sanPhamLoadMore);
            recyclerSanPhamYeuThich.post(new Runnable() {
                @Override
                public void run() {
                    adapterSanPham.notifyDataSetChanged();
                }
            });
        }
    }
}