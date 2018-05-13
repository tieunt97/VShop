package com.example.tieu_nt.vshop.Presenter.TrangChu;

import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewTrangChu;

import java.util.List;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterLogicTrangChu implements IPresenterTrangChu{
    private ViewTrangChu viewTrangChu;
    private ModelSanPham modelSanPham;

    public PresenterLogicTrangChu(ViewTrangChu viewTrangChu) {
        this.viewTrangChu = viewTrangChu;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachThuongHieu() {

    }

    @Override
    public void layDanhSachSanPham() {
        List<SanPham> dsSanPham = modelSanPham.layDanhSachSanPham("http://172.20.10.7:8080/1/products");
        if(dsSanPham.size() > 0){
            viewTrangChu.hienThiSanPham(dsSanPham);
        }
    }
}
