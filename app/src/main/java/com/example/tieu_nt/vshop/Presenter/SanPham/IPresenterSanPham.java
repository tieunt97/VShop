package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public interface IPresenterSanPham {
    void layDanhSachSanPham(String dươngDan);
    List<SanPham> layDanhSachSanPhamLoadMore(String duongDan);
}
