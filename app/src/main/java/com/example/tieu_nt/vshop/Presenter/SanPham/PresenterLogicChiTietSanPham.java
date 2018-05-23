package com.example.tieu_nt.vshop.Presenter.SanPham;

import android.content.Context;

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
    private Context context;


    public PresenterLogicChiTietSanPham(Context context, ViewChiTietSanPham viewChiTietSanPham) {
        this.context = context;
        this.viewChiTietSanPham = viewChiTietSanPham;
        this.modelSanPham = ModelSanPham.getInstance();
        this.modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layChiTietSanPham(int idSanPham) {
        SanPham sanPham = modelSanPham.layChiTietSanPham(idSanPham);
        viewChiTietSanPham.hienThiChiTietSanPham(sanPham);
        List<String> dsHinhSP = sanPham.getDsHinhSP();
        if(dsHinhSP.size() > 0){
            viewChiTietSanPham.hienThiSliderSP(dsHinhSP);
        }
        if(sanPham.getDsDanhGia().size() > 0){
            viewChiTietSanPham.hienThiDSDanhGia(sanPham.getDsDanhGia());
        }
    }

    @Override
    public boolean themSanPhamYeuThich(int idSanPham) {
        return modelKhachHang.themSanPhamYeuThich(idSanPham);
    }

    @Override
    public boolean xoaSanPhamYeuThich(int idSanPham) {
        return false;
    }
}
