package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

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
        List<SanPham> dsSanPham = modelSanPham.layDanhSachSanPham(dươngDan);
        if(dsSanPham.size() > 0){
            viewHienThiDanhSachSanPham.hienThiDanhSachSanPham(dsSanPham);
        }
    }

    @Override
    public List<SanPham> layDanhSachSanPhamLoadMore(String duongDan) {
        List<SanPham> dsSanPham = modelSanPham.layDanhSachSanPham(duongDan);

        return dsSanPham;
    }
}
