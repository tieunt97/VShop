/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Trong
 */
public class DataAccess {
    public static Connection conn = null;
    private final String  url = "jdbc:mysql://localhost:3306/mydb?useSSL=true";
    private final String user = "root";
    private final String pass = "01685394030";
    
    /**
    *Hàm này để tạo kết nối với cơ sở dữ liệu
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException 
     * @see SQLException
    */
    public void connectDB() throws SQLException, ClassNotFoundException {
            try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            //            Logger.getLogger(KetNoiSQLserver.class.getName()).log(Level.SEVERE, null, ex);
            conn.close();
        }
    }
    
    /**
    *Hàm này để đóng kết nối cơ sở dữ liệu
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
    */
    public void closeDB() throws SQLException {
        if (conn != null)
        	conn.close();
    }
}
