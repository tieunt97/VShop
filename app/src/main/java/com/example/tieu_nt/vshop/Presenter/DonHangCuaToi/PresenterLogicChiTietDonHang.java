package com.example.tieu_nt.vshop.Presenter.DonHangCuaToi;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.ViewChiTietDonHang;

import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class PresenterLogicChiTietDonHang implements IPresenterChiTietDonHang {
    private ViewChiTietDonHang viewChiTietDonHang;
    private ModelKhachHang modelKhachHang;


    public PresenterLogicChiTietDonHang(ViewChiTietDonHang viewChiTietDonHang) {
        this.viewChiTietDonHang = viewChiTietDonHang;
        this.modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDSSanPhamDonHang(int idDonHang) {
        List<SanPham> dsSanPham = modelKhachHang.layDanhSachSanPhamDonHang(idDonHang);
        viewChiTietDonHang.hienThiDSSanPhamDonHang(dsSanPham);
    }

    @Override
    public boolean huyDonHang(int idDonHang) {
        return modelKhachHang.huyDonHang(idDonHang);
    }
}
