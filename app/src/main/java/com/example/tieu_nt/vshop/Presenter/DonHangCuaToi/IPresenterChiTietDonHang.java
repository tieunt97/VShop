package com.example.tieu_nt.vshop.Presenter.DonHangCuaToi;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public interface IPresenterChiTietDonHang {
    void layDSSanPhamDonHang(int idDonHang);
    boolean xoaSanPhamDonHang(int idDonHang, int idSanPham);
    boolean capNhatSanPhamDonHang(int idDonHang, int idSanPham, boolean them);
}
