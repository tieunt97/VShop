package com.example.tieu_nt.vshop.Presenter.TinTuc;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangTinTuc;
import com.example.tieu_nt.vshop.View.TinTuc.ViewHienThiDanhSachTinTuc;

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
        TrangTinTuc trangTinTuc = modelKhachHang.layDanhSachTinTuc(duongDan);
        if (trangTinTuc.getDsTinTuc().size() > 0){
            viewHienThiDanhSachTinTuc.hienThiDanhSachTinTuc(trangTinTuc);
        }
    }

    @Override
    public TrangTinTuc layDanhSachTinTucLoadMore(String duongDan) {
        TrangTinTuc trangTinTuc = modelKhachHang.layDanhSachTinTuc(duongDan);

        return trangTinTuc;
    }
}
