package com.example.tieu_nt.vshop.View.DonHangCuaToi;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Adapter.AdapterSanPhamGioHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.DonHangCuaToi.PresenterLogicChiTietDonHang;
import com.example.tieu_nt.vshop.R;

import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class ChiTietDonHangActivity extends AppCompatActivity implements View.OnClickListener, ViewChiTietDonHang{
    private Toolbar toolbar;
    private Button btnHuyDonHang;
    private RecyclerView recyclerDonHang;
    private RecyclerView.LayoutManager layoutManager;
    private PresenterLogicChiTietDonHang presenterLogicChiTietDonHang;
    private AdapterSanPhamGioHang adapterSanPhamGioHang;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietdonhang);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        layoutManager = new LinearLayoutManager(this);
        recyclerDonHang.setLayoutManager(layoutManager);

        presenterLogicChiTietDonHang = new PresenterLogicChiTietDonHang(this);
        presenterLogicChiTietDonHang.layDSSanPhamDonHang(1);
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
        btnHuyDonHang = (Button) findViewById(R.id.btnHuyDonHang);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void hienThiDSSanPhamDonHang(List<SanPham> dsSanPham) {
         adapterSanPhamGioHang = new AdapterSanPhamGioHang(this, dsSanPham, null, null, null);
         recyclerDonHang.setAdapter(adapterSanPhamGioHang);
         adapterSanPhamGioHang.notifyDataSetChanged();
    }

    @Override
    public void huyDonHang(final int idDonHang) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_thongbao_xacnhan, null);
        Button btnHuy = (Button) view1.findViewById(R.id.btnHuy);
        Button btnDongY = (Button) view1.findViewById(R.id.btnDongY);
        btnDongY.setText("Thoát");
        TextView tvNoiDung = (TextView) view1.findViewById(R.id.tvNoiDung);
        tvNoiDung.setText("Bạn có chắc muốn hủy đơn hàng này?");

        builder.setView(view1);
        final AlertDialog dialogCloseApp = builder.create();
        dialogCloseApp.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCloseApp.dismiss();
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCloseApp.dismiss();
                if (presenterLogicChiTietDonHang.huyDonHang(idDonHang)){
                    Toast.makeText(ChiTietDonHangActivity.this, "Hủy đơn hàng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
