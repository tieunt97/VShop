package com.example.tieu_nt.vshop.Model.Data;

import com.example.tieu_nt.vshop.Model.KhachHang;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class ModelKhachHang {
    private static ModelKhachHang modelKhachHang;

    private ModelKhachHang(){}

    public static ModelKhachHang getInstance(){
        if (modelKhachHang == null)
            modelKhachHang = new ModelKhachHang();

        return modelKhachHang;
    }

    public KhachHang layThongTinKhachHang(int idKhachHang){
        KhachHang khachHang = new KhachHang();

        return khachHang;
    }

    public boolean themSanPhamGioHang(int idKhachHang, int idSanPham){
        boolean b = false;

        return b;
    }

    public boolean capNhatSanPhamYeuThich(int idKhachHang, int idSanPham){
        boolean b = false;

        return b;
    }

}
