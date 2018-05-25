package com.example.tieu_nt.vshop.View.TrangChu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Adapter.AdapterThuongHieu;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.Presenter.TrangChu.PresenterLogicThuongHieu;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.BottomSheetLocSanPham;
import com.example.tieu_nt.vshop.View.DialogSapXep;
import com.example.tieu_nt.vshop.View.DialogTimKiem;
import com.example.tieu_nt.vshop.View.LocSanPham;
import com.example.tieu_nt.vshop.View.MainActivity;
import com.example.tieu_nt.vshop.View.SapXepSanPham;
import com.example.tieu_nt.vshop.View.TimKiemSanPham;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 3/15/2018.
 */

public class TrangChuActivity extends MainActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, ViewHienThiDanhSachThuongHieu,
ViewHienThiDanhSachSanPham, ILoadMore, SapXepSanPham, TimKiemSanPham, LocSanPham{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ToggleButton tgLayout;
    private Button btnSapXep, btnLoc;
    private TextView tvSoSPGioHang, tvHoTen;
    private RecyclerView recyclerView, recyclerThuongHieu, recyclerSanPham;
    private AdapterMenu adapterMenu;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private AdapterSanPham adapterSanPham;
    private ModelKhachHang modelKhachHang;
    private Menu menu;
    private BottomSheetLocSanPham bottomSheetLocSanPham;
    private boolean grid = true;
    private List<SanPham> dsSanPham;
    private TrangSanPham trangSanPham;
    private PresenterLogicThuongHieu presenterLogicThuongHieu;
    private PresenterLogicSanPham presenterLogicSanPham;
    private PresenterLogicGioHang presenterLogicGioHang;
    public static final int REQUEST_CHITIETSANPHAM = 2, REQUEST_GIOHANG = 3, REQUEST_THUONGHIEU = 4;

    public static NguoiDung nguoiDung;
    public static final String SERVER = "http://192.168.1.76/VShop/shop-mobile/public";
//    public static final String SERVER = "http://10.0.3.2:8080/VShop/shop-mobile/public";
    public static final String API_DANGNHAP = SERVER + "/login";
    public static final String API_DANGKY = SERVER + "/register";
    public static final String API_DANGXUAT = SERVER + "/logout";
    public static final String API_THUONGHIEU = SERVER + "/providers";
    private String duongDan = SERVER + "/sort&filter/products?product_type_id=1";
    private String sapXep = "", maThuongHieu = "", soSaoTB = "";
    private LoadMoreScroll loadMoreScroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        anhXa();
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        modelKhachHang = ModelKhachHang.getInstance();
        nguoiDung = (NguoiDung) getIntent().getSerializableExtra("nguoiDung");

        if(nguoiDung != null){
            tvHoTen.setText(nguoiDung.getTenNguoiDung());
        }else{
            tvHoTen.setText("Bạn chưa đăng nhập");
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        bottomSheetLocSanPham = new BottomSheetLocSanPham();

        drawerToggle = new ActionBarDrawerToggle(TrangChuActivity.this, drawerLayout, R.string.open, R.string.close){
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

        adapterMenu = new AdapterMenu(TrangChuActivity.this, drawerLayout, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

        setActions();
        selectImage();

        presenterLogicGioHang = new PresenterLogicGioHang(this);

        presenterLogicThuongHieu = new PresenterLogicThuongHieu(this);
        presenterLogicThuongHieu.layDanhSachThuongHieu();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        this.menu = menu;
        MenuItem iGioHang = menu.findItem(R.id.itemGioHang);
        View itemGioHang = MenuItemCompat.getActionView(iGioHang);
        tvSoSPGioHang = (TextView) itemGioHang.findViewById(R.id.tvSoSPGioHang);

        List<SanPham> dsSanPhamGioHang = presenterLogicGioHang.layDSSanPhamGioHang();
        if(dsSanPhamGioHang.size() == 0) {
            tvSoSPGioHang.setVisibility(View.GONE);
        }else{
            int soSP = 0;
            for(SanPham sp: dsSanPhamGioHang){
                soSP += sp.getSoLuong();
            }
            tvSoSPGioHang.setVisibility(View.VISIBLE);
            tvSoSPGioHang.setText(String.valueOf(soSP));
        }

        itemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivityForResult(iGioHang, REQUEST_GIOHANG);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemTimKiem:
                DialogTimKiem dialogTimKiem = new DialogTimKiem(this, this);
                dialogTimKiem.show();
                break;
        }
        return true;
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        tgLayout = (ToggleButton) findViewById(R.id.tgLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerThuongHieu = (RecyclerView) findViewById(R.id.recyclerThuongHieu);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerSanPham = (RecyclerView) findViewById(R.id.recyclerSanPham);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        tvHoTen = (TextView) findViewById(R.id.tvHoTen);
    }

    private void setActions(){
        btnSapXep.setOnClickListener(this);
        btnLoc.setOnClickListener(this);
        tgLayout.setOnCheckedChangeListener(this);
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
    public void hienThiThuongHieu(List<ThuongHieu> dsThuongHieu) {
        AdapterThuongHieu adapterThuongHieu = new AdapterThuongHieu(this, dsThuongHieu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerThuongHieu.setLayoutManager(layoutManager);
        recyclerThuongHieu.setAdapter(adapterThuongHieu);
        adapterThuongHieu.notifyDataSetChanged();
    }

    @Override
    public void hienThiDanhSachSanPham(TrangSanPham trangSanPham) {
        this.trangSanPham = trangSanPham;
        this.dsSanPham = trangSanPham.getDsSanPham();
        int layout;
        if(grid){
            layout = R.layout.custom_layout_sanpham;
            layoutManager = gridLayoutManager;
        }else{
            layout = R.layout.custom_layout_sanpham_list;
            layoutManager = linearLayoutManager;
        }
        recyclerSanPham.setLayoutManager(layoutManager);
        adapterSanPham = new AdapterSanPham(this,  dsSanPham, layout);
        recyclerSanPham.setAdapter(adapterSanPham);
        loadMoreScroll = new LoadMoreScroll(layoutManager, this);
        loadMoreScroll.setTrangCuoi(trangSanPham.isTrangCuoi());
        loadMoreScroll.setDuongDan(trangSanPham.getNextPage());
        recyclerSanPham.addOnScrollListener(loadMoreScroll);
        adapterSanPham.notifyDataSetChanged();
    }

    @Override
    public void loadMore(String duongDan) {
        trangSanPham = presenterLogicSanPham.layDanhSachSanPhamLoadMore(duongDan);
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        grid = !b;
        presenterLogicSanPham.layDanhSachSanPham(duongDan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == IMG_GALLERY_REQUEST){
                Uri uri = data.getData();
                imgInfo.setImageURI(uri);
            }else if(requestCode == REQUEST_CHITIETSANPHAM){
                int soSP = data.getIntExtra("soSP", 0);
                if(soSP == 1){
                    tvSoSPGioHang.setVisibility(View.VISIBLE);
                }
                tvSoSPGioHang.setText(String.valueOf(soSP));
            }else if(requestCode == REQUEST_GIOHANG){
                int soSP = data.getIntExtra("soSP", 0);
                if(soSP == 0){
                    tvSoSPGioHang.setVisibility(View.GONE);
                }
                tvSoSPGioHang.setText(String.valueOf(soSP));
            }else if(requestCode == REQUEST_THUONGHIEU){
                int soSP = data.getIntExtra("soSP", 0);
                if(soSP == 0){
                    tvSoSPGioHang.setVisibility(View.GONE);
                }
                tvSoSPGioHang.setText(String.valueOf(soSP));
            }
        }
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
    public void timKiemSanPham(String timKiem) {
        Intent iTimKiem = new Intent(this, SanPhamTimKiemActivity.class);
        iTimKiem.putExtra("timKiem", timKiem);
        startActivity(iTimKiem);
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
