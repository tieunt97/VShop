package com.example.tieu_nt.vshop.View.TrangChu;


import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public interface ViewChiTietSanPham {
    void hienThiSliderSP(List<String> dsHinhSP);
    void hienThiChiTietSanPham(SanPham sanPham);
    void hienThiDSDanhGia(List<DanhGia> dsDanhGia);
}
