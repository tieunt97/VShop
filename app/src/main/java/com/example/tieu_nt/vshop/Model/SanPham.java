package com.example.tieu_nt.vshop.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class SanPham {
    private int idSanPham, idNguoiBan, giaChuan;
    private String tenSanPham, moTa, hinhSanPham;


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
}
