/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.TaiKhoan;
import model.dao.TaiKhoanDAO;

/**
 *
 * @author Trong
 */
public class DangNhapController {

    TaiKhoanDAO taiKhoanDAO;
    public DangNhapController() {
        taiKhoanDAO = new TaiKhoanDAO();
    }
    
    public int checkTaiKhoan(TaiKhoan taiKhoan){
        return taiKhoanDAO.checkTaiKhoan(taiKhoan);
    }
    
    public int checkMaTaiKhoan(TaiKhoan taiKhoan){
        return taiKhoanDAO.checkMaTaiKhoan(taiKhoan);
    }
}
