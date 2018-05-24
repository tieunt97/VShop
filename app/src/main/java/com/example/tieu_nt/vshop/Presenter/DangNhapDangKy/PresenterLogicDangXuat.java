package com.example.tieu_nt.vshop.Presenter.DangNhapDangKy;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;

/**
 * Created by tieu_nt on 5/24/2018.
 */

public class PresenterLogicDangXuat {
    private ModelKhachHang modelKhachHang;
    private static PresenterLogicDangXuat presenterLogicDangXuat;

    private PresenterLogicDangXuat(){
        modelKhachHang = ModelKhachHang.getInstance();
    }

    public static PresenterLogicDangXuat getInstance(){
        if(presenterLogicDangXuat == null)
            presenterLogicDangXuat = new PresenterLogicDangXuat();

        return presenterLogicDangXuat;
    }

    public boolean dangXuat(){
        return modelKhachHang.dangXuat();
    }
}
