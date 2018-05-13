package com.example.tieu_nt.vshop.View.TrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Model.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.SanPham;
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
    private RecyclerView.LayoutManager layoutGrid, layoutList;
    private AdapterSanPham adapterSanPham;
    private List<SanPham> dsSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheothuonghieu);
        anhXa();

        layoutGrid = new GridLayoutManager(this, 2);
        layoutList = new LinearLayoutManager(this);
        setSupportActionBar(toolbar);
        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("");
        setActions();
    }

    private void setActions() {
        btnSapXep.setOnClickListener(this);
        btnLoc.setOnClickListener(this);
        tgLayout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                grid = !b;
                presenterLogicSanPham.layDanhSachSanPham("");
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
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        if(grid) {
            recyclerSanPham.setLayoutManager(layoutGrid);
            adapterSanPham = new AdapterSanPham(this, dsSanPham, R.layout.custom_layout_sanpham);
            recyclerSanPham.setOnScrollListener(new LoadMoreScroll(layoutGrid, this));
        }else{
            recyclerSanPham.setLayoutManager(layoutList);
            adapterSanPham = new AdapterSanPham(this, dsSanPham, R.layout.custom_layout_sanpham_list);
            recyclerSanPham.setOnScrollListener(new LoadMoreScroll(layoutList, this));
        }

        recyclerSanPham.post(new Runnable() {
            @Override
            public void run() {
                recyclerSanPham.setAdapter(adapterSanPham);
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        List<SanPham> dsSanPham = presenterLogicSanPham.layDanhSachSanPhamLoadMore(duongDan);
        if (dsSanPham.size() > 0){
            this.dsSanPham.addAll(dsSanPham);
            recyclerSanPham.post(new Runnable() {
                @Override
                public void run() {
                    recyclerSanPham.setAdapter(adapterSanPham);
                }
            });
        }
    }
}
