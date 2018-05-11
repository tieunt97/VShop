package com.example.tieu_nt.vshop.View.TrangChu;

import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.ThuongHieu;

import java.util.List;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public interface ViewTrangChu {
    void hienThiThuongHieu(List<ThuongHieu> dsThuongHieu);
    void hienThiSanPham(List<SanPham> dsSanPham);
}
