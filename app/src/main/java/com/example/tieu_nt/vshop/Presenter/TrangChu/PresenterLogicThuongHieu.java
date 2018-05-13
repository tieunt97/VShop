package com.example.tieu_nt.vshop.Presenter.TrangChu;

import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiDanhSachThuongHieu;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterLogicThuongHieu implements IPresenterThuongHieu{
    private ViewHienThiDanhSachThuongHieu viewTrangChu;
    private ModelSanPham modelSanPham;

    public PresenterLogicThuongHieu(ViewHienThiDanhSachThuongHieu viewTrangChu) {
        this.viewTrangChu = viewTrangChu;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachThuongHieu(String duongDan) {

    }

}
