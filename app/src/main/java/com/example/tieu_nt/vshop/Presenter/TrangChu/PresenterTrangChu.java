package com.example.tieu_nt.vshop.Presenter.TrangChu;

import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewTrangChu;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterTrangChu implements IPresenterTrangChu{
    private ViewTrangChu viewTrangChu;
    private ModelSanPham modelSanPham;

    public PresenterTrangChu(ViewTrangChu viewTrangChu) {
        this.viewTrangChu = viewTrangChu;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachThuongHieu() {

    }

    @Override
    public void layDanhSachSanPham() {

    }
}
