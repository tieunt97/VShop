package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewChiTietSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham{
    private ViewChiTietSanPham viewChiTietSanPham;
    private ModelSanPham modelSanPham;
    private ModelKhachHang modelKhachHang;



    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        this.modelSanPham = ModelSanPham.getInstance();
        this.modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layChiTietSanPham(int idSanPham) {
        SanPham sanPham = modelSanPham.layChiTietSanPham(idSanPham);
        List<String> dsHinhSP = sanPham.getDsHinhSP();
        if(dsHinhSP.size() > 0){
            viewChiTietSanPham.hienThiSliderSP(dsHinhSP);
        }
        viewChiTietSanPham.hienThiChiTietSanPham(sanPham);
    }

    @Override
    public boolean capNhatSanPhamYeuThich(int idKhachHang, int idSanPham){
        return modelKhachHang.capNhatSanPhamYeuThich(idKhachHang, idSanPham);
    }
}
