/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.BaiDang;
import model.ChiTietPhieuMua;
import model.PhieuMua;
import model.SanPham;
import model.TaiKhoan;
import model.dao.BaiDangDAO;
import model.dao.TaiKhoanDAO;
import model.dao.DonHangDAO;
import model.dao.SanPhamDAO;

/**
 *
 * @author Trong
 */
public class NhanVienController {
    private DonHangDAO donHangDAO;
    private SanPhamDAO sanPhamDAO;
    private TaiKhoanDAO taiKhoanDAO;
    private BaiDangDAO baiDangDAO;
    public NhanVienController(){
        donHangDAO = new DonHangDAO();
        sanPhamDAO = new SanPhamDAO();
        taiKhoanDAO = new TaiKhoanDAO();
        baiDangDAO = new BaiDangDAO();
    }
    
    public ArrayList<PhieuMua> getDonHang(){
        return donHangDAO.getDonHang();
    }
    
    public ArrayList<ChiTietPhieuMua> getchiTietDonHang(String maPhieuMua){
        return donHangDAO.getChiTietDonHang(maPhieuMua);
    }
    
    public SanPham getSanPhamMua(int maSP){
        return sanPhamDAO.getSanPhamMua(maSP);
    }
    
    public TaiKhoan getTTTaiKhoan(String maPhieuMua){
        return taiKhoanDAO.getTTTaiKhoan(maPhieuMua);
    }
    
    public boolean xacNhanDonHang(String maPhieuMua){
        return donHangDAO.xacNhanDonHang(maPhieuMua);
    }
    
    public boolean huyDonHang(String maPhieuMua, ArrayList<String> listMaSP){
        return donHangDAO.huyDonHang(maPhieuMua, listMaSP);
    }
    
    
    public ArrayList<BaiDang> getBaiDang(){
        return baiDangDAO.getBaiDang();
    }
    
    public boolean themBaiDang(BaiDang baiDang){
        return baiDangDAO.themBaiDang(baiDang);
    }
    
    public boolean suaBaiDang(BaiDang baiDang){
        return baiDangDAO.suaBaiDang(baiDang);
    }
    
    public boolean xoaBaiDang(int maBaiDang){
        return baiDangDAO.xoaBaiDang(maBaiDang);
    }
}
