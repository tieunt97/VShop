package com.example.tieu_nt.vshop.Presenter.GioHang;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/25/2018.
 */

public class PresenterMuaHang {
    private ModelSanPham modelSanPham;
    private ModelKhachHang modelKhachHang;

    public PresenterMuaHang() {
        this.modelSanPham = ModelSanPham.getInstance();
        modelKhachHang = ModelKhachHang.getInstance();
    }

    public int laySoLuongSanPhamTrongKho(int idSanPham){
        return modelSanPham.laySoLuongTrongKho(idSanPham);
    }

    public boolean muaHang(String token, DonHang donHang){
        return modelKhachHang.xacNhanMuaHang(token, donHang);
    }
}
