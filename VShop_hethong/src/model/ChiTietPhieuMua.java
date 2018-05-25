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
public class ChiTietPhieuMua {
    private int maChiTietPhieuMua;
    private int maSanPham;
    private int soLuong;

    public ChiTietPhieuMua() {
    }

    public ChiTietPhieuMua(int maSanPham, int soLuong) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    
    public int getMaChiTietPhieuMua() {
        return maChiTietPhieuMua;
    }

    public void setMaChiTietPhieuMua(int maChiTietPhieuMua) {
        this.maChiTietPhieuMua = maChiTietPhieuMua;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
