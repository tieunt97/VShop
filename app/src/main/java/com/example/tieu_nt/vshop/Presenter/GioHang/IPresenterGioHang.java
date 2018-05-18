package com.example.tieu_nt.vshop.Presenter.GioHang;

import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public interface IPresenterGioHang {
    boolean themSanPhamGioHang(SanPham sanPham);
    boolean xoaSanPhamGioHang(int idSanPham);
    boolean capNhatSoLuongSPGioHang(int idSanPham, int soLuong, int soLuongTonKho);
    List<SanPham> layDSSanPhamGioHang();
}
