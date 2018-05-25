package com.example.tieu_nt.vshop.View.TrangChu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.BottomSheetLocSanPham;
import com.example.tieu_nt.vshop.View.DialogSapXep;
import com.example.tieu_nt.vshop.View.LocSanPham;
import com.example.tieu_nt.vshop.View.SapXepSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 4/20/2018.
 */

public class HienThiSanPhamTheoThuongHieuActivity extends AppCompatActivity implements View.OnClickListener,
        ViewHienThiDanhSachSanPham, ILoadMore, SapXepSanPham, LocSanPham {
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerSanPham;
    private ToggleButton tgLayout;
    private Button btnSapXep, btnLoc;
    private Menu menu;
    private TextView tvSoSPGioHang;
    private boolean grid = true;
    private PresenterLogicSanPham presenterLogicSanPham;
    private PresenterLogicGioHang presenterLogicGioHang;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private AdapterSanPham adapterSanPham;
    private List<SanPham> dsSanPham;
    private TrangSanPham trangSanPham;
    private String duongDan = "";
    private ThuongHieu thuongHieu;
    private int soSP;
    private LoadMoreScroll loadMoreScroll;
    private BottomSheetLocSanPham bottomSheetLocSanPham;
    private String sapXep = "", maThuongHieu = "", soSaoTB = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheothuonghieu);
        anhXa();

        bottomSheetLocSanPham = new BottomSheetLocSanPham();

        thuongHieu = (ThuongHieu) getIntent().getSerializableExtra("thuongHieu");
        if(thuongHieu != null){
            duongDan = TrangChuActivity.SERVER + "/sort&filter/products?provider_id=" + thuongHieu.getIdThuongHieu();
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        toolbar.setTitle(thuongHieu.getTenThuongHieu());

        gridLayoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager = new LinearLayoutManager(this);

        presenterLogicGioHang = new PresenterLogicGioHang(this);

        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham(duongDan);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterLogicSanPham.layDanhSachSanPham(duongDan);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        setActions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_giohang, menu);
        this.menu = menu;
        MenuItem iGioHang = menu.findItem(R.id.itemGioHang);
        View itemGioHang = MenuItemCompat.getActionView(iGioHang);
        tvSoSPGioHang = (TextView) itemGioHang.findViewById(R.id.tvSoSPGioHang);

        List<SanPham> dsSanPhamGioHang = presenterLogicGioHang.layDSSanPhamGioHang();
        if(dsSanPhamGioHang.size() == 0) {
            tvSoSPGioHang.setVisibility(View.GONE);
        }else{
            for(SanPham sp: dsSanPhamGioHang){
                soSP += sp.getSoLuong();
            }
            tvSoSPGioHang.setVisibility(View.VISIBLE);
            tvSoSPGioHang.setText(String.valueOf(soSP));
        }

        itemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(HienThiSanPhamTheoThuongHieuActivity.this, GioHangActivity.class);
                startActivityForResult(iGioHang, TrangChuActivity.REQUEST_GIOHANG);
            }
        });

        return true;
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

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerSanPham = (RecyclerView) findViewById(R.id.recyclerSanPham);
        tgLayout = (ToggleButton) findViewById(R.id.tgLayout);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
        btnLoc = (Button) findViewById(R.id.btnLoc);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSapXep:
                DialogSapXep dialogSapXep = new DialogSapXep(this, this);
                dialogSapXep.show();
                break;
            case R.id.btnLoc:
                bottomSheetLocSanPham.show(getSupportFragmentManager(), "LocSanPham");
                break;
        }
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
        loadMoreScroll = new LoadMoreScroll(layoutManager, this);
        loadMoreScroll.setTrangCuoi(trangSanPham.isTrangCuoi());
        loadMoreScroll.setDuongDan(trangSanPham.getNextPage());
        recyclerSanPham.addOnScrollListener(loadMoreScroll);
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
        loadMoreScroll.setTrangCuoi(trangSanPham.isTrangCuoi());
        loadMoreScroll.setDuongDan(trangSanPham.getNextPage());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == TrangChuActivity.REQUEST_CHITIETSANPHAM){
                soSP = data.getIntExtra("soSP", 0);
                if(soSP == 1){
                    tvSoSPGioHang.setVisibility(View.VISIBLE);
                }
                tvSoSPGioHang.setText(String.valueOf(soSP));
            }else if(requestCode == TrangChuActivity.REQUEST_GIOHANG){
                soSP = data.getIntExtra("soSP", 0);
                if(soSP == 0){
                    tvSoSPGioHang.setVisibility(View.GONE);
                }
                tvSoSPGioHang.setText(String.valueOf(soSP));
            }
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("soSP", soSP);
        setResult(RESULT_OK, data);
        super.finish();
    }


    private String getDuongDan(){
        String dd = duongDan + sapXep + maThuongHieu + soSaoTB;
        return dd;
    }

    @Override
    public void sapXep(String sapXep) {
        if(!sapXep.equals("")){
            this.sapXep = "&sort=" + sapXep;
            presenterLogicSanPham.layDanhSachSanPham(getDuongDan());
        }
    }

    @Override
    public void locSanPham(int idThuongHieu, int giaThap, int giaCao, float danhGia) {
        Toast.makeText(this, "Lọc sẩn phẩm", Toast.LENGTH_SHORT).show();
        if(idThuongHieu > 0){
            this.maThuongHieu = "&provider_id=" + idThuongHieu;
        }
        if(danhGia > 0){
            soSaoTB = "&evaluation=" + danhGia;
        }
        bottomSheetLocSanPham.dismiss();
        presenterLogicSanPham.layDanhSachSanPham(getDuongDan());
    }
}
