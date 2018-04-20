package com.example.tieu_nt.vshop.View.TrangChu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.tieu_nt.vshop.Model.DrawerItem;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.Adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 3/15/2018.
 */

public class TrangChuActivity extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout trangChu, frameLoc;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ToggleButton tgLayout;
    private Button btnSapXep;
    private RecyclerView recyclerView, recyclerThuongHieu, recyclerSanPham;
    private AdapterMenu recyclerViewAdapter;
    private CircleImageView imgInfo;
    private String items[] = {"Trang chủ", "Tin tức", "Danh sách yêu thích","Giỏ hàng", "Cài đặt",
            "Trung tâm hỗ trợ", "Giới thiệu VShop", "Đăng xuất"};
    private int[] hinh = {R.drawable.home, R.drawable.newspaper, R.drawable.like, R.drawable.shopping_cart,
            R.drawable.settings, R.drawable.mail, R.drawable.info, R.drawable.logout};
    private List<DrawerItem> dsItems = new ArrayList<>();
    public static int IMG_GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        anhXa();

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

        for(int i = 0; i < items.length; i++){
            dsItems.add(new DrawerItem(hinh[i], items[i]));
        }
        recyclerViewAdapter = new AdapterMenu(TrangChuActivity.this, dsItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);

        setActions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        return true;
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        frameLoc = (FrameLayout) findViewById(R.id.frameLoc);
        tgLayout = (ToggleButton) findViewById(R.id.tgLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerThuongHieu = (RecyclerView) findViewById(R.id.recyclerThuongHieu);
        recyclerSanPham = (RecyclerView) findViewById(R.id.recyclerSanPham);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
    }

    private void setActions(){
        imgInfo.setOnClickListener(this);
        btnSapXep.setOnClickListener(this);
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
                Toast.makeText(this, "BLABLA", Toast.LENGTH_SHORT).show();
                Intent iChiTietSP = new Intent(TrangChuActivity.this, ChiTietSanPhamActivity.class);
                startActivity(iChiTietSP);
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
}
