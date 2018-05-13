package com.example.tieu_nt.vshop.View.CaiDat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class DoiMatKhauActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private EditText edtMatKhauCu, edtMatKhauMoi, edtXacNhanMKMoi;
    private Button btnCapNhat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_doimatkhau);
        anhXa();

        setSupportActionBar(toolbar);
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtMatKhauCu = (EditText) findViewById(R.id.edtMatKhauCu);
        edtMatKhauMoi = (EditText) findViewById(R.id.edtMatKhauMoi);
        edtXacNhanMKMoi = (EditText) findViewById(R.id.edtXacNhanMKMoi);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
    }
}
