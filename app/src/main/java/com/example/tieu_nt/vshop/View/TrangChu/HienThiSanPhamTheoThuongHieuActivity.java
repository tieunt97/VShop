package com.example.tieu_nt.vshop.View.TrangChu;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.R;

import java.util.List;

/**
 * Created by tieu_nt on 4/20/2018.
 */

public class HienThiSanPhamTheoThuongHieuActivity extends AppCompatActivity implements View.OnClickListener,
        ViewHienThiDanhSachSanPham, ILoadMore{
    private Toolbar toolbar;
    private RecyclerView recyclerSanPham;
    private ToggleButton tgLayout;
    private Button btnSapXep, btnLoc;
    private boolean grid = true;
    private PresenterLogicSanPham presenterLogicSanPham;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private AdapterSanPham adapterSanPham;
    private List<SanPham> dsSanPham;
    private TrangSanPham trangSanPham;
    private String duongDan = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheothuonghieu);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        gridLayoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager = new LinearLayoutManager(this);

        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham(duongDan);
        setActions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setActions() {
        btnSapXep.setOnClickListener(this);
        btnLoc.setOnClickListener(this);
        tgLayout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                grid = !b;
                presenterLogicSanPham.layDanhSachSanPham(duongDan);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        return true;
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerSanPham = (RecyclerView) findViewById(R.id.recyclerSanPham);
        tgLayout = (ToggleButton) findViewById(R.id.tgLayout);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
        btnLoc = (Button) findViewById(R.id.btnLoc);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void hienThiDanhSachSanPham(TrangSanPham trangSanPham) {
        this.trangSanPham = trangSanPham;
        this.dsSanPham = trangSanPham.getDsSanPham();
        int layout;
        if(grid) {
            layout = R.layout.custom_layout_sanpham;
            layoutManager = gridLayoutManager;
        }else{
            layout = R.layout.custom_layout_sanpham_list;
            layoutManager = linearLayoutManager;
        }
        recyclerSanPham.setLayoutManager(layoutManager);
        recyclerSanPham.addOnScrollListener(new LoadMoreScroll(layoutManager, this,
                this.trangSanPham.isTrangCuoi(), this.trangSanPham.getNextPage()));
        adapterSanPham = new AdapterSanPham(this, dsSanPham, layout);
        recyclerSanPham.setAdapter(adapterSanPham);
        recyclerSanPham.post(new Runnable() {
            @Override
            public void run() {
                adapterSanPham.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        trangSanPham = presenterLogicSanPham.layDanhSachSanPhamLoadMore(duongDan);
        if (trangSanPham.getDsSanPham().size() > 0){
            this.dsSanPham.addAll(trangSanPham.getDsSanPham());
            recyclerSanPham.post(new Runnable() {
                @Override
                public void run() {
                    adapterSanPham.notifyDataSetChanged();
                }
            });
        }
    }
}
