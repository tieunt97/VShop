package com.example.tieu_nt.vshop.View.DangNhap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.tieu_nt.vshop.Adapter.ViewPagerAdapterDangNhap;
import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class DangNhapActivity extends AppCompatActivity{
    private TabLayout tabLayout;
    private ViewPager  viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        anhXa();

        setSupportActionBar(toolbar);

        ViewPagerAdapterDangNhap adapter = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);

    }

    private void anhXa(){
        tabLayout = (TabLayout) findViewById(R.id.tabDangNhap);
        viewPager =(ViewPager) findViewById(R.id.viewPagerDangNhap);
        toolbar = (Toolbar) findViewById(R.id.toolbarDangNhap);
    }
}
