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
public class ThuocTinh {
    private int maThuocTinh;
    private String tenThuocTinh;

    public ThuocTinh() {
    }

    public ThuocTinh(int maThuocTinh, String tenThuocTinh) {
        this.maThuocTinh = maThuocTinh;
        this.tenThuocTinh = tenThuocTinh;
    }

    public int getMaThuocTinh() {
        return maThuocTinh;
    }

    public void setMaThuocTinh(int maThuocTinh) {
        this.maThuocTinh = maThuocTinh;
    }

    public String getTenThuocTinh() {
        return tenThuocTinh;
    }

    public void setTenThuocTinh(String tenThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
    }
    
    
}
