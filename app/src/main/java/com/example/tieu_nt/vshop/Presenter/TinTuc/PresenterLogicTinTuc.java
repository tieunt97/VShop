package com.example.tieu_nt.vshop.Presenter.TinTuc;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.TinTuc;

import java.util.List;

/**
 * Created by tieu_nt on 4/26/2018.
 */

public class PresenterLogicTinTuc implements IPresenterTinTuc{
    private ViewHienThiDanhSachTinTuc viewHienThiDanhSachTinTuc;
    private ModelKhachHang modelKhachHang;

    public PresenterLogicTinTuc(ViewHienThiDanhSachTinTuc viewHienThiDanhSachTinTuc) {
        this.viewHienThiDanhSachTinTuc = viewHienThiDanhSachTinTuc;
        modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDanhSachTinTuc(String duongDan) {
        List<TinTuc> dsTinTuc = modelKhachHang.layDanhSachTinTuc(duongDan);
        if (dsTinTuc.size() > 0){
            viewHienThiDanhSachTinTuc.hienThiDanhSachTinTuc(dsTinTuc);
        }
    }

    @Override
    public List<TinTuc> layDanhSachTinTucLoadMore(String duongDan) {
        List<TinTuc> dsTinTuc = modelKhachHang.layDanhSachTinTuc(duongDan);

        return dsTinTuc;
    }
}
