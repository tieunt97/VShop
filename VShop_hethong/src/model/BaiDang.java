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
public class BaiDang {
    private int maBaiDang;
    private String tieuDe;
    private String noiDung;

    public BaiDang() {
    }

    public BaiDang(int maBaiDang, String tieuDe, String noiDung) {
        this.maBaiDang = maBaiDang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
    }

    public BaiDang(String tieuDe, String noiDung) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
    }

    public int getMaBaiDang() {
        return maBaiDang;
    }

    public void setMaBaiDang(int maBaiDang) {
        this.maBaiDang = maBaiDang;
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
    
    
}
