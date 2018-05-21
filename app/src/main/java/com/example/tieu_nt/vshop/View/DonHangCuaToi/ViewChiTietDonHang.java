package com.example.tieu_nt.vshop.View.DonHangCuaToi;

import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public interface ViewChiTietDonHang {
    void hienThiDSSanPhamDonHang(List<SanPham> dsSanPham);
    void huyDonHang(int idDonHang);
}
