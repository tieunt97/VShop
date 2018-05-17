package com.example.tieu_nt.vshop.Model.Data;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class ModelSanPham {
    private static ModelSanPham modelSanPham;

    private ModelSanPham(){}

    public static ModelSanPham getInstance(){
        if(modelSanPham == null)
            modelSanPham = new ModelSanPham();

        return modelSanPham;
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
        DownloadJSON downloadJSON = new DownloadJSON("http://172.20.10.7:8080/products/" + idSanPham);
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONObject data = object.getJSONObject("data");
            sanPham.setIdSanPham(data.getInt("id"));
            sanPham.setTenSanPham(data.getString("product_name"));
            sanPham.setGiaChuan(data.getInt("base_price"));
            sanPham.setMoTa(data.getString("description"));
            sanPham.setSoLuong(data.getInt("quantity"));
            JSONArray dsHinh = data.getJSONArray("sub_images");
            List<String> dsHinhSP = new ArrayList<>();
            for(int i = 0; i < dsHinh.length(); i++){
                dsHinhSP.add(dsHinh.getString(i));
            }
            sanPham.setDsHinhSP(dsHinhSP);
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }
}
