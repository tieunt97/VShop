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
public class PhieuMua {
    private int maPhieu;
    private String trangThai;
    private String ngayGiao;
    private String diaChiGiao;
    private int phiGiao;
    private String ngayDatHang;

    public PhieuMua() {
    }

    public PhieuMua(int maPhieu, String trangThai, String diaChiGiao, String ngayDatHang) {
        this.maPhieu = maPhieu;
        this.trangThai = trangThai;
        this.diaChiGiao = diaChiGiao;
        this.ngayDatHang = ngayDatHang;
    }

    
    public PhieuMua(int maPhieu, String trangThai, String diaChiGiao, int phiGiao, String ngayDatHang) {
        this.maPhieu = maPhieu;
        this.trangThai = trangThai;
        this.diaChiGiao = diaChiGiao;
        this.phiGiao = phiGiao;
        this.ngayDatHang = ngayDatHang;
    }
    

    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getDiaChiGiao() {
        return diaChiGiao;
    }

    public void setDiaChiGiao(String diaChiGiao) {
        this.diaChiGiao = diaChiGiao;
    }

    public int getPhiGiao() {
        return phiGiao;
    }

    public void setPhiGiao(int phiGiao) {
        this.phiGiao = phiGiao;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }
    
    
}
