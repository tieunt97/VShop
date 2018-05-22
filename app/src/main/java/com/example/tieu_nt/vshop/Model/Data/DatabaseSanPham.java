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

    public static final String TB_YEUTHICH = "YEUTHICH";

    public static final String TB_DIACHI = "DIACHI";
    public static final String TB_DIACHI_MADC = "MADIACHI";
    public static final String TB_DIACHI_TEN = "DIACHI";

    private static final String CREATE_TB_GIOHANG = "CREATE TABLE " + TB_GIOHANG + " (" + MASP + " INTEGER PRIMARY KEY , "
            + TENSP + " TEXT, " + GIATIEN + " REAL, " + HINHANH + "  BLOB, " + SOLUONG + " INTEGER, "
            + DANHGIATB + " REAL, " + SOLUONGTONKHO + " INTEGER );";

    private static final String CREATE_TB_YEUTHICH = "CREATE TABLE " + TB_YEUTHICH + " (" + MASP + " INTEGER PRIMARY KEY , "
            + TENSP + " TEXT, " + GIATIEN + " REAL, " + HINHANH + "  BLOB, " + SOLUONG + " INTEGER, "
            + DANHGIATB + " REAL, " + SOLUONGTONKHO + " INTEGER );";

    private static final String CREATE_TB_DIACHI = "CREATE TABLE " + TB_DIACHI + " (" + TB_DIACHI_MADC + " INTEGER PRIMARY KEY , "
            + TB_DIACHI_TEN + " TEXT);";

    private static final String DELETE_TB_GIOHANG = "DROP TABLE IF EXISTS "  + TB_GIOHANG;
    private static final String DELETE_TB_YEUTHICH = "DROP TABLE IF EXISTS "  + TB_YEUTHICH;


    public DatabaseSanPham(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_DIACHI);
        db.execSQL(CREATE_TB_GIOHANG);
        db.execSQL(CREATE_TB_YEUTHICH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TB_GIOHANG);
        db.execSQL(DELETE_TB_YEUTHICH);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
