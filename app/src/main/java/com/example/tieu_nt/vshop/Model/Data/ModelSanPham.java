package com.example.tieu_nt.vshop.Model.Data;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.ChiTietSanPham;
import com.example.tieu_nt.vshop.Model.Constants;
import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDanhGia;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;

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

public class ModelSanPham {
    private static ModelSanPham modelSanPham;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private ModelSanPham(){}

    public static ModelSanPham getInstance(){
        if(modelSanPham == null)
            modelSanPham = new ModelSanPham();

        return modelSanPham;
    }

    public int laySoLuongTrongKho(int idSanPham){
        int soLuong = 0;
        DownloadJSON downloadJSON = new DownloadJSON(Constants.SERVER + "/products/" + idSanPham + "/getRestOfAmount");
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            soLuong = object.getInt("data");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return soLuong;
    }

    public TrangDanhGia layDanhSachDanhGia(String duongDan){
        TrangDanhGia trangDanhGia = new TrangDanhGia();
        List<DanhGia> dsDanhGia = new ArrayList<>();

        DownloadJSON downloadJSON = new DownloadJSON(duongDan);
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONObject data = object.getJSONObject("data");
            JSONArray arrayDanhGia = data.getJSONArray("data");
            for(int i = 0; i < arrayDanhGia.length(); i++){
                JSONObject obDanhGia = arrayDanhGia.getJSONObject(i);
                DanhGia danhGia = new DanhGia();
                danhGia.setTieuDe(obDanhGia.getString("title"));
                danhGia.setNoiDung(obDanhGia.getString("content"));
                danhGia.setSoSao((float) obDanhGia.getDouble("star_number"));
                danhGia.setThoiGian(sdf.parse(obDanhGia.getString("created_at")));
                danhGia.setTenKhachHang(obDanhGia.getString("customer_name"));

                dsDanhGia.add(danhGia);
            }

            int current_page = data.getInt("current_page");
            int last_page = data.getInt("last_page");
            if(current_page < last_page){
                trangDanhGia.setTrangCuoi(false);
            }else{
                trangDanhGia.setTrangCuoi(true);
            }
            trangDanhGia.setNextPage(data.getString("next_page_url"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        trangDanhGia.setDsDanhGia(dsDanhGia);
        return trangDanhGia;
    }

    public TrangSanPham layDanhSachSanPham(String duongDan){
        TrangSanPham trangSanPham = new TrangSanPham();
        List<SanPham> dsSanPham = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongDan);
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONObject data = object.getJSONObject("data");
            JSONArray arraySanPham = data.getJSONArray("data");
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

            int current_page = data.getInt("current_page");
            int last_page = data.getInt("last_page");
            if(current_page < last_page){
                trangSanPham.setTrangCuoi(false);
            }else{
                trangSanPham.setTrangCuoi(true);
            }
            trangSanPham.setNextPage(data.getString("next_page_url"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        trangSanPham.setDsSanPham(dsSanPham);
        return trangSanPham;
    }

    public SanPham layChiTietSanPham(int idSanPham){
        SanPham sanPham = new SanPham();
        DownloadJSON downloadJSON = new DownloadJSON(Constants.SERVER + "/products/" + idSanPham);
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONObject data = object.getJSONObject("data");
            sanPham.setIdSanPham(data.getInt("id"));
            sanPham.setTenSanPham(data.getString("product_name"));
            sanPham.setHinhSanPham(data.getString("main_image"));
            sanPham.setGiaChuan(data.getInt("base_price"));
            sanPham.setMoTa(data.getString("description"));
            sanPham.setThuongHieu(data.getString("provider"));
            sanPham.setSoLuongTonKho(data.getInt("quantity"));
            JSONArray dsHinh = data.getJSONArray("sub_images");
            List<String> dsHinhSP = new ArrayList<>();
            for(int i = 0; i < dsHinh.length(); i++){
                JSONArray arrayHinh = dsHinh.getJSONArray(i);
                dsHinhSP.add(arrayHinh.getString(0));
            }
            sanPham.setDsHinhSP(dsHinhSP);

            //lay danh sach so sao
            JSONObject star_info = data.getJSONObject("star_info");
            sanPham.setDanhGiaTB((float) star_info.getDouble("star_number_average"));
            sanPham.setSoLuotDanhGia(star_info.getInt("star_count_average"));
            JSONArray star_array = star_info.getJSONArray("star_detail");
            int length =star_array.length();
            if(length > 0){
                int dsSoSao[] = new int[5];
                for(int i = 0; i < length; i++){
                    JSONObject object1 = star_array.getJSONObject(i);
                    dsSoSao[i] = object1.getInt("number");
                }
                sanPham.setDsSoSao(dsSoSao);
            }

            //layDangGiaDauTien
            List<DanhGia> dsDanhGia = new ArrayList<>();
            JSONArray arrayDanhGia = data.getJSONArray("evaluationInfo");
            int soLuongDG = arrayDanhGia.length();
            for(int i = 0; i < soLuongDG; i++){
                JSONObject obDanhGia = arrayDanhGia.getJSONObject(i);
                DanhGia danhGia = new DanhGia();
                danhGia.setTieuDe(obDanhGia.getString("title"));
                danhGia.setNoiDung(obDanhGia.getString("content"));
                danhGia.setSoSao((float) obDanhGia.getDouble("star_number"));
                danhGia.setThoiGian(sdf.parse(obDanhGia.getString("created_at")));
                danhGia.setTenKhachHang(obDanhGia.getString("customer_name"));

                dsDanhGia.add(danhGia);
            }
            sanPham.setDsDanhGia(dsDanhGia);

            //lay chi tiet san pham
            JSONArray arrayChiTiet = data.getJSONArray("specificationsInfo");
            List<ChiTietSanPham> dsChiTietSP = new ArrayList<>();
            int size = arrayChiTiet.length();
            for(int i = 0; i < size; i++){
                JSONObject obChiTiet = arrayChiTiet.getJSONObject(i);
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setTenChiTiet(obChiTiet.getString("category_name"));
                ctsp.setGiaTri(obChiTiet.getString("value"));

                dsChiTietSP.add(ctsp);
            }
            sanPham.setDsChiTietSanPham(dsChiTietSP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sanPham;
    }
}
