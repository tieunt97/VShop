package com.example.tieu_nt.vshop.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class SanPham implements Serializable{
    private int idSanPham, idNguoiBan, giaChuan, soLuotDanhGia;
    private String tenSanPham, moTa, hinhSanPham;
    private List<String> dsHinhSP;
    private float danhGiaTB;
    private boolean yeuThich;


    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdNguoiBan() {
        return idNguoiBan;
    }

    public void setIdNguoiBan(int idNguoiBan) {
        this.idNguoiBan = idNguoiBan;
    }

    public int getGiaChuan() {
        return giaChuan;
    }

    public void setGiaChuan(int giaChuan) {
        this.giaChuan = giaChuan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public List<String> getDsHinhSP() {
        return dsHinhSP;
    }

    public void setDsHinhSP(List<String> dsHinhSP) {
        this.dsHinhSP = dsHinhSP;
    }

    public int getSoLuotDanhGia() {
        return soLuotDanhGia;
    }

    public void setSoLuotDanhGia(int soLuotDanhGia) {
        this.soLuotDanhGia = soLuotDanhGia;
    }

    public float getDanhGiaTB() {
        return danhGiaTB;
    }

    public void setDanhGiaTB(float danhGiaTB) {
        this.danhGiaTB = danhGiaTB;
    }

    public boolean isYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(boolean yeuThich) {
        this.yeuThich = yeuThich;
    }
}
