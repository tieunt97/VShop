package com.example.tieu_nt.vshop.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class TinTuc implements Serializable{
    private int idTinTuc;
    private String tieuDe, noiDung;
    private Date thoiGian;

    public int getIdTinTuc() {
        return idTinTuc;
    }

    public void setIdTinTuc(int idTinTuc) {
        this.idTinTuc = idTinTuc;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }
}
