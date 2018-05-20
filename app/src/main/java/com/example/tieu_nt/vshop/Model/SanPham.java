package com.example.tieu_nt.vshop.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class SanPham implements Serializable{
    private int idSanPham, giaChuan, soLuotDanhGia, soLuongTonKho, soLuong;
    private String tenSanPham, moTa, hinhSanPham, thuongHieu;
    private List<String> dsHinhSP;
    private float danhGiaTB;
    private boolean yeuThich;
    private int[] dsSoSao;
    private byte[] hinhSPGioHang;
    private List<ChiTietSanPham> dsChiTietSanPham;
    private List<DanhGia> dsDanhGia;


    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
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

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public int[] getDsSoSao() {
        return dsSoSao;
    }

    public void setDsSoSao(int[] dsSoSao) {
        this.dsSoSao = dsSoSao;
    }

    public byte[] getHinhSPGioHang() {
        return hinhSPGioHang;
    }

    public void setHinhSPGioHang(byte[] hinhSPGioHang) {
        this.hinhSPGioHang = hinhSPGioHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public List<ChiTietSanPham> getDsChiTietSanPham() {
        return dsChiTietSanPham;
    }

    public void setDsChiTietSanPham(List<ChiTietSanPham> dsChiTietSanPham) {
        this.dsChiTietSanPham = dsChiTietSanPham;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public List<DanhGia> getDsDanhGia() {
        return dsDanhGia;
    }

    public void setDsDanhGia(List<DanhGia> dsDanhGia) {
        this.dsDanhGia = dsDanhGia;
    }
}
