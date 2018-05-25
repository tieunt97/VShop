package com.example.tieu_nt.vshop.Model;

import java.io.Serializable;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class NguoiDung implements Serializable{
    public static final int LEVEL_KHACHHANG = 1, LEVEL_NVGIAOHANG = 4;
    private int idNguoiDung, level;
    private String tenNguoiDung, soDT, diaChi, anhNguoiDung, email, matKhau, token;


    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
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

    public String getAnhNguoiDung() {
        return anhNguoiDung;
    }

    public void setAnhNguoiDung(String anhNguoiDung) {
        this.anhNguoiDung = anhNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
