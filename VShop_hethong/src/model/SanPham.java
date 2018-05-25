/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Trong
 */
public class SanPham {
    private int maSanPham;
    private String tenSP;
    private String donVi;
    private int gia;
    private String nhaCungCap;
    private String moTa;
    private int soLuong;
    private String loaiSP;
    private String anhChinh;
    private String anhPhu;

    public SanPham(int maSanPham, String tenSP, String donVi, int gia, String moTa, int soLuong, String anhChinh, String anhPhu) {
        this.maSanPham = maSanPham;
        this.tenSP = tenSP;
        this.donVi = donVi;
        this.gia = gia;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.anhChinh = anhChinh;
        this.anhPhu = anhPhu;
    }

    
    public SanPham() {
    }

    public SanPham(int maSanPham, String tenSP, int gia) {
        this.maSanPham = maSanPham;
        this.tenSP = tenSP;
        this.gia = gia;
    }

    public SanPham(int maSanPham, String tenSP, String donVi, int gia) {
        this.maSanPham = maSanPham;
        this.tenSP = tenSP;
        this.donVi = donVi;
        this.gia = gia;
    }

    
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getAnhChinh() {
        return anhChinh;
    }

    public void setAnhChinh(String anhChinh) {
        this.anhChinh = anhChinh;
    }

    public String getAnhPhu() {
        return anhPhu;
    }

    public void setAnhPhu(String anhPhu) {
        this.anhPhu = anhPhu;
    }
    
    
}
