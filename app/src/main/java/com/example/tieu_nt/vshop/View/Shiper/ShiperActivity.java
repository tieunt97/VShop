package com.example.tieu_nt.vshop.View.Shiper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Adapter.AdapterNhanDonHang;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Presenter.Shiper.PresenterDonHangShiper;
import com.example.tieu_nt.vshop.R;

import java.util.ArrayList;
import java.util.List;

public class ShiperActivity extends AppCompatActivity implements ViewDonHangShiper{
    private android.support.v7.widget.Toolbar toolbar;
    private LinearLayout shiper;
    private DrawerLayout drawerLayout;
    private TabHost tabHost;
    private SwipeRefreshLayout swiperDangGiao, swiperNhanDon;
    private RecyclerView recyclerDangGiao, recyclerNhanDon, recyclerLichSu;
    private NavigationView navigationView;

    private AdapterNhanDonHang adapterNhanDonHang, adapterGiaoHang, adapterLichSu;
    private PresenterDonHangShiper presenterDonHangShiper;

    public static final String SERVER = "http://10.10.17.228/VShop/shop-mobile/public";
    public static final String ORDER_WAIT = SERVER+"/api/shipper/order/waiting?api_token=b38f26623f4781ef021667636d0a042283e7db70536109cfe8549a3e70159f50";
    public static final String ORDER_SHIP = SERVER+"/api/shipper/order/my_order_list?api_token=b38f26623f4781ef021667636d0a042283e7db70536109cfe8549a3e70159f50";
    public static final String ORDER_HISTORY = SERVER+"/api/shipper/order/my_history_orders?api_token=b38f26623f4781ef021667636d0a042283e7db70536109cfe8549a3e70159f50";
    public static final String ORDER_SHIPED = SERVER+"/api/shipper/order/shipped?api_token=b38f26623f4781ef021667636d0a042283e7db70536109cfe8549a3e70159f50";
    public static final String ORDER_RECIEVE = SERVER+"/api/shipper/order/receive_order?api_token=b38f26623f4781ef021667636d0a042283e7db70536109cfe8549a3e70159f50";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shiper_layout);
        initView();

        presenterDonHangShiper = new PresenterDonHangShiper(this);
        //set navigation
        setupNavigation();
        //set tab host
        setupTabhost();
        //setrecycler view
        setRecycleView();

        presenterDonHangShiper.layDanhSachNhanDonHang(ORDER_WAIT);
        presenterDonHangShiper.layDanhSachGiaoHang(ORDER_SHIP);
        presenterDonHangShiper.layDanhSachLichSuGiao(ORDER_HISTORY);
    }

    private void setRecycleView() {

        //set don hang dang giao
//        AdapterNhanDonHang adapterDonHang = new AdapterNhanDonHang(this, arrayList);
//        adapterDonHang.notifyDataSetChanged();
//        recyclerDangGiao.setAdapter(adapterDonHang);
//        recyclerDangGiao.setLayoutManager(new LinearLayoutManager(this));

        //set nhan don hang
    }

    private void setupTabhost() {
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Giao hàng");
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab 2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Chờ nhận");
        tabHost.addTab(spec2);

        TabHost.TabSpec spec3 = tabHost.newTabSpec("Tab 3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Lịch sử giao");
        tabHost.addTab(spec3);
    }

    private void setupNavigation() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(ShiperActivity.this, drawerLayout, R.string.open, R.string.close);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //set header
        navigationView.inflateHeaderView(R.layout.drawer_header_layout);

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        shiper = findViewById(R.id.shiper);
        drawerLayout = findViewById(R.id.drawerLayout);
        tabHost = findViewById(R.id.tabhost);
        recyclerNhanDon = findViewById(R.id.recycleNhanDon);
        recyclerDangGiao = findViewById(R.id.recycleDangGiao);
        recyclerLichSu = findViewById(R.id.recycleLichSu);
        navigationView = findViewById(R.id.navigation);
    }

    @Override
    public void hienThiDsNhanDonHang(ArrayList<DonHang> dsDonHang) {
        recyclerNhanDon.setLayoutManager(new LinearLayoutManager(this));
        adapterNhanDonHang = new AdapterNhanDonHang(this, dsDonHang, presenterDonHangShiper, 0);
        recyclerNhanDon.setAdapter(adapterNhanDonHang);
        adapterNhanDonHang.notifyDataSetChanged();
    }

    @Override
    public void hienThiDsDonHangGiao(ArrayList<DonHang> dsDonHang) {
        recyclerDangGiao.setLayoutManager(new LinearLayoutManager(this));
        adapterGiaoHang = new AdapterNhanDonHang(this, dsDonHang, presenterDonHangShiper, 1);
        recyclerDangGiao.setAdapter(adapterGiaoHang);
        adapterGiaoHang.notifyDataSetChanged();
    }

    @Override
    public void hienThiDsLichSuGiaoHang(ArrayList<DonHang> dsDonHang) {
        recyclerLichSu.setLayoutManager(new LinearLayoutManager(this));
        adapterLichSu = new AdapterNhanDonHang(this, dsDonHang, presenterDonHangShiper, 2);
        recyclerLichSu.setAdapter(adapterLichSu);
        adapterLichSu.notifyDataSetChanged();
    }

    @Override
    public void nhanHangThanhCong() {
        Toast.makeText(this, "Nhận đơn hàng thành công", Toast.LENGTH_SHORT).show();
        updateRecycleView();
    }

    @Override
    public void nhanHangThatBai() {
        Toast.makeText(this, "Nhận đơn hàng thất bại", Toast.LENGTH_SHORT).show();
    }

    public void updateRecycleView(){
        presenterDonHangShiper.layDanhSachNhanDonHang(ORDER_WAIT);
        presenterDonHangShiper.layDanhSachGiaoHang(ORDER_SHIP);
        presenterDonHangShiper.layDanhSachLichSuGiao(ORDER_HISTORY);
    }
}
