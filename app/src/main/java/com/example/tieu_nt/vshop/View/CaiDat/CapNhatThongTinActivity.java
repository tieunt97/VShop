package com.example.tieu_nt.vshop.View.CaiDat;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class CapNhatThongTinActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private EditText edtDiaChi, edtSoDT;
    private Button btnCapNhat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhatthongtin);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSoDT = (EditText) findViewById(R.id.edtSoDT);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
    }
}
