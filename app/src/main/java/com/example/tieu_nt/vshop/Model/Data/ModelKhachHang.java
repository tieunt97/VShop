package com.example.tieu_nt.vshop.Model.Data;

import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDonHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.TinTuc;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangTinTuc;

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

    public boolean huyDonHang(int idDonHang){

        return false;
    }

    public List<SanPham> layDanhSachSanPhamDonHang(int idDonHang){
        List<SanPham> dsSanPham = new ArrayList<>();

        return dsSanPham;
    }

    public TrangDonHang layDanhSachDonHang(String duongDan){
        TrangDonHang trangDonHang = new TrangDonHang();
        List<DonHang> dsDonHang = new ArrayList<>();

        trangDonHang.setDsDonHang(dsDonHang);
        return trangDonHang;
    }

    public TrangTinTuc layDanhSachTinTuc(String duongDan){
        TrangTinTuc trangTinTuc = new TrangTinTuc();
        List<TinTuc> dsTinTuc = new ArrayList<>();

        trangTinTuc.setDsTinTuc(dsTinTuc);
        return trangTinTuc;
    }

    public boolean capNhatSanPhamYeuThich(int idKhachHang, int idSanPham){
        boolean b = false;

        return b;
    }

}
