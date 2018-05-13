package com.example.tieu_nt.vshop.View.CaiDat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class DiaChiActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private EditText edtTinhTP, edtQuanHuyen, edtXaPhuong, edtXom;
    private Button btnCapNhatDiaChi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diachimoi);
        anhXa();

        setSupportActionBar(toolbar);
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtTinhTP  = (EditText) findViewById(R.id.edtTinhTP);
        edtQuanHuyen  = (EditText) findViewById(R.id.edtQuanHuyen);
        edtXaPhuong  = (EditText) findViewById(R.id.edtXaPhuong);
        edtXom  = (EditText) findViewById(R.id.edtXom);
        btnCapNhatDiaChi = (Button) findViewById(R.id.btnCapNhatDiaChi);
    }
}
