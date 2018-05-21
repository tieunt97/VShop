package com.example.tieu_nt.vshop.Model;

import java.io.Serializable;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public class ChiTietSanPham implements Serializable{
    private String tenChiTiet, giaTri;


    public String getTenChiTiet() {
        return tenChiTiet;
    }

    public void setTenChiTiet(String tenChiTiet) {
        this.tenChiTiet = tenChiTiet;
    }

    public String getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
    }
}
