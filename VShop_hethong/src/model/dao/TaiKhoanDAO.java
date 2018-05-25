/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TaiKhoan;

/**
 *
 * @author Trong
 */
public class TaiKhoanDAO extends DataAccess{
    
    private final String getTTTaiKhoan = "SELECT DISTINCT users.id, users.name, users.phone_number, users.address FROM `shop-mobile`.sale_bills, `shop-mobile`.users WHERE sale_bills.customer_id = users.id AND sale_bills.id = ?;";
    private final String checkTaiKhoan = "SELECT DISTINCT level FROM `shop-mobile`.users WHERE users.email = ? AND users.password = ?;";
    private final String checkMaTaiKhoan = "SELECT DISTINCT id FROM `shop-mobile`.users WHERE users.email = ? AND users.password = ?;";
    
    public TaiKhoan getTTTaiKhoan(String maPhieuMua){
        ResultSet rs = null;
        TaiKhoan taiKhoan = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getTTTaiKhoan);
            pst.setString(1, maPhieuMua);
            rs = pst.executeQuery();
            if(rs.next()){
                int maTK = rs.getInt(1);
                String hoTen = rs.getString(2);
                String soDT = rs.getString(3);
                String diaChi = rs.getString(4);
                taiKhoan = new TaiKhoan(maTK, hoTen, soDT, diaChi);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taiKhoan;
    }
    
    public int checkTaiKhoan(TaiKhoan taiKhoan){
        int level = -1;
        ResultSet rs = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(checkTaiKhoan);
            pst.setString(1, taiKhoan.getEmail());
            pst.setString(2, taiKhoan.getMatKhau());
            rs = pst.executeQuery();
            if(rs.next()){
                level = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return level;
    }
    
    public int checkMaTaiKhoan(TaiKhoan taiKhoan){
        int maTK = -1;
        ResultSet rs = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(checkMaTaiKhoan);
            pst.setString(1, taiKhoan.getEmail());
            pst.setString(2, taiKhoan.getMatKhau());
            rs = pst.executeQuery();
            if(rs.next()){
                maTK = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maTK;
    }
}
