package com.example.tieu_nt.vshop.Model.Data;

import android.util.Log;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.SanPham;

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

    public List<SanPham> layDanhSachSanPham(String duongDan){
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

    public void layChiTietSanPham(SanPham sanPham){
        DownloadJSON downloadJSON = new DownloadJSON("http://172.20.10.7:8080/1/products");
        downloadJSON.execute();
        try {
            String dataJSON = downloadJSON.get();
            Log.d("dataJSOn", dataJSON);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
