package com.example.tieu_nt.vshop.Model;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.Date;
import java.util.List;
>>>>>>> 1aecfad939e8da13cf73ff4396f57b1ced219b4d

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class DonHang implements Serializable{
<<<<<<< HEAD
    private int idHoaDon, idKhachHang, idSanPham, tongTienTT, phiShip, trangThai;
    private String ngayDatHang, ngayGiaoHang, diaChi;
    private NguoiDung khachHang;
    private ArrayList<SanPham> dsSanPham;
=======
    private int idHoaDon, phiShip, trangThai;
    private String diaChi;
    private Date ngayDat, ngayGiao;
    private NguoiDung khachHang;
    private List<SanPham> dsSanPham;
>>>>>>> 1aecfad939e8da13cf73ff4396f57b1ced219b4d
    public static final int TRANGTHAI_DADATHANG = 1, TRANGTHAI_DANGGIAOHANG = 2, TRANGTHAI_DANHANHANG = 3,
    TRANGTHAI_HUYDONHANG = 4, TRANGTHAI_COHANGTRA = 5;


    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getPhiShip() {
        return phiShip;
    }

    public void setPhiShip(int phiShip) {
        this.phiShip = phiShip;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public NguoiDung getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(NguoiDung khachHang) {
        this.khachHang = khachHang;
    }

    public List<SanPham> getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }


    public ArrayList<SanPham> getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(ArrayList<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
    }

    public NguoiDung getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(NguoiDung khachHang) {
        this.khachHang = khachHang;
    }
}
