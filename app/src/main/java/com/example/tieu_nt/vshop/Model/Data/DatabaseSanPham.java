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
    public static final String MASP = "MASP";
    public static final String TENSP = "TENSP";
    public static final String GIATIEN = "GIATIEN";
    public static final String HINHANH = "HINHANH";
    public static final String SOLUONG = "SOLUONG";
    public static final String DANHGIATB = "DANHGIATB";
    public static final String SOLUONGTONKHO = "SOLUONGTONKHO";

    public static final String TB_NGUOIDUNG = "NGUOIDUNG";
    public static final String TB_NGUOIDUNG_ID = "ID";
    public static final String TB_NGUOIDUNG_MAND = "MAND";
    public static final String TB_NGUOIDUNG_LEVEL = "LEVEL";
    public static final String TB_NGUOIDUNG_DIACHI = "DIACHI";

    private static final String CREATE_TB_GIOHANG = "CREATE TABLE " + TB_GIOHANG + " (" + MASP + " INTEGER PRIMARY KEY , "
            + TENSP + " TEXT, " + GIATIEN + " REAL, " + HINHANH + "  BLOB, " + SOLUONG + " INTEGER, "
            + DANHGIATB + " REAL, " + SOLUONGTONKHO + " INTEGER );";

    private static final String CREATE_TB_NGUOIDUNG = "CREATE TABLE " + TB_NGUOIDUNG + " (" + TB_NGUOIDUNG_ID + " INTEGER PRIMARY KEY , "
            + TB_NGUOIDUNG_MAND + " INTEGER, " + TB_NGUOIDUNG_LEVEL + " INTEGER, " + TB_NGUOIDUNG_DIACHI + " TEXT);";

    private static final String DELETE_TB_GIOHANG = "DROP TABLE IF EXISTS "  + TB_GIOHANG;
    private static final String DELETE_TB_NGUOIDUNG = "DROP TABLE IF EXISTS "  + TB_NGUOIDUNG;


    public DatabaseSanPham(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_NGUOIDUNG);
        db.execSQL(CREATE_TB_GIOHANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TB_NGUOIDUNG);
        db.execSQL(DELETE_TB_GIOHANG);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
