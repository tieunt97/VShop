package com.example.tieu_nt.vshop.Model.LoadMore;

import com.example.tieu_nt.vshop.Model.TinTuc;

import java.util.List;

/**
 * Created by tieu_nt on 5/17/2018.
 */

public class TrangTinTuc {
    private List<TinTuc> dsTinTuc;
    private boolean trangCuoi;
    private String nextPage;

    public List<TinTuc> getDsTinTuc() {
        return dsTinTuc;
    }

    public void setDsTinTuc(List<TinTuc> dsTinTuc) {
        this.dsTinTuc = dsTinTuc;
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
