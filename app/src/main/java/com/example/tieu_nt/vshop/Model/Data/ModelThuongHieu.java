package com.example.tieu_nt.vshop.Model.Data;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public class ModelThuongHieu {
    private static ModelThuongHieu modelThuongHieu;

    private ModelThuongHieu(){

    }

    public static ModelThuongHieu getInstance(){
        if(modelThuongHieu == null)
            modelThuongHieu = new ModelThuongHieu();

        return modelThuongHieu;
    }

    public List<ThuongHieu> layTatCaThuongHieu(){
        List<ThuongHieu> dsThuongHieu = new ArrayList<>();

        DownloadJSON downloadJSON = new DownloadJSON(TrangChuActivity.API_THUONGHIEU);
        downloadJSON.execute();

        try {
            String dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONArray arrayThuongHieu = object.getJSONArray("data");
            int size = arrayThuongHieu.length();
            for(int i = 0; i < size; i++){
                JSONObject obThuongHieu = arrayThuongHieu.getJSONObject(i);
                ThuongHieu thuongHieu = new ThuongHieu();
                thuongHieu.setIdThuongHieu(obThuongHieu.getInt("id"));
                thuongHieu.setTenThuongHieu(obThuongHieu.getString("name"));
                thuongHieu.setHinhThuongHieu(obThuongHieu.getString("icon"));

                dsThuongHieu.add(thuongHieu);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dsThuongHieu;
    }
}
