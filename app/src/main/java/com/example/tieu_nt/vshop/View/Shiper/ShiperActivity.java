package com.example.tieu_nt.vshop.View.Shiper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.example.tieu_nt.vshop.Adapter.AdapterDonHang;
import com.example.tieu_nt.vshop.R;

import java.util.ArrayList;

public class ShiperActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private LinearLayout shiper;
    private DrawerLayout drawerLayout;
    private TabHost tabHost;

    private RecyclerView recyclerTatCa, recyclerDangCho;
    private NavigationView navigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shiper_layout);
        initView();

        //set navigation
        setupNavigation();

        //set tab host
        setupTabhost();

        //setrecycler view
        setRecycleView();
    }

    private void setRecycleView() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1");

        //set tat ca don hang
        AdapterDonHang adapterDonHang = new AdapterDonHang(this, arrayList);
        adapterDonHang.notifyDataSetChanged();
        recyclerTatCa.setAdapter(adapterDonHang);
        recyclerTatCa.setLayoutManager(new LinearLayoutManager(this));

        //set cho don hang dang cho
        AdapterDonHang adapterTatCa = new AdapterDonHang(this, arrayList);
        adapterDonHang.notifyDataSetChanged();
        recyclerDangCho.setAdapter(adapterTatCa);
        recyclerDangCho.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupTabhost() {
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Tất cả");
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab 2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Đang chờ");
        tabHost.addTab(spec2);
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
        recyclerTatCa = findViewById(R.id.recycleTatCa);
        recyclerDangCho = findViewById(R.id.recycleDangCho);
        recyclerTatCa = findViewById(R.id.recycleTatCa);
        recyclerDangCho = findViewById(R.id.recycleDangCho);
        navigationView = findViewById(R.id.navigation);
    }
}
