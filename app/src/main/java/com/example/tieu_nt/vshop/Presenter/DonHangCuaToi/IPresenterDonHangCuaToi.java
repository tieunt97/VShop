package com.example.tieu_nt.vshop.Presenter.DonHangCuaToi;

import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDonHang;

import java.util.List;

/**
 * Created by tieu_nt on 5/15/2018.
 */

public interface IPresenterDonHangCuaToi {
    void layDanhSachDonHang(String duongDan);
    TrangDonHang layDanhSachDonHangLoadMore(String duongDan);
}
