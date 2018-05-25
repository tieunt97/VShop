/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BaiDang;
import view.DangNhapForm;

/**
 *
 * @author Trong
 */
public class BaiDangDAO extends DataAccess{
    
    private final String getBaiDang = "SELECT DISTINCT id, title, content FROM `shop-mobile`.posters WHERE employee_id = ?;";
    private final String themBaiDang = "INSERT INTO `shop-mobile`.`posters` (`employee_id`, `title`, `content`, `created_at`) VALUES (?, ?, ?, ?);";
    private final String suaBaiDang = "UPDATE `shop-mobile`.`posters` SET `title`= ?, `content`= ?, `updated_at`= ? WHERE `id`= ?;";
    private final String xoaBaiDang = "DELETE FROM `shop-mobile`.`posters` WHERE `id`= ?;";
    
    public ArrayList<BaiDang> getBaiDang(){
        int idNhanVien = DangNhapForm.ma;
        ResultSet rs = null;
        ArrayList<BaiDang> list = new ArrayList<>();
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getBaiDang);
            pst.setInt(1, idNhanVien);
            rs = pst.executeQuery();
            while(rs.next()){
                int maBaiDang = rs.getInt(1);
                String tieuDe = rs.getString(2);
                String noiDung = rs.getString(3);
                BaiDang baiDang = new BaiDang(maBaiDang, tieuDe, noiDung);
                list.add(baiDang);
            }
            pst.close();
            closeDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BaiDangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean themBaiDang(BaiDang baiDang){
        int idNhanVien = DangNhapForm.ma;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(themBaiDang);
            pst.setInt(1, idNhanVien);
            pst.setString(2, baiDang.getTieuDe());
            pst.setString(3, baiDang.getNoiDung());
            Date date = new Date();
            Timestamp ngayDang = new Timestamp(date.getTime());
            pst.setTimestamp(4, ngayDang);
            pst.executeUpdate();
            pst.close();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BaiDangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean suaBaiDang(BaiDang baiDang){
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(suaBaiDang);
            pst.setString(1, baiDang.getTieuDe());
            pst.setString(2, baiDang.getNoiDung());
            Date date = new Date();
            Timestamp ngaySua = new Timestamp(date.getTime());
            pst.setTimestamp(3, ngaySua);
            pst.setInt(4, baiDang.getMaBaiDang());
            pst.executeUpdate();
            pst.close();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BaiDangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean xoaBaiDang(int maBaiDang){
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(xoaBaiDang);
            pst.setInt(1, maBaiDang);
            pst.executeUpdate();
            pst.close();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BaiDangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
