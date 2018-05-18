package com.example.tieu_nt.vshop.View.TrangChu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Adapter.AdapterThuongHieu;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.KhachHang;
import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.Presenter.TrangChu.PresenterLogicThuongHieu;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.MainActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 3/15/2018.
 */

public class TrangChuActivity extends MainActivity implements View.OnClickListener, ViewHienThiDanhSachThuongHieu,
ViewHienThiDanhSachSanPham, ILoadMore{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ToggleButton tgLayout;
    private Button btnSapXep, btnLoc;
    private TextView tvSoSPGioHang;
    private RecyclerView recyclerView, recyclerThuongHieu, recyclerSanPham;
    private AdapterMenu adapterMenu;
    private CircleImageView imgInfo;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private AdapterSanPham adapterSanPham;
    private ModelKhachHang modelKhachHang;
    private Menu menu;
    private boolean grid = true;
    private List<SanPham> dsSanPham;
    private TrangSanPham trangSanPham;
    private PresenterLogicThuongHieu presenterLogicThuongHieu;
    private PresenterLogicSanPham presenterLogicSanPham;
    private ImageView[] imgSapXep = new ImageView[5];
    private RelativeLayout[] relaSapXep = new RelativeLayout[5];
    private int viTriSapXep = -1;
    private final String SAPXEP_SPMOI = "idSanPham", SAPXEP_GIA = "giaChuan", SAPXEP_YEUTHICH = "soLuotThich",
            SAPXEP_DANHGIA = "soDanhGia", SAPXEP_GIAM = "DESC", SAPXEP_TANG = "ASC";

    private String sapXep = "", giaTri = "";

    public static KhachHang khachHang;
    public static int IMG_GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        anhXa();
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        modelKhachHang = ModelKhachHang.getInstance();
        int idKhachHang = getIntent().getIntExtra("idKhachHang", 0);
        if(idKhachHang != 0)
            khachHang = modelKhachHang.layThongTinKhachHang(idKhachHang);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");


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
        presenterLogicThuongHieu = new PresenterLogicThuongHieu(this);
        presenterLogicThuongHieu.layDanhSachThuongHieu("");

        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("http://172.20.10.7:8080/product_type/1/products");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        this.menu = menu;
        MenuItem iGioHang = menu.findItem(R.id.itemGioHang);
        View itemGioHang = MenuItemCompat.getActionView(iGioHang);
        tvSoSPGioHang = (TextView) itemGioHang.findViewById(R.id.tvSoSPGioHang);

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
        recyclerSanPham = (RecyclerView) findViewById(R.id.recyclerSanPham);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
        btnLoc = (Button) findViewById(R.id.btnLoc);
    }

    private void setActions(){
        imgInfo.setOnClickListener(this);
        btnSapXep.setOnClickListener(this);
        btnLoc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgInfo:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, IMG_GALLERY_REQUEST);
                break;
            case R.id.btnSapXep:
                sapXep();
                break;
            case R.id.btnLoc:
                Toast.makeText(this, "Lọc", Toast.LENGTH_SHORT);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == IMG_GALLERY_REQUEST){
                Uri uri = data.getData();
                imgInfo.setImageURI(uri);
            }
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
        recyclerSanPham.addOnScrollListener(new LoadMoreScroll(layoutManager, this, this.trangSanPham.isTrangCuoi(), this.trangSanPham.getNextPage()));
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
