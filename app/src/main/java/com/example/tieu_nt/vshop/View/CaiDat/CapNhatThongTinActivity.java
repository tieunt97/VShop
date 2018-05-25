package com.example.tieu_nt.vshop.View.CaiDat;

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

import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Presenter.CaiDat.PresenteLogicCapNhatThongTin;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.DangNhap.DangNhapActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class CapNhatThongTinActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private EditText edtDiaChi, edtSoDT, edtHoTen, edtEmail;
    private Button btnCapNhat;
    private NguoiDung nguoiDung;
    private PresenteLogicCapNhatThongTin presenteLogicCapNhatThongTin;

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

        nguoiDung = TrangChuActivity.nguoiDung;
        presenteLogicCapNhatThongTin = new PresenteLogicCapNhatThongTin();
        hienThiThongTin();
        setAction();
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
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSoDT = (EditText) findViewById(R.id.edtSoDT);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
    }

    private void hienThiThongTin(){
        edtHoTen.setText(nguoiDung.getTenNguoiDung());
        edtEmail.setText(nguoiDung.getEmail());
        if(!nguoiDung.getSoDT().equals("null"))
            edtSoDT.setText(nguoiDung.getSoDT());
        if(!nguoiDung.getDiaChi().equals("null"))
            edtDiaChi.setText(nguoiDung.getDiaChi());
    }

    private void setAction(){
        btnCapNhat.setOnClickListener(this);
    }

    private boolean kiemTraDuLieu(String hoTen, String email, String soDT, String diaChi){
        if(hoTen.equals("") || email.equals("") || soDT.equals("") || diaChi.equals(""))
            return false;
        else
            return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCapNhat:
                String hoTen = edtHoTen.getText().toString();
                String email = edtHoTen.getText().toString();
                String soDT = edtSoDT.getText().toString();
                String diaChi = edtDiaChi.getText().toString();

                if (!kiemTraDuLieu(hoTen, email, soDT, diaChi)){
                    Toast.makeText(this, "Có dữ liệu trống", Toast.LENGTH_SHORT).show();
                }else if(!DangNhapActivity.validate(edtEmail.getText().toString())){
                    Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }else{
                    if(presenteLogicCapNhatThongTin.capNhatThongTin(hoTen, email, soDT, diaChi)){
                        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Email này đã được đăng ký, cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
