package com.example.tieu_nt.vshop.Presenter.SanPham;

import com.example.tieu_nt.vshop.Model.Data.ModelSanPham;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.TrangChu.ViewChiTietSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterChiTietSanPham implements IPresenterChiTietSanPham{
    private ViewChiTietSanPham viewChiTietSanPham;
    private ModelSanPham modelSanPham;


    public PresenterChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layChiTietSanPham(SanPham sanPham) {
        modelSanPham.layChiTietSanPham(sanPham);
        List<String> dsHinhSP = sanPham.getDsHinhSP();
        if(dsHinhSP.size() > 0){
            viewChiTietSanPham.hienThiSliderSP(dsHinhSP);
        }
        viewChiTietSanPham.hienThiChiTietSanPham(sanPham);
    }
}
