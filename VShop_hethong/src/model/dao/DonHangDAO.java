/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trong
 */
public class DonHangDAO extends DataAccess{
    private final String getDonHang = "SELECT DISTINCT id, status_order, destination_address, book_date FROM `shop-mobile`.sale_bills WHERE status_order = \"pending\";";
    private final String getChiTietDonHang = "SELECT DISTINCT product_id, amount FROM `shop-mobile`.sale_descriptions WHERE sale_bill_id = ?;";
    private final String xacNhanDonHang = "UPDATE `shop-mobile`.`sale_bills` SET `status_order`='shipping' WHERE `id`=?;";
    private final String huyChiTietDonHang = "DELETE FROM `shop-mobile`.`sale_descriptions` WHERE sale_bill_id = ? and product_id = ?;";
    private final String huyDonHang = "DELETE FROM `shop-mobile`.`sale_bills` WHERE `id` = ?;";
    public ArrayList<PhieuMua> getDonHang(){
        ResultSet rs = null;
        ArrayList<PhieuMua> listPhieuMua = new ArrayList<>();
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getDonHang);
            rs = pst.executeQuery();
            while(rs.next()){
                int maPhieuMua = rs.getInt(1);
                String trangThai = rs.getString(2);
                String diaChiGiao = rs.getString(3);
                String ngayDatHang = rs.getString(4);
                PhieuMua phieuMua = new PhieuMua(maPhieuMua, trangThai, diaChiGiao, ngayDatHang);
                listPhieuMua.add(phieuMua);
            }
            closeDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPhieuMua;
    }
    
    public ArrayList<ChiTietPhieuMua> getChiTietDonHang(String maPhieuMua){
        ArrayList<ChiTietPhieuMua> list = new ArrayList<ChiTietPhieuMua>();
        ResultSet rs = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getChiTietDonHang);
            pst.setString(1, maPhieuMua);
            rs = pst.executeQuery();
            while(rs.next()){
                int maSP = rs.getInt(1);
                int soLuong = rs.getInt(2);
                ChiTietPhieuMua chiTietPhieuMua = new ChiTietPhieuMua(maSP, soLuong);
                list.add(chiTietPhieuMua);
            }
            closeDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean xacNhanDonHang(String maPhieuMua){
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(xacNhanDonHang);
            pst.setString(1, maPhieuMua);
            pst.executeUpdate();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean huyChiTietDonHang(String maPhieuMua, ArrayList<String> listMaSP){
        try {
            connectDB();
            for(int i=0; i<listMaSP.size(); i++){
                String maSP = listMaSP.get(i);
                PreparedStatement pst = conn.prepareStatement(huyChiTietDonHang);
                pst.setString(1, maPhieuMua);
                pst.setString(2, maSP);
                pst.executeUpdate();
                pst.close();
            }
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean huyDonHang(String maPhieuMua, ArrayList<String> listMaSP){
        if(!huyChiTietDonHang(maPhieuMua, listMaSP)){
            return false;
        }else{
            try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(huyDonHang);
            pst.setString(1, maPhieuMua);
            pst.executeUpdate();
            pst.close();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        }
    }
}
