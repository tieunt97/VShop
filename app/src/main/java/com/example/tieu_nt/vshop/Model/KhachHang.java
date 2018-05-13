package com.example.tieu_nt.vshop.Model;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class KhachHang {
    private int idKhachHang;
    private String tenKhachHang, soDT, diaChi, anhKhachHang;
    private TaiKhoan taiKhoan;


    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAnhKhachHang() {
        return anhKhachHang;
    }

    public void setAnhKhachHang(String anhKhachHang) {
        this.anhKhachHang = anhKhachHang;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
