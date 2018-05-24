package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;

/**
 * Created by tieu_nt on 5/24/2018.
 */

public class PresenterLogicSanPhamYeuThich implements IPresenterSanPhamYeuThich{
    private ModelKhachHang modelKhachHang;

    public PresenterLogicSanPhamYeuThich(){
        modelKhachHang = ModelKhachHang.getInstance();
    }


    @Override
    public boolean kiemTraSanPham(int idSanPham) {
        return modelKhachHang.kiemTraSanPhamYeuThich(idSanPham);
    }

    @Override
    public boolean capNhatSanPhamYeuThich(boolean isThich, int idSanPham) {
        return modelKhachHang.capNhatSanPhamYeuThich(isThich, idSanPham);
    }
}
