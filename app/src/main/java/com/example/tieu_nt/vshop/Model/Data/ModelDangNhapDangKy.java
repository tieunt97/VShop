package com.example.tieu_nt.vshop.Model.Data;

import android.util.Log;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class ModelDangNhapDangKy {
    private static ModelDangNhapDangKy modelDangNhapDangKy;
    private ModelDangNhapDangKy(){}

    public static ModelDangNhapDangKy getInstance(){
        if(modelDangNhapDangKy == null)
            modelDangNhapDangKy = new ModelDangNhapDangKy();

        return modelDangNhapDangKy;
    }

    public NguoiDung dangNhap(String email, String matKhau){
        NguoiDung nguoiDung = null;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.API_DANGNHAP;

        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email", email);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("password", matKhau);

        attrs.add(hsEmail);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            boolean success = object.getBoolean("success");
            if(success){
                JSONObject obNguoiDung = object.getJSONObject("data");
                nguoiDung = new NguoiDung();
                nguoiDung.setIdNguoiDung(obNguoiDung.getInt("id"));
                nguoiDung.setTenNguoiDung(obNguoiDung.getString("name"));
                nguoiDung.setSoDT(obNguoiDung.getString("phone_number"));
                nguoiDung.setDiaChi(obNguoiDung.getString("address"));
                nguoiDung.setEmail(obNguoiDung.getString("email"));
                nguoiDung.setLevel(obNguoiDung.getInt("level"));
                nguoiDung.setToken(obNguoiDung.getString("remember_token"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nguoiDung;
    }

    public NguoiDung dangKy(String hoTen, String email, String matKhau){
        NguoiDung nguoiDung = null;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.API_DANGKY;

        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email", email);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("password", matKhau);

        HashMap<String,String> hsName = new HashMap<>();
        hsName.put("name", hoTen);

        HashMap<String,String> hsLevel = new HashMap<>();
        hsLevel.put("level", String.valueOf(NguoiDung.LEVEL_KHACHHANG));

        attrs.add(hsEmail);
        attrs.add(hsMatKhau);
        attrs.add(hsName);
        attrs.add(hsLevel);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            boolean success = object.getBoolean("success");
            if(success){
                JSONObject obNguoiDung = object.getJSONObject("data");
                nguoiDung = new NguoiDung();
                nguoiDung.setIdNguoiDung(obNguoiDung.getInt("id"));
                nguoiDung.setTenNguoiDung(obNguoiDung.getString("name"));
                nguoiDung.setEmail(obNguoiDung.getString("email"));
                nguoiDung.setLevel(NguoiDung.LEVEL_KHACHHANG);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nguoiDung;
    }
}
