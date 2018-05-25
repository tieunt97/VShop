/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.LoaiSanPham;
import model.SanPham;
import model.ThuocTinh;
import model.ThuongHieu;
import model.dao.SanPhamDAO;

/**
 *
 * @author Trong
 */
public class ThuKhoController {

    SanPhamDAO sanPhamDAO;
    public ThuKhoController() {
        sanPhamDAO = new SanPhamDAO();
    }
    
    public ArrayList<LoaiSanPham> getLoaiSP(){
        return sanPhamDAO.getLoaiSP();
    }
    
    public ArrayList<ThuongHieu> getThuongHieu(){
        return sanPhamDAO.getThuongHieu();
    }
    
    public ArrayList<ThuocTinh> getThuocTinh(){
        return sanPhamDAO.getThuocTinh();
    }
    
    public ArrayList<SanPham> getSanPham(String loaiSp, String thuongHieu, String thuocTinh){
        return sanPhamDAO.getSanPham(loaiSp, thuongHieu, thuocTinh);
    }
    
    public boolean themLoaiSP(String loaiSP){
        return sanPhamDAO.themLoaiSP(loaiSP);
    }
    
    public boolean suaLoaiSP(String loaiSP, int maSP){
        return sanPhamDAO.suaLoaiSP(loaiSP, maSP);
    }
    
    public boolean xoaLoaiSP(int maLoaiSP){
        return xoaLoaiSP(maLoaiSP);
    }
}
