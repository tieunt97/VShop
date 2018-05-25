/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTML;
import model.LoaiSanPham;
import model.SanPham;
import model.ThuocTinh;
import model.ThuongHieu;

/**
 *
 * @author Trong
 */
public class SanPhamDAO extends DataAccess {

    private final String getSanPhamMua = "SELECT DISTINCT product_name, unit, base_price FROM `shop-mobile`.products WHERE products.id = ?;";
    private final String getLoaiSP = "SELECT DISTINCT id, product_type_name, icon_image FROM `shop-mobile`.product_types;";
    private final String getThuongHieu = "SELECT DISTINCT id, name, icon FROM `shop-mobile`.providers;";
    private final String getThuocTinh = "SELECT id, category_name FROM `shop-mobile`.category_attributes;";
    private final String getSanPham1 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.providers "
            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id "
            + "and providers.name = ?;";
    private final String getSanPham2 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.category_attributes, `shop-mobile`.providers "
            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id "
            + "and product_types.product_type_name = ?;";
//    private final String getSanPham3 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
//            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.category_attributes, `shop-mobile`.providers "
//            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id "
//            + "and product_types.product_type_name = ? and providers.name = ?;";
    private final String getSanPham4 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.category_attributes, `shop-mobile`.providers "
            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id;";
//    private final String getSanPham5 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
//            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.category_attributes, `shop-mobile`.providers "
//            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id "
//            + "and providers.name = ?;";
//    private final String getSanPham6 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
//            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.category_attributes, `shop-mobile`.providers "
//            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id "
//            + "and product_types.product_type_name = ?;";
//    private final String getSanPham7 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
//            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.category_attributes, `shop-mobile`.providers "
//            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id;";
    private final String getSanPham8 = "SELECT DISTINCT products.id, products.product_name, products.unit, products.base_price, products.description, products.quantity, products.main_image, products.sub_images "
            + "FROM `shop-mobile`.products, `shop-mobile`.product_types, `shop-mobile`.category_attributes, `shop-mobile`.providers "
            + "WHERE products.product_type_id = product_types.id and products.provider = providers.id "
            + "and product_types.product_type_name = ? and providers.name = ?;";
    
    private final String themLoaiSP = "INSERT INTO `shop-mobile`.`product_types` (`product_type_name`, `icon_image`) VALUES (?, ?);";
    private final String suaLoaiSP = "UPDATE `shop-mobile`.`product_types` SET `product_type_name`= ? WHERE `id`= ?;";
    private final String xoaLoaiSP = "DELETE FROM `shop-mobile`.`product_types` WHERE `id`= ?;";
    
    public SanPham getSanPhamMua(int maSP) {
        ResultSet rs = null;
        SanPham sanPham = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getSanPhamMua);
            pst.setInt(1, maSP);
            rs = pst.executeQuery();
            if (rs.next()) {
                String tenSP = rs.getString(1);
                String donVi = rs.getString(2);
                int gia = rs.getInt(3);
                sanPham = new SanPham(maSP, tenSP, donVi, gia);
            }
            pst.close();
            closeDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sanPham;
    }

    public ArrayList<LoaiSanPham> getLoaiSP() {
        ArrayList<LoaiSanPham> listLoaiSP = new ArrayList<>();
        ResultSet rs = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getLoaiSP);
            rs = pst.executeQuery();
            while (rs.next()) {
                int maLoai = rs.getInt(1);
                String tenLoai = rs.getString(2);
                String icon_image = rs.getString(3);
                LoaiSanPham loaiSP = new LoaiSanPham(maLoai, tenLoai, icon_image);
                listLoaiSP.add(loaiSP);
            }
            pst.close();
            closeDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLoaiSP;
    }

    public ArrayList<ThuongHieu> getThuongHieu() {
        ArrayList<ThuongHieu> listThuongHieu = new ArrayList<>();
        ResultSet rs = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getThuongHieu);
            rs = pst.executeQuery();
            while (rs.next()) {
                int maThuongHieu = rs.getInt(1);
                String tenThuongHieu = rs.getString(2);
                String icon_image = rs.getString(3);
                ThuongHieu thuongHieu = new ThuongHieu(maThuongHieu, tenThuongHieu, icon_image);
                listThuongHieu.add(thuongHieu);
            }
            pst.close();
            closeDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuongHieu;
    }

    public ArrayList<ThuocTinh> getThuocTinh() {
        ArrayList<ThuocTinh> listThuocTinh = new ArrayList<>();
        ResultSet rs = null;
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(getThuocTinh);
            rs = pst.executeQuery();
            while (rs.next()) {
                int maThuocTinh = rs.getInt(1);
                String tenThuocTinh = rs.getString(2);
                ThuocTinh thuocTinh = new ThuocTinh(maThuocTinh, tenThuocTinh);
                listThuocTinh.add(thuocTinh);
            }
            pst.close();
            closeDB();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    private ResultSet getResultSet(String loaiSp, String thuongHieu, String thuocTinh) throws SQLException, ClassNotFoundException {
        connectDB();
        ResultSet rs = null;
        if (loaiSp == null) {
            PreparedStatement pst = conn.prepareStatement(getSanPham1);
            pst.setString(1, thuongHieu);
            rs = pst.executeQuery();
        } else if (thuongHieu == null) {
            PreparedStatement pst = conn.prepareStatement(getSanPham2);
            pst.setString(1, loaiSp);
            rs = pst.executeQuery();
//        } else if (thuocTinh == null) {
//            PreparedStatement pst = conn.prepareStatement(getSanPham3);
//            pst.setString(1, loaiSp);
//            pst.setString(2, thuongHieu);
//            rs = pst.executeQuery();
        } else if (loaiSp == null && thuongHieu == null) {
            PreparedStatement pst = conn.prepareStatement(getSanPham4);
            rs = pst.executeQuery();
//        } else if (loaiSp == null && thuocTinh == null) {
//            PreparedStatement pst = conn.prepareStatement(getSanPham5);
//            pst.setString(1, thuongHieu);
//            rs = pst.executeQuery();
//        } else if (thuongHieu == null && thuocTinh == null) {
//            PreparedStatement pst = conn.prepareStatement(getSanPham6);
//            pst.setString(1, loaiSp);
//            rs = pst.executeQuery();
//        } else if (loaiSp == null && thuongHieu == null && thuocTinh == null) {
//            PreparedStatement pst = conn.prepareStatement(getSanPham7);
//            rs = pst.executeQuery();
        } else {
            PreparedStatement pst = conn.prepareStatement(getSanPham8);
            pst.setString(1, loaiSp);
            pst.setString(2, thuongHieu);
            rs = pst.executeQuery();
        }
//        closeDB();
        return rs;
    }

    public ArrayList<SanPham> getSanPham(String loaiSp, String thuongHieu, String thuocTinh) {
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        try {
            ResultSet rs = getResultSet(loaiSp, thuongHieu, thuocTinh);
            while (rs.next()) {
                int maSP = rs.getInt(1);
                String tenSP = rs.getString(2);
                String donVi = rs.getString(3);
                int gia = rs.getInt(4);
                String moTa = rs.getString(5);
                int soLuong = rs.getInt(6);
                String anhChinh = rs.getString(7);
                String anhPhu = rs.getString(8);
                SanPham sanPham = new SanPham(maSP, tenSP, donVi, gia, moTa, soLuong, anhChinh, anhPhu);
                listSanPham.add(sanPham);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    
    public boolean themLoaiSP(String loaiSP){
        String icon = "0";
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(themLoaiSP);
            pst.setString(1, loaiSP);
            pst.setString(2, icon);
            pst.executeUpdate();
            pst.close();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean suaLoaiSP(String loaiSP, int maLoaiSP){
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(suaLoaiSP);
            pst.setString(1, loaiSP);
            pst.setInt(2, maLoaiSP);
            pst.executeUpdate();
            pst.close();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean xoaLoaiSP(int maLoaiSP){
        try {
            connectDB();
            PreparedStatement pst = conn.prepareStatement(xoaLoaiSP);
            pst.setInt(1, maLoaiSP);
            pst.executeUpdate();
            pst.close();
            closeDB();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
