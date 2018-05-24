package com.example.tieu_nt.vshop.View.TrangChu;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.R;

import java.util.List;

/**
 * Created by tieu_nt on 5/24/2018.
 */

public class SanPhamTimKiemActivity extends AppCompatActivity implements ViewHienThiDanhSachSanPham, ILoadMore{
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerSanPham;
    private String timKiem;
    private String duongDan;
    private PresenterLogicSanPham presenterLogicSanPham;
    private List<SanPham> dsSanPham;
    private AdapterSanPham adapterSanPham;
    private RecyclerView.LayoutManager layoutManager;
    private LoadMoreScroll loadMoreScroll;
    private TextView tvThongBao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanphamtimkiem);
        anhXa();
        tvThongBao.setVisibility(View.VISIBLE);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        timKiem = getIntent().getStringExtra("timKiem");

        duongDan = TrangChuActivity.SERVER + "/products/search/" + timKiem;

        layoutManager = new GridLayoutManager(this, 2);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerSanPham = findViewById(R.id.recyclerSanPham);
        tvThongBao = findViewById(R.id.tvThongBao);
    }

    @Override
    public void hienThiDanhSachSanPham(TrangSanPham trangSanPham) {
        tvThongBao.setVisibility(View.GONE);
        this.dsSanPham = trangSanPham.getDsSanPham();
        recyclerSanPham.setLayoutManager(layoutManager);
        adapterSanPham = new AdapterSanPham(this,  dsSanPham, R.layout.custom_layout_sanpham);
        recyclerSanPham.setAdapter(adapterSanPham);
        loadMoreScroll = new LoadMoreScroll(layoutManager, this);
        loadMoreScroll.setTrangCuoi(trangSanPham.isTrangCuoi());
        loadMoreScroll.setDuongDan(trangSanPham.getNextPage());
        recyclerSanPham.addOnScrollListener(loadMoreScroll);
        adapterSanPham.notifyDataSetChanged();
    }

    @Override
    public void loadMore(String duongDan) {
        TrangSanPham trangSanPham = presenterLogicSanPham.layDanhSachSanPhamLoadMore(duongDan);
        loadMoreScroll.setTrangCuoi(trangSanPham.isTrangCuoi());
        loadMoreScroll.setDuongDan(trangSanPham.getNextPage());
        if(trangSanPham.getDsSanPham().size() > 0){
            dsSanPham.addAll(trangSanPham.getDsSanPham());
            recyclerSanPham.post(new Runnable() {
                @Override
                public void run() {
                    adapterSanPham.notifyDataSetChanged();
                }
            });
        }
    }
}
