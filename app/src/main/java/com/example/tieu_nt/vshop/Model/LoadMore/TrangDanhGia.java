package com.example.tieu_nt.vshop.Model.LoadMore;

import com.example.tieu_nt.vshop.Model.DanhGia;

import java.util.List;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public class TrangDanhGia {
    private List<DanhGia> dsDanhGia;
    private String nextPage;
    private boolean trangCuoi;


    public List<DanhGia> getDsDanhGia() {
        return dsDanhGia;
    }

    public void setDsDanhGia(List<DanhGia> dsDanhGia) {
        this.dsDanhGia = dsDanhGia;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isTrangCuoi() {
        return trangCuoi;
    }

    public void setTrangCuoi(boolean trangCuoi) {
        this.trangCuoi = trangCuoi;
    }
}
