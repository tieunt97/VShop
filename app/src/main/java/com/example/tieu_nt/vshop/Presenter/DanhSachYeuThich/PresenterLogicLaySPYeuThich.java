package com.example.tieu_nt.vshop.Presenter.DanhSachYeuThich;

import android.util.Log;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.DanhSachYeuThich.ViewHienThiSanPhamYeuThich;

import java.util.List;

/**
 * Created by tieu_nt on 5/25/2018.
 */

public class PresenterLogicLaySPYeuThich {
    private ViewHienThiSanPhamYeuThich viewHienThiSanPhamYeuThich;
    private ModelKhachHang modelKhachHang;


    public PresenterLogicLaySPYeuThich(ViewHienThiSanPhamYeuThich viewHienThiSanPhamYeuThich) {
        this.viewHienThiSanPhamYeuThich = viewHienThiSanPhamYeuThich;
        modelKhachHang = ModelKhachHang.getInstance();
    }

    public void layDSSanPhamYT(String duongDan){
        List<SanPham> dsSanPham = modelKhachHang.layDanhSachSanPhamYeuThich(duongDan);
        if(dsSanPham.size() > 0){
            viewHienThiSanPhamYeuThich.hienThiSanPhamYeuThich(dsSanPham);
        }
    }
}
