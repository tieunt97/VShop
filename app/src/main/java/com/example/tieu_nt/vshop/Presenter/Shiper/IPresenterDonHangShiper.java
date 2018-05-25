package com.example.tieu_nt.vshop.Presenter.Shiper;

public interface IPresenterDonHangShiper {
    void layDanhSachNhanDonHang(String links);
    void layDanhSachGiaoHang(String links);
    void layDanhSachLichSuGiao(String links);
    void nhanDonHang(String links, int idDonHang);
    void giaoHang(String links, int idDonHang);
}
