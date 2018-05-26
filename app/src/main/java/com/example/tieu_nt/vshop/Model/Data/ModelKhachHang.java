package com.example.tieu_nt.vshop.Model.Data;

import android.util.Log;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.Constants;
import com.example.tieu_nt.vshop.Model.DonHang;
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
import java.util.HashMap;
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

    public boolean dangXuat(){
        boolean b = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(TrangChuActivity.API_DANGXUAT, attrs);
        downloadJSON.execute();

        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            b = object.getBoolean("data");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public List<SanPham> layDanhSachSanPhamYeuThich(String duongDan){
        List<SanPham> dsSanPham = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongDan);
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONArray arraySanPham = object.getJSONArray("data");
            for(int i = 0; i < arraySanPham.length(); i++){
                JSONObject objectSanPham = arraySanPham.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setIdSanPham(objectSanPham.getInt("id"));
                sanPham.setTenSanPham(objectSanPham.getString("product_name"));
                sanPham.setHinhSanPham(objectSanPham.getString("main_image"));
                sanPham.setGiaChuan(objectSanPham.getInt("base_price"));
                sanPham.setDanhGiaTB((float) objectSanPham.getDouble("star_number"));
                sanPham.setSoLuotDanhGia(objectSanPham.getInt("star_count"));

                dsSanPham.add(sanPham);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dsSanPham;
    }

    public boolean huyDonHang(int idDonHang){

        return false;
    }

    public boolean capNhatThongTin(String hoTen, String email, String soDT, String diaChi){
        return false;
    }

    public boolean xacNhanMuaHang(String token, DonHang donHang){
        boolean b = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongDan = Constants.SERVER + "/api/user/customer/order_book";

        HashMap<String, String> hsToken = new HashMap<>();
        hsToken.put("api_token", token);

        HashMap<String, String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("destination_address", token);

        String product = "";
        int size = donHang.getDsSanPham().size();
        for(int i = 0; i < size; i++){
            SanPham sp = donHang.getDsSanPham().get(i);
            if(i == 0){
                product += sp.getIdSanPham() + "," + sp.getSoLuong();
            }else{
                product += "," + sp.getIdSanPham() + "," + sp.getSoLuong();
            }
        }
        HashMap<String, String> hsSP = new HashMap<>();
        hsSP.put("products", product);
        Log.d("DONHANG", token);
        Log.d("DONHANG", donHang.getDiaChi());
        Log.d("DONHANG", product);
        attrs.add(hsToken);
        attrs.add(hsDiaChi);
        attrs.add(hsSP);

        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            b = object.getBoolean("success");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return b;
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

    public boolean capNhatSanPhamYeuThich(String token, boolean isThich, int idSanPham){
        boolean b = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsToken = new HashMap<>();
        hsToken.put("api_token", token);

        HashMap<String,String> hsIdSP = new HashMap<>();
        hsIdSP.put("product_id", String.valueOf(idSanPham));

        attrs.add(hsToken);
        attrs.add(hsIdSP);
        String duongDan;
        if(isThich){
            duongDan = Constants.SERVER + "/api/likes/customer/like";
        }else{
            duongDan = Constants.SERVER + "/api/likes/customer/dislike";
        }
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();

        try {
            String dataJSON = downloadJSON.get();
            Log.d("dataJSONYT", dataJSON);
            JSONObject object = new JSONObject(dataJSON);
            b = object.getBoolean("success");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean kiemTraSanPhamYeuThich(String token, int idSanPham){
        boolean b = false;
        String duongDan = Constants.SERVER + "/api/likes/customer/is_liked?api_token=" + token + "&product_id=" + idSanPham;
        DownloadJSON downloadJSON = new DownloadJSON(duongDan);
        downloadJSON.execute();

        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            b = object.getBoolean("data");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}