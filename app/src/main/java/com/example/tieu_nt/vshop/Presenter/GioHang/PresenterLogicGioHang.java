package com.example.tieu_nt.vshop.Presenter.GioHang;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.tieu_nt.vshop.Model.Data.ModelGioHang;
import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiSanPhamGioHang;

import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class PresenterLogicGioHang implements IPresenterGioHang {
    private ModelGioHang modelGioHang;
    private ViewHienThiSanPhamGioHang viewHienThiSanPhamGioHang;

    public PresenterLogicGioHang(Context context, ViewHienThiSanPhamGioHang viewHienThiSanPhamGioHang) {
        this.viewHienThiSanPhamGioHang = viewHienThiSanPhamGioHang;
        this.modelGioHang = ModelGioHang.getInstance();
        this.modelGioHang.ketNoiSQLite(context);
    }

    public PresenterLogicGioHang(Context context) {
        this.modelGioHang = ModelGioHang.getInstance();
        this.modelGioHang.ketNoiSQLite(context);
    }

    public Bitmap layHinhSanPham(String linkHinh){
        return modelGioHang.layHinhSanPham(linkHinh);
    }

    @Override
    public boolean themSanPhamGioHang(SanPham sanPham) {
        return modelGioHang.themSanPhamGioHang(sanPham);
    }

    @Override
    public boolean xoaSanPhamGioHang(int idSanPham) {
        return modelGioHang.xoaSanPhamTrongGioHang(idSanPham);
    }

    public boolean xoaSanPhamGioHang(){
        return modelGioHang.xoaSanPhamTrongGioHang();
    }

    @Override
    public boolean capNhatSoLuongSPGioHang(int idSanPham, int soLuong, int soLuongTonKho) {
        return modelGioHang.capNhatSoLuongSanPhamGioHang(idSanPham, soLuong, soLuongTonKho);
    }

    @Override
    public List<SanPham> layDSSanPhamGioHang() {
        List<SanPham> dsSanPham = modelGioHang.layDSSanPhamGioHang();
        if(viewHienThiSanPhamGioHang != null) viewHienThiSanPhamGioHang.hienThiSanPhamGioHang(dsSanPham);

        return dsSanPham;
    }
}
