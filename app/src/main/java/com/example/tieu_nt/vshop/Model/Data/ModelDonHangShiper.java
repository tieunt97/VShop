package com.example.tieu_nt.vshop.Model.Data;

import android.util.Log;

import com.example.tieu_nt.vshop.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDonHangShiper {
    public ArrayList<DonHang> getDanhSachDonHangShiper(String duongdan){
        ArrayList<DonHang> dsDonHang = new ArrayList<>();

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();

        try {
            String dataDonHang = downloadJSON.get();

            Log.d("datadonhang", dataDonHang);

            JSONObject object = new JSONObject(dataDonHang);
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length(); i++){
                DonHang donHang = new DonHang();
                NguoiDung nguoiDung = new NguoiDung();
                ArrayList<SanPham> dsSanPham = new ArrayList<>();

                JSONObject objDonhang = array.getJSONObject(i);
                donHang.setIdHoaDon(objDonhang.getInt("id"));
                donHang.setDiaChi(objDonhang.getString("destination_address"));
                donHang.setPhiShip(objDonhang.getInt("ship_fee"));
                //set khách hàng
                JSONArray arrCustomer = objDonhang.getJSONArray("customer_info");
                JSONObject objCustomer = arrCustomer.getJSONObject(0);

                nguoiDung.setIdNguoiDung(objDonhang.getInt("customer_id"));
                nguoiDung.setTenNguoiDung(objCustomer.getString("name"));
                nguoiDung.setSoDT(objCustomer.getString("phone_number"));
                donHang.setKhachHang(nguoiDung);

                //set ds sản phẩm
                JSONObject jsonOrderDetail = objDonhang.getJSONObject("order_detail");
                JSONArray arraySanPham = jsonOrderDetail.getJSONArray("info");
                for (int j = 0; j < arraySanPham.length(); j++){
                    JSONObject jsonSP = arraySanPham.getJSONObject(j);
                    SanPham sp = new SanPham();
                    sp.setTenSanPham(jsonSP.getString("product_name"));
                    sp.setGiaChuan(jsonSP.getInt("base_price"));
                    sp.setSoLuong(jsonSP.getInt("amount"));
                    sp.setHinhSanPham(jsonSP.getString("main_image"));
                    dsSanPham.add(sp);
                }
                donHang.setTongTienTT(jsonOrderDetail.getInt("total"));
                donHang.setDsSanPham(dsSanPham);

                dsDonHang.add(donHang);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }

    public ArrayList<DonHang> getDanhSachDonHangGiao(String links) {
        ArrayList<DonHang> dsDonHang = new ArrayList<>();

        DownloadJSON downloadJSON = new DownloadJSON(links);
        downloadJSON.execute();

        try {
            String dataDonHang = downloadJSON.get();

            Log.d("datadonhang", dataDonHang);

            JSONObject object = new JSONObject(dataDonHang);
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length(); i++){
                DonHang donHang = new DonHang();
                NguoiDung nguoiDung = new NguoiDung();
                ArrayList<SanPham> dsSanPham = new ArrayList<>();

                JSONObject objDonhang = array.getJSONObject(i);
                donHang.setIdHoaDon(objDonhang.getInt("id"));
                donHang.setDiaChi(objDonhang.getString("destination_address"));
                donHang.setPhiShip(objDonhang.getInt("ship_fee"));
                //set khách hàng
                JSONArray arrCustomer = objDonhang.getJSONArray("customer_info");
                JSONObject objCustomer = arrCustomer.getJSONObject(0);

                nguoiDung.setIdNguoiDung(objDonhang.getInt("customer_id"));
                nguoiDung.setTenNguoiDung(objCustomer.getString("name"));
                nguoiDung.setSoDT(objCustomer.getString("phone_number"));
                donHang.setKhachHang(nguoiDung);

                //set ds sản phẩm
                JSONObject jsonOrderDetail = objDonhang.getJSONObject("order_detail");
                JSONArray arraySanPham = jsonOrderDetail.getJSONArray("info");
                for (int j = 0; j < arraySanPham.length(); j++){
                    JSONObject jsonSP = arraySanPham.getJSONObject(j);
                    SanPham sp = new SanPham();
                    sp.setTenSanPham(jsonSP.getString("product_name"));
                    sp.setGiaChuan(jsonSP.getInt("base_price"));
                    sp.setSoLuong(jsonSP.getInt("amount"));
                    sp.setHinhSanPham(jsonSP.getString("main_image"));
                    dsSanPham.add(sp);
                }
                donHang.setTongTienTT(jsonOrderDetail.getInt("total"));
                donHang.setDsSanPham(dsSanPham);

                dsDonHang.add(donHang);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }

    public ArrayList<DonHang> getDanhSachLiichSuGiaoHang(String links) {
        ArrayList<DonHang> dsDonHang = new ArrayList<>();

        DownloadJSON downloadJSON = new DownloadJSON(links);
        downloadJSON.execute();

        try {
            String dataDonHang = downloadJSON.get();

            Log.d("datadonhang", dataDonHang);

            JSONObject object = new JSONObject(dataDonHang);
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length(); i++){
                DonHang donHang = new DonHang();
                NguoiDung nguoiDung = new NguoiDung();
                ArrayList<SanPham> dsSanPham = new ArrayList<>();

                JSONObject objDonhang = array.getJSONObject(i);
                donHang.setIdHoaDon(objDonhang.getInt("id"));
                donHang.setDiaChi(objDonhang.getString("destination_address"));
                donHang.setPhiShip(objDonhang.getInt("ship_fee"));
                //set khách hàng
                JSONArray arrCustomer = objDonhang.getJSONArray("customer_info");
                JSONObject objCustomer = arrCustomer.getJSONObject(0);

                nguoiDung.setIdNguoiDung(objDonhang.getInt("customer_id"));
                nguoiDung.setTenNguoiDung(objCustomer.getString("name"));
                nguoiDung.setSoDT(objCustomer.getString("phone_number"));
                donHang.setKhachHang(nguoiDung);

                //set ds sản phẩm
                JSONObject jsonOrderDetail = objDonhang.getJSONObject("order_detail");
                JSONArray arraySanPham = jsonOrderDetail.getJSONArray("info");
                for (int j = 0; j < arraySanPham.length(); j++){
                    JSONObject jsonSP = arraySanPham.getJSONObject(j);
                    SanPham sp = new SanPham();
                    sp.setTenSanPham(jsonSP.getString("product_name"));
                    sp.setGiaChuan(jsonSP.getInt("base_price"));
                    sp.setSoLuong(jsonSP.getInt("amount"));
                    sp.setHinhSanPham(jsonSP.getString("main_image"));
                    dsSanPham.add(sp);
                }
                donHang.setTongTienTT(jsonOrderDetail.getInt("total"));
                donHang.setDsSanPham(dsSanPham);

                dsDonHang.add(donHang);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }

    public boolean nhanDonHang(String links, int idDonHang) {
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        HashMap<String,String> hsIdDonHang = new HashMap<>();
        hsIdDonHang.put("sale_bill_id", idDonHang+"");

        attrs.add(hsIdDonHang);

        DownloadJSON downloadJSON = new DownloadJSON(links,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            Log.d("nhandonhang", dataJSON);
            JSONObject object = new JSONObject(dataJSON);
            boolean success = object.getBoolean("success");

            if (success){
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean giaoDonHang(String links, int idDonHang) {
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        HashMap<String,String> hsIdDonHang = new HashMap<>();
        hsIdDonHang.put("sale_bill_id", idDonHang+"");

        attrs.add(hsIdDonHang);

        DownloadJSON downloadJSON = new DownloadJSON(links,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            Log.d("giaodonhang", dataJSON);
            JSONObject object = new JSONObject(dataJSON);
            boolean success = object.getBoolean("success");

            if (success){
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
