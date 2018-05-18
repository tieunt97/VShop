package com.example.tieu_nt.vshop.Model.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class ModelGioHang {
    SQLiteDatabase database;

    public void ketNoiSQLite(Context context){
        DatabaseSanPham databaseSanPham = new DatabaseSanPham(context);
        database = databaseSanPham.getWritableDatabase();
    }

    public boolean xoaSanPhamTrongGioHang(int idSanPham){
        int kiemTra = database.delete(DatabaseSanPham.TB_GIOHANG, DatabaseSanPham.TB_GIOHANG_MASP + " = " + idSanPham, null);
        if (kiemTra > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean capNhatSoLuongSanPhamGioHang(int idSanPham, int soLuong, int soLuongTonKho){
        ContentValues values = new ContentValues();
        values.put(DatabaseSanPham.TB_GIOHANG_SOLUONG, soLuong);
        values.put(DatabaseSanPham.TB_GIOHANG_SOLUONGTONKHO, soLuongTonKho);

        int id = database.update(DatabaseSanPham.TB_GIOHANG, values, DatabaseSanPham.TB_GIOHANG_MASP + " = " + idSanPham, null);
        if (id > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean themSanPhamGioHang(SanPham sanPham){
        ContentValues values = new ContentValues();
        values.put(DatabaseSanPham.TB_GIOHANG_MASP, sanPham.getIdSanPham());
        values.put(DatabaseSanPham.TB_GIOHANG_TENSP, sanPham.getTenSanPham());
        values.put(DatabaseSanPham.TB_GIOHANG_GIATIEN, sanPham.getGiaChuan());
        values.put(DatabaseSanPham.TB_GIOHANG_HINHANH, sanPham.getHinhSPGioHang());
        values.put(DatabaseSanPham.TB_GIOHANG_SOLUONG, sanPham.getSoLuong());
        values.put(DatabaseSanPham.TB_GIOHANG_SOLUONGTONKHO, sanPham.getSoLuongTonKho());

        long id = database.insert(DatabaseSanPham.TB_GIOHANG, null, values);
        if (id > 0){
            return true;
        }else{
            return false;
        }

    }

    public List<SanPham> layDSSanPhamGioHang(){
        List<SanPham> dsSanPham = new ArrayList<>();

        String sql = "SELECT * FROM " + DatabaseSanPham.TB_GIOHANG;
        Cursor cursor =  database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            SanPham sanPham = new SanPham();
            sanPham.setIdSanPham(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_MASP)));
            sanPham.setTenSanPham(cursor.getString(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_TENSP)));
            sanPham.setGiaChuan(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_GIATIEN)));
            sanPham.setHinhSPGioHang(cursor.getBlob(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_HINHANH)));
            sanPham.setSoLuong(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_SOLUONG)));
            sanPham.setSoLuongTonKho(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_SOLUONGTONKHO)));

            dsSanPham.add(sanPham);
        }

        return dsSanPham;
    }
}
