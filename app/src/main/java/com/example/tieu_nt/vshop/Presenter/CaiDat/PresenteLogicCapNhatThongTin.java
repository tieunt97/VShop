package com.example.tieu_nt.vshop.Presenter.CaiDat;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;

/**
 * Created by tieu_nt on 5/24/2018.
 */

public class PresenteLogicCapNhatThongTin implements IPresenterCapNhatThongTin{
    private ModelKhachHang modelKhachHang;


    public PresenteLogicCapNhatThongTin() {
        modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public boolean capNhatThongTin(String hoTen, String email, String soDT, String diaChi) {
        return modelKhachHang.capNhatThongTin(hoTen, email, soDT, diaChi);
    }
}
