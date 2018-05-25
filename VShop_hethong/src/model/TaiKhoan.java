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
public class TaiKhoan {
    private int maTK;
    private String email;
    private String matKhau;
    private String hoTen;
    private String soDT;
    private String diaChi;
    private String level;
    private String maXacNhan;

    public TaiKhoan() {
    }

    public TaiKhoan(int maTK, String hoTen, String soDT, String diaChi) {
        this.maTK = maTK;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.diaChi = diaChi;
    }

    public TaiKhoan(String email, String matKhau) {
        this.email = email;
        this.matKhau = matKhau;
    }

    
    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
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

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMaXacNhan() {
        return maXacNhan;
    }

    public void setMaXacNhan(String maXacNhan) {
        this.maXacNhan = maXacNhan;
    }
    
    
}
