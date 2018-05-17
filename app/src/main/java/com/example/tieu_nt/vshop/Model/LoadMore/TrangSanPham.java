package com.example.tieu_nt.vshop.Model.LoadMore;

import com.example.tieu_nt.vshop.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/17/2018.
 */

public class TrangSanPham {
    private List<SanPham> dsSanPham;
    private String nextPage;
    private boolean trangCuoi;

    public List<SanPham> getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
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
