package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDanhGia;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiDanhSachDanhGia;

import java.util.List;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public class PresenterLogicDanhGiaSanPham implements IPresenterDanhGiaSanPham{
    private ViewHienThiDanhSachDanhGia viewHienThiDanhSachDanhGia;
    private ModelSanPham modelSanPham;

    public PresenterLogicDanhGiaSanPham(ViewHienThiDanhSachDanhGia viewHienThiDanhSachDanhGia) {
        this.viewHienThiDanhSachDanhGia = viewHienThiDanhSachDanhGia;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachDanhGia(String duongDan) {
        TrangDanhGia trangDanhGia = modelSanPham.layDanhSachDanhGia(duongDan);
        if (trangDanhGia.getDsDanhGia().size() > 0){
            viewHienThiDanhSachDanhGia.hienThiDanhSachDanhGia(trangDanhGia);
        }
    }

    @Override
    public TrangDanhGia layDSDanhGiaLoadMore(String duongDan) {
        TrangDanhGia trangDanhGia = modelSanPham.layDanhSachDanhGia(duongDan);

        return trangDanhGia;
    }
}
