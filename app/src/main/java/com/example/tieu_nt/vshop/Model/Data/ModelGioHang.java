package com.example.tieu_nt.vshop.Model.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class ModelGioHang {
    private static ModelGioHang modelGioHang;
    private SQLiteDatabase database;

    private ModelGioHang(){

    }

    public static ModelGioHang getInstance(){
        if(modelGioHang == null)
            modelGioHang = new ModelGioHang();

        return modelGioHang;
    }

    public void ketNoiSQLite(Context context){
        DatabaseSanPham databaseSanPham = new DatabaseSanPham(context);
        database = databaseSanPham.getWritableDatabase();
    }

    public boolean xoaSanPhamTrongGioHang(int idSanPham){
        int kiemTra = database.delete(DatabaseSanPham.TB_GIOHANG, DatabaseSanPham.MASP + " = " + idSanPham, null);
        if (kiemTra > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean capNhatSoLuongSanPhamGioHang(int idSanPham, int soLuong, int soLuongTonKho){
        ContentValues values = new ContentValues();
        values.put(DatabaseSanPham.SOLUONG, soLuong);
        values.put(DatabaseSanPham.SOLUONGTONKHO, soLuongTonKho);

        int id = database.update(DatabaseSanPham.TB_GIOHANG, values, DatabaseSanPham.MASP + " = " + idSanPham, null);
        if (id > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean themSanPhamGioHang(SanPham sanPham){
        ContentValues values = new ContentValues();
        values.put(DatabaseSanPham.MASP, sanPham.getIdSanPham());
        values.put(DatabaseSanPham.TENSP, sanPham.getTenSanPham());
        values.put(DatabaseSanPham.GIATIEN, sanPham.getGiaChuan());
        values.put(DatabaseSanPham.HINHANH, sanPham.getHinhSPGioHang());
        values.put(DatabaseSanPham.SOLUONG, sanPham.getSoLuong());
        values.put(DatabaseSanPham.DANHGIATB, sanPham.getDanhGiaTB());
        values.put(DatabaseSanPham.SOLUONGTONKHO, sanPham.getSoLuongTonKho());

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
            sanPham.setIdSanPham(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.MASP)));
            sanPham.setTenSanPham(cursor.getString(cursor.getColumnIndex(DatabaseSanPham.TENSP)));
            sanPham.setGiaChuan(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.GIATIEN)));
            sanPham.setHinhSPGioHang(cursor.getBlob(cursor.getColumnIndex(DatabaseSanPham.HINHANH)));
            sanPham.setSoLuong(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.SOLUONG)));
            sanPham.setDanhGiaTB(cursor.getFloat(cursor.getColumnIndex(DatabaseSanPham.DANHGIATB)));
            sanPham.setSoLuongTonKho(cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.SOLUONGTONKHO)));

            dsSanPham.add(sanPham);
        }

        return dsSanPham;
    }
}
