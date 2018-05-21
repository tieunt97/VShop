package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public interface IPresenterSanPham {
    void layDanhSachSanPham(String dươngDan);
    TrangSanPham layDanhSachSanPhamLoadMore(String duongDan);
}
