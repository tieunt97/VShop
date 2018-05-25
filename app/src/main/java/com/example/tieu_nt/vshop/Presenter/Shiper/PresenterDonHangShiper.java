package com.example.tieu_nt.vshop.Presenter.Shiper;

import com.example.tieu_nt.vshop.Model.Data.ModelDonHangShiper;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.View.Shiper.ViewDonHangShiper;

import java.util.ArrayList;

public class PresenterDonHangShiper implements IPresenterDonHangShiper {
    ViewDonHangShiper viewDonHangShiper;
    ModelDonHangShiper modelDonHangShiper;

    public PresenterDonHangShiper(ViewDonHangShiper viewDonHangShiper){
        this.viewDonHangShiper = viewDonHangShiper;
        modelDonHangShiper = new ModelDonHangShiper();
    }

    @Override
    public void layDanhSachNhanDonHang(String links) {
        ArrayList<DonHang> dsDonHang = modelDonHangShiper.getDanhSachDonHangShiper(links);

        if (dsDonHang.size() > 0){
            viewDonHangShiper.hienThiDsNhanDonHang(dsDonHang);
        }
    }

    @Override
    public void layDanhSachGiaoHang(String links) {
        ArrayList<DonHang> dsDonHang = modelDonHangShiper.getDanhSachDonHangGiao(links);

        if (dsDonHang.size() > 0){
            viewDonHangShiper.hienThiDsDonHangGiao(dsDonHang);
        }
    }

    @Override
    public void layDanhSachLichSuGiao(String links) {
        ArrayList<DonHang> dsDonHang = modelDonHangShiper.getDanhSachLiichSuGiaoHang(links);

        if (dsDonHang.size() > 0){
            viewDonHangShiper.hienThiDsLichSuGiaoHang(dsDonHang);
        }
    }

    @Override
    public void nhanDonHang(String links, int idDonHang) {
        boolean check = modelDonHangShiper.nhanDonHang(links, idDonHang);

        if (check){
            viewDonHangShiper.nhanHangThanhCong();
        }else{
            viewDonHangShiper.nhanHangThatBai();
        }
    }

    @Override
    public void giaoHang(String links, int idDonHang) {
        boolean check = modelDonHangShiper.giaoDonHang(links, idDonHang);

        if (check){
            viewDonHangShiper.nhanHangThanhCong();
        }else{
            viewDonHangShiper.nhanHangThatBai();
        }
    }


}
