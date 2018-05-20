package com.example.tieu_nt.vshop.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.example.tieu_nt.vshop.View.MainActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 3/15/2018.
 */

public class TrangChuActivity extends MainActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, ViewHienThiDanhSachThuongHieu,
ViewHienThiDanhSachSanPham, ILoadMore{
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
    private ImageView[] imgSapXep = new ImageView[5];
    private RelativeLayout[] relaSapXep = new RelativeLayout[5];
    private int viTriSapXep = -1;
    private final String SAPXEP_SPMOI = "idSanPham", SAPXEP_GIA = "giaChuan", SAPXEP_YEUTHICH = "soLuotThich",
            SAPXEP_DANHGIA = "soDanhGia", SAPXEP_GIAM = "DESC", SAPXEP_TANG = "ASC";

    private String sapXep = "", giaTri = "";

    public static NguoiDung nguoiDung;
    public static final String SERVER = "http://192.168.1.110:8080/VShop/shop-mobile/public";
    public static final String API_DANGNHAP = "http://192.168.1.110:8080/VShop/shop-mobile/public/login";
    public static final String API_DANGKY = "http://192.168.1.110:8080/VShop/shop-mobile/public/register";
    private String duongDan = "http://192.168.1.110:8080/VShop/shop-mobile/public/product_provider/1/products";

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
        presenterLogicThuongHieu.layDanhSachThuongHieu("");

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

        int soSP = presenterLogicGioHang.layDSSanPhamGioHang().size();
        if(soSP == 0) {
            tvSoSPGioHang.setVisibility(View.GONE);
        }else{
            tvSoSPGioHang.setVisibility(View.VISIBLE);
            tvSoSPGioHang.setText(String.valueOf(soSP));
        }

        itemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.itemGioHang:
//                Intent iGioHang = new Intent(this, GioHangActivity.class);
//                startActivity(iGioHang);
//                break;
//        }
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
                sapXep();
                break;
            case R.id.btnLoc:
                bottomSheetLocSanPham.show(getSupportFragmentManager(), "LocSanPham");
                break;
        }
    }

    private void sapXep(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(TrangChuActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_sapxep, null, false);
        relaSapXep[0] = view.findViewById(R.id.relaGiaCaoDenThap);
        relaSapXep[1] = view.findViewById(R.id.relaGiaThapDenCao);
        relaSapXep[2] = view.findViewById(R.id.relaSanPhamMoi);
        relaSapXep[3] = view.findViewById(R.id.relaYeuThichNhat);
        relaSapXep[4] = view.findViewById(R.id.relaDanhGia);

        imgSapXep[0] = view.findViewById(R.id.imgGiaCaoDenThap);
        imgSapXep[1] = view.findViewById(R.id.imgGiaThapDenCao);
        imgSapXep[2] = view.findViewById(R.id.imgSanPhamMoi);
        imgSapXep[3] = view.findViewById(R.id.imgYeuThich);
        imgSapXep[4] = view.findViewById(R.id.imgDanhGia);

        Button btnHuy = (Button) view.findViewById(R.id.btnHuy);
        Button btnApDung = (Button) view.findViewById(R.id.btnApDung);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        relaSapXep[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 0){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 0;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_GIA;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 1){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 1;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_GIA;
                    sapXep = SAPXEP_TANG;
                }
            }
        });

        relaSapXep[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 2){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 2;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_SPMOI;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 3){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 3;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_YEUTHICH;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 4){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 4;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_DANHGIA;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viTriSapXep != -1){
                    viTriSapXep = -1;
                    giaTri = "";
                    sapXep = "";
                }
                alertDialog.dismiss();
            }
        });

        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == -1) alertDialog.dismiss();
                else{
                    alertDialog.dismiss();
                }
            }
        });
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
        recyclerSanPham.addOnScrollListener(new LoadMoreScroll(layoutManager, this, this.trangSanPham.isTrangCuoi(), this.trangSanPham.getNextPage()));
        adapterSanPham.notifyDataSetChanged();
    }

    @Override
    public void loadMore(String duongDan) {
        trangSanPham = presenterLogicSanPham.layDanhSachSanPhamLoadMore(duongDan);
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
}
