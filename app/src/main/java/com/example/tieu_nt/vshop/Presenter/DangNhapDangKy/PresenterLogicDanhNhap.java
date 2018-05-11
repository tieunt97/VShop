package com.example.tieu_nt.vshop.Presenter.DangNhapDangKy;

import com.example.tieu_nt.vshop.Model.Data.ModelDangNhapDangKy;
import com.example.tieu_nt.vshop.View.DangNhap.DangNhapActivity;
import com.example.tieu_nt.vshop.View.DangNhap.ViewDangNhap;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterLogicDanhNhap implements IPresenterDangNhap{
    private ViewDangNhap viewDangNhap;
    private ModelDangNhapDangKy modelDangNhapDangKy;

    public PresenterLogicDanhNhap(ViewDangNhap viewDangNhap) {
        this.viewDangNhap = viewDangNhap;
        this.modelDangNhapDangKy = ModelDangNhapDangKy.getInstance();
    }

    @Override
    public void kiemTraDangNhap(String email, String matKhau) {
        if(matKhau.equals("") && email.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Email và Mật khẩu");
            return;
        }else if(email.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Email");
            return;
        }else if(matKhau.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Mật khẩu");
            return;
        }else if(!DangNhapActivity.validate(email)){
            viewDangNhap.dangNhapThatBai("Email không hợp lệ");
            return;
        }else if(matKhau.length() < 6){
            viewDangNhap.dangNhapThatBai("Mật khẩu không hợp lệ, ít nhất 6 ký tự");
            return;
        }else{
            int idKhachHang = modelDangNhapDangKy.dangNhap(email, matKhau);
            if(idKhachHang > 0){
                viewDangNhap.dangNhapThanhCong(idKhachHang);
            }else{
                viewDangNhap.dangNhapThatBai("Email hoặc Mật khẩu không  đúng");
            }
        }
    }
}
