package com.example.tieu_nt.vshop.Model.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDonHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.TinTuc;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangTinTuc;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class ModelKhachHang {
    private static ModelKhachHang modelKhachHang;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private ModelKhachHang(){}

    public static ModelKhachHang getInstance(){
        if (modelKhachHang == null)
            modelKhachHang = new ModelKhachHang();

        return modelKhachHang;
    }

    public boolean huyDonHang(int idDonHang){

        return false;
    }

    public boolean capNhatThongTin(String hoTen, String email, String soDT, String diaChi){
        return false;
    }

    public boolean xacNhanMuaHang(int idKhachHang, int idSanPham){
        return false;
    }

    public boolean danhGiaSanPham(DanhGia danhGia){

        return false;
    }

    public List<SanPham> layDanhSachSanPhamDonHang(int idDonHang){
        List<SanPham> dsSanPham = new ArrayList<>();

        return dsSanPham;
    }

    public TrangDonHang layDanhSachDonHang(String duongDan){
        TrangDonHang trangDonHang = new TrangDonHang();
        List<DonHang> dsDonHang = new ArrayList<>();

        trangDonHang.setDsDonHang(dsDonHang);
        return trangDonHang;
    }

    public TrangTinTuc layDanhSachTinTuc(String duongDan){
        TrangTinTuc trangTinTuc = new TrangTinTuc();
        List<TinTuc> dsTinTuc = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongDan);
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONArray arrayTinTuc = object.getJSONArray("data");
            for(int i = 0; i < arrayTinTuc.length(); i++){
                JSONObject obTinTuc = arrayTinTuc.getJSONObject(i);
                TinTuc tinTuc = new TinTuc();
                tinTuc.setIdTinTuc(obTinTuc.getInt("id"));
                tinTuc.setTieuDe(obTinTuc.getString("title"));
                tinTuc.setNoiDung(obTinTuc.getString("content"));
                tinTuc.setThoiGian(simpleDateFormat.parse(obTinTuc.getString("created_at")));

                dsTinTuc.add(tinTuc);
            }

            int current_page = object.getInt("current_page");
            int last_page = object.getInt("last_page");
            if(current_page < last_page){
                trangTinTuc.setTrangCuoi(false);
            }else{
                trangTinTuc.setTrangCuoi(true);
            }
            trangTinTuc.setNextPage(object.getString("next_page_url"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        trangTinTuc.setDsTinTuc(dsTinTuc);
        return trangTinTuc;
    }

    public boolean capNhatSanPhamYeuThich(int idKhachHang, int idSanPham){
        boolean b = false;

        return b;
    }

    public boolean themSanPhamYeuThich(int idSanPham){
        boolean b = false;
        DownloadJSON downloadJSON = new DownloadJSON(TrangChuActivity.SERVER + "/likes/customer/like?product_id=" + idSanPham);
        downloadJSON.execute();

        try {
            String dataJSON = downloadJSON.get();
            Log.d("THEMSP", dataJSON);
            JSONObject object = new JSONObject(dataJSON);
            b = object.getBoolean("success");
            Log.d("THEMSP", String.valueOf(b));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean xoaSanPhamYeuThich(int idSanPham){
        boolean b = false;

        return b;
    }
}
