package com.example.tieu_nt.vshop.Model.LoadMore;

import com.example.tieu_nt.vshop.Model.DonHang;

import java.util.List;

/**
 * Created by tieu_nt on 5/17/2018.
 */

public class TrangDonHang {
    private List<DonHang> dsDonHang;
    private boolean trangCuoi;
    private String nextPage;


    public List<DonHang> getDsDonHang() {
        return dsDonHang;
    }

    public void setDsDonHang(List<DonHang> dsDonHang) {
        this.dsDonHang = dsDonHang;
    }

    public boolean isTrangCuoi() {
        return trangCuoi;
    }

    public void setTrangCuoi(boolean trangCuoi) {
        this.trangCuoi = trangCuoi;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
