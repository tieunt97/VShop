package com.example.tieu_nt.vshop.Model.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class DatabaseSanPham extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLSANPHAM";
    public static final String TB_GIOHANG = "GIOHANG";
    public static final String TB_GIOHANG_MASP = "MASP";
    public static final String TB_GIOHANG_TENSP = "TENSP";
    public static final String TB_GIOHANG_GIATIEN = "GIATIEN";
    public static final String TB_GIOHANG_HINHANH = "HINHANH";
    public static final String TB_GIOHANG_SOLUONG = "SOLUONG";
    public static final String TB_GIOHANG_SOLUONGTONKHO = "SOLUONGTONKHO";
    private static final String CREATE_TB_GIOHANG = "CREATE TABLE " + TB_GIOHANG + " (" + TB_GIOHANG_MASP + " INTEGER PRIMARY KEY , "
            + TB_GIOHANG_TENSP + " TEXT, " + TB_GIOHANG_GIATIEN + " REAL, " +TB_GIOHANG_HINHANH + "  BLOB, "
            + TB_GIOHANG_SOLUONG + " INTEGER, " + TB_GIOHANG_SOLUONGTONKHO + " INTEGER );";
    private static final String DELETE_TB_GIOHANG = "DROP TABLE IF EXISTS "  + TB_GIOHANG;


    public DatabaseSanPham(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_GIOHANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TB_GIOHANG);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
