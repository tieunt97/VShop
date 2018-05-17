package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiDanhSachSanPham;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterLogicSanPham implements IPresenterSanPham{
    private ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham;
    private ModelSanPham modelSanPham;

    public PresenterLogicSanPham(ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham) {
        this.viewHienThiDanhSachSanPham = viewHienThiDanhSachSanPham;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachSanPham(String dươngDan) {
        TrangSanPham trangSanPham = modelSanPham.layDanhSachSanPham(dươngDan);
        if(trangSanPham.getDsSanPham().size() > 0){
            viewHienThiDanhSachSanPham.hienThiDanhSachSanPham(trangSanPham);
        }
    }

    @Override
    public TrangSanPham layDanhSachSanPhamLoadMore(String duongDan) {
        TrangSanPham trangSanPham = modelSanPham.layDanhSachSanPham(duongDan);

        return trangSanPham;
    }
}
