package com.example.tieu_nt.vshop.Model.Data;

import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.KhachHang;
import com.example.tieu_nt.vshop.Model.TinTuc;

import java.util.ArrayList;
import java.util.List;

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

    public List<DonHang> layDanhSachDonHang(String duongDan){
        List<DonHang> dsDonHang = new ArrayList<>();

        return dsDonHang;
    }

    public KhachHang layThongTinKhachHang(int idKhachHang){
        KhachHang khachHang = new KhachHang();

        return khachHang;
    }

    public List<TinTuc> layDanhSachTinTuc(String duongDan){
        List<TinTuc> dsTinTuc = new ArrayList<>();

        return dsTinTuc;
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
