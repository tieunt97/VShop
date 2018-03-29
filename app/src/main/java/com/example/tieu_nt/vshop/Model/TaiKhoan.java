package com.example.tieu_nt.vshop.Model;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class TaiKhoan {
    private String email, matKhau;

    public TaiKhoan(){
        this.email = null;
        this.matKhau = null;
    }

    public TaiKhoan(String email, String matKhau){
        this.email = email;
        this.matKhau = matKhau;
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
}
