package com.example.tieu_nt.vshop.Presenter.TinTuc;

import com.example.tieu_nt.vshop.Model.TinTuc;

import java.util.List;

/**
 * Created by tieu_nt on 4/26/2018.
 */

public interface IPresenterTinTuc {
    void layDanhSachTinTuc(String duongDan);
    List<TinTuc> layDanhSachTinTucLoadMore(String duongDan);
}
