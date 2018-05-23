package com.example.tieu_nt.vshop.View.TrangChu;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/23/2018.
 */

public class XacNhanMuaHangActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private EditText edtHoTen, edtDiaChi, edtSoDT;
    private Button btnXacNhan;
    private DonHang donHang;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xacnhanmuahang);
        anhXa();

        donHang = new DonHang();
        donHang.setPhiShip(getIntent().getIntExtra("phiShip", 0));
        donHang.setTrangThai(DonHang.TRANGTHAI_DADATHANG);
        donHang.setKhachHang(TrangChuActivity.nguoiDung);
        donHang.setDiaChi(TrangChuActivity.nguoiDung.getDiaChi());

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        setThongTin();
    }

    private void setThongTin(){
        edtHoTen.setText(donHang.getKhachHang().getTenNguoiDung());
        if(!donHang.getDiaChi().equals("null")){
            edtDiaChi.setText(donHang.getDiaChi());
        }
        if(!donHang.getKhachHang().getSoDT().equals("null")){
            edtSoDT.setText(donHang.getKhachHang().getSoDT());
        }
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
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSoDT = (EditText) findViewById(R.id.edtSoDT);
        btnXacNhan = (Button) findViewById(R.id.btnXacNhan);
    }
}
