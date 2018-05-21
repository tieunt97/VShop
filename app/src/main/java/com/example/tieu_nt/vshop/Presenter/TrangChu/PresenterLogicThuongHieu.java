package com.example.tieu_nt.vshop.Presenter.TrangChu;

import com.example.tieu_nt.vshop.Model.Data.ModelThuongHieu;
import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.View.TrangChu.ViewHienThiDanhSachThuongHieu;

import java.util.List;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterLogicThuongHieu implements IPresenterThuongHieu{
    private ViewHienThiDanhSachThuongHieu viewHienThiDanhSachThuongHieu;
    private ModelThuongHieu modelThuongHieu;


    public PresenterLogicThuongHieu(ViewHienThiDanhSachThuongHieu viewHienThiDanhSachThuongHieu) {
        this.viewHienThiDanhSachThuongHieu = viewHienThiDanhSachThuongHieu;
        this.modelThuongHieu = ModelThuongHieu.getInstance();
    }

    @Override
    public void layDanhSachThuongHieu() {
        List<ThuongHieu> dsThuongHieu = modelThuongHieu.layTatCaThuongHieu();
        if(dsThuongHieu.size() > 0){
            viewHienThiDanhSachThuongHieu.hienThiThuongHieu(dsThuongHieu);
        }
    }
}
