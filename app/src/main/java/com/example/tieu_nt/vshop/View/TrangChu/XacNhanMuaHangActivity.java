package com.example.tieu_nt.vshop.View.TrangChu;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Model.DangNhap;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterMuaHang;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 5/23/2018.
 */

public class XacNhanMuaHangActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private EditText edtHoTen, edtDiaChi;
    private Button btnXacNhan;
    private DonHang donHang;
    private List<SanPham> dsSanPham;
    private PresenterLogicGioHang presenterLogicGioHang;
    private PresenterMuaHang presenterMuaHang;
    private int soSP;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xacnhanmuahang);
        anhXa();

        donHang = new DonHang();
        donHang.setPhiShip(getIntent().getIntExtra("phiShip", 0));
        donHang.setTrangThai(DonHang.TRANGTHAI_DADATHANG);
        donHang.setKhachHang(DangNhap.getInstance().getNguoiDung());
        donHang.setDiaChi(DangNhap.getInstance().getNguoiDung().getDiaChi());

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        presenterMuaHang = new PresenterMuaHang();

        presenterLogicGioHang = new PresenterLogicGioHang(this);
        dsSanPham = presenterLogicGioHang.layDSSanPhamGioHang();
        for(SanPham sp: dsSanPham){
            soSP += sp.getSoLuong();
        }
        setThongTin();

        setActions();
    }

    private void setActions(){
        btnXacNhan.setOnClickListener(this);
    }

    private void setThongTin(){
        edtHoTen.setText(donHang.getKhachHang().getTenNguoiDung());
        edtHoTen.setEnabled(false);
        if(!donHang.getDiaChi().equals("null")){
            edtDiaChi.setText(donHang.getDiaChi());
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
        btnXacNhan = (Button) findViewById(R.id.btnXacNhan);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnXacNhan:
                if(!edtDiaChi.getText().toString().equals(""))
                    muaHang();
                else{
                    Toast.makeText(this, "Địa chỉ trống", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void muaHang(){
        for(int i = 0; i < dsSanPham.size(); i++){
            SanPham sp = dsSanPham.get(i);
            final int soLuongKho = presenterMuaHang.laySoLuongSanPhamTrongKho(sp.getIdSanPham());
            if(sp.getSoLuong() > soLuongKho){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view1 = getLayoutInflater().inflate(R.layout.dialog_thongbao_xacnhan, null, false);
                TextView tvNoiDung = (TextView) view1.findViewById(R.id.tvNoiDung);
                tvNoiDung.setText("Số lượng sản phẩm không đủ, bạn có muốn lấy tất sản phẩm còn lại");
                Button btnDongY = (Button) view1.findViewById(R.id.btnDongY);
                btnDongY.setText("Có mua");

                Button btnHuy = view1.findViewById(R.id.btnHuy);

                builder.setView(view1);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final int finalI = i;
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        donHang.getDsSanPham().remove(finalI);
                        alertDialog.dismiss();
                    }
                });

                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dsSanPham.get(finalI).setSoLuong(soLuongKho);
                        alertDialog.dismiss();
                    }
                });
            }
        }

        donHang.setDsSanPham((ArrayList<SanPham>) dsSanPham);
        donHang.setDiaChi(edtDiaChi.getText().toString());
        if(presenterLogicGioHang.xoaSanPhamGioHang()){
            soSP = 0;
        }
        if(presenterMuaHang.muaHang(DangNhap.getInstance().getNguoiDung().getToken(), donHang)){
            Toast.makeText(XacNhanMuaHangActivity.this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("soSP", soSP);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
