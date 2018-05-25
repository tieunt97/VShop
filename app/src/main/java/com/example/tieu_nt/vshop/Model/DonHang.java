package com.example.tieu_nt.vshop.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class DonHang implements Serializable{
    private int idHoaDon, idKhachHang, idSanPham, tongTienTT, phiShip, trangThai;
    private String ngayDatHang, ngayGiaoHang, diaChi;
    private NguoiDung khachHang;
    private ArrayList<SanPham> dsSanPham;
    private Date ngayDat, ngayGiao;
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

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getTongTienTT() {
        return tongTienTT;
    }

    public void setTongTienTT(int tongTienTT) {
        this.tongTienTT = tongTienTT;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public String getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public void setNgayGiaoHang(String ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }
}
