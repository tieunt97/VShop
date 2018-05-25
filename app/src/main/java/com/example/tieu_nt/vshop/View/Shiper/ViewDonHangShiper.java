package com.example.tieu_nt.vshop.View.Shiper;

import com.example.tieu_nt.vshop.Model.DonHang;

import java.util.ArrayList;
import java.util.List;

public interface ViewDonHangShiper {
    void hienThiDsNhanDonHang(ArrayList<DonHang> dsDonHang);
    void hienThiDsDonHangGiao(ArrayList<DonHang> dsDonHang);
    void hienThiDsLichSuGiaoHang(ArrayList<DonHang> dsDonHang);
    void nhanHangThanhCong();
    void nhanHangThatBai();
}
