package com.example.tieu_nt.vshop.Presenter.DonHangCuaToi;

import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.ViewHienThiDanhSachDonHang;

import java.util.List;

/**
 * Created by tieu_nt on 5/15/2018.
 */

public class PresenterLogicDonHangCuaToi implements IPresenterDonHangCuaToi{
    private ViewHienThiDanhSachDonHang viewHienThiDanhSachDonHang;
    private ModelKhachHang modelKhachHang;


    public PresenterLogicDonHangCuaToi(ViewHienThiDanhSachDonHang viewHienThiDanhSachDonHang) {
        this.viewHienThiDanhSachDonHang = viewHienThiDanhSachDonHang;
        modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDanhSachDonHang(String duongDan) {
        List<DonHang> dsDonHang = modelKhachHang.layDanhSachDonHang(duongDan);
        if(dsDonHang.size() > 0){
            viewHienThiDanhSachDonHang.hienThiDanhSachDonHang(dsDonHang);
        }
    }

    @Override
    public List<DonHang> layDanhSachDonHangLoadMore(String duongDan) {
        List<DonHang> donHangLoadMore = modelKhachHang.layDanhSachDonHang(duongDan);

        return donHangLoadMore;
    }
}
