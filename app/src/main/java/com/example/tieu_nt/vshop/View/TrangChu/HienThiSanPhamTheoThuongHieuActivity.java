package com.example.tieu_nt.vshop.View.TrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 4/20/2018.
 */

public class HienThiSanPhamTheoThuongHieuActivity extends AppCompatActivity{
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheothuonghieu);

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        return true;
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
