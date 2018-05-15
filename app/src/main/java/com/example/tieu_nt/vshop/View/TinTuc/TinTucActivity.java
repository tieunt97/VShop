package com.example.tieu_nt.vshop.View.TinTuc;

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
import com.example.tieu_nt.vshop.Adapter.AdapterTinTuc;
import com.example.tieu_nt.vshop.Model.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.TinTuc;
import com.example.tieu_nt.vshop.Presenter.TinTuc.PresenterLogicTinTuc;
import com.example.tieu_nt.vshop.Presenter.TinTuc.ViewHienThiDanhSachTinTuc;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class TinTucActivity extends AppCompatActivity implements ViewHienThiDanhSachTinTuc, ILoadMore{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView, recyclerViewTinTuc;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterTinTuc adapterTinTuc;
    private AdapterMenu adapterMenu;
    private CircleImageView imgInfo;
    private PresenterLogicTinTuc presenterLogicTinTuc;
    private List<TinTuc> dsTinTuc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tintuc_dsyeuthich);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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

        adapterMenu = new AdapterMenu(TinTucActivity.this, drawerLayout, 1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

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
    }

    @Override
    public void hienThiDanhSachTinTuc(List<TinTuc> dsTinTuc) {
        this.dsTinTuc = dsTinTuc;
        layoutManager = new LinearLayoutManager(this);
        recyclerViewTinTuc.setLayoutManager(layoutManager);
        adapterTinTuc = new AdapterTinTuc(this, dsTinTuc);
        recyclerViewTinTuc.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        recyclerViewTinTuc.post(new Runnable() {
            @Override
            public void run() {
                adapterTinTuc.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        List<TinTuc> tinTucLoadMore = presenterLogicTinTuc.layDanhSachTinTucLoadMore(duongDan);
        if(tinTucLoadMore.size() > 0){
            this.dsTinTuc.addAll(tinTucLoadMore);
            recyclerViewTinTuc.post(new Runnable() {
                @Override
                public void run() {
                    adapterTinTuc.notifyDataSetChanged();
                }
            });
        }
    }
}
