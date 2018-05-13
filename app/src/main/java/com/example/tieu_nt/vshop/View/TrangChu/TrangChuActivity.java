package com.example.tieu_nt.vshop.View.TrangChu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.Adapter.AdapterSanPham;
import com.example.tieu_nt.vshop.Adapter.AdapterThuongHieu;
import com.example.tieu_nt.vshop.Model.KhachHang;
import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.tieu_nt.vshop.Presenter.TrangChu.PresenterLogicThuongHieu;
import com.example.tieu_nt.vshop.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 3/15/2018.
 */

public class TrangChuActivity extends AppCompatActivity implements View.OnClickListener, ViewHienThiDanhSachThuongHieu,
ViewHienThiDanhSachSanPham{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ToggleButton tgLayout;
    private Button btnSapXep, btnLoc;
    private RecyclerView recyclerView, recyclerThuongHieu, recyclerSanPham;
    private AdapterMenu adapterMenu;
    private CircleImageView imgInfo;
    private ModelKhachHang modelKhachHang;
    private boolean grid = true;
    private List<SanPham> dsSanPham;
    private PresenterLogicThuongHieu presenterLogicThuongHieu;
    private PresenterLogicSanPham presenterLogicSanPham;

    public static KhachHang khachHang;
    public static int IMG_GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        anhXa();
        modelKhachHang = ModelKhachHang.getInstance();
        int idKhachHang = getIntent().getIntExtra("idKhachHang", 0);
        if(idKhachHang != 0)
            khachHang = modelKhachHang.layThongTinKhachHang(idKhachHang);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


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
                Intent iChiTietSP = new Intent(TrangChuActivity.this, ChiTietSanPhamActivity.class);
                startActivity(iChiTietSP);
                break;
            case R.id.btnLoc:
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
                break;
        }
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
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        int layout;
        if(grid){
            layout = R.layout.custom_layout_sanpham;
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerSanPham.setLayoutManager(layoutManager);
        }else{
            layout = R.layout.custom_layout_sanpham_list;
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerSanPham.setLayoutManager(layoutManager);

        }
        AdapterSanPham adapterSanPham = new AdapterSanPham(this,  dsSanPham, layout);
        recyclerSanPham.setAdapter(adapterSanPham);
        adapterSanPham.notifyDataSetChanged();
    }
}
