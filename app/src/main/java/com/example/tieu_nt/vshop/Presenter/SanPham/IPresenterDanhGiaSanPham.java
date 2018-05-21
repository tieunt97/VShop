package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDanhGia;

import java.util.List;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public interface IPresenterDanhGiaSanPham {
    void layDanhSachDanhGia(String duongDan);
    TrangDanhGia layDSDanhGiaLoadMore(String duongDan);
    boolean danhGiaSanPham(DanhGia danhGia);
}
