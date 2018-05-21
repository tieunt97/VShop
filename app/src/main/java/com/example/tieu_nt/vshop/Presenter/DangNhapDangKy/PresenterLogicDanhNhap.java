package com.example.tieu_nt.vshop.Presenter.DangNhapDangKy;

import com.example.tieu_nt.vshop.Model.Data.ModelDangNhapDangKy;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.View.DangNhap.DangNhapActivity;
import com.example.tieu_nt.vshop.View.DangNhap.ViewDangNhapDangKy;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class PresenterLogicDanhNhap implements IPresenterDangNhap{
    private ViewDangNhapDangKy viewDangNhap;
    private ModelDangNhapDangKy modelDangNhapDangKy;

    public PresenterLogicDanhNhap(ViewDangNhapDangKy viewDangNhap) {
        this.viewDangNhap = viewDangNhap;
        this.modelDangNhapDangKy = ModelDangNhapDangKy.getInstance();
    }

    @Override
    public void kiemTraDangNhap(String email, String matKhau) {
        if(matKhau.equals("") && email.equals("")){
            viewDangNhap.thaoTacThatBai("Bạn chưa nhập Email và Mật khẩu");
            return;
        }else if(email.equals("")){
            viewDangNhap.thaoTacThatBai("Bạn chưa nhập Email");
            return;
        }else if(matKhau.equals("")){
            viewDangNhap.thaoTacThatBai("Bạn chưa nhập Mật khẩu");
            return;
        }else if(!DangNhapActivity.validate(email)){
            viewDangNhap.thaoTacThatBai("Email không hợp lệ");
            return;
        }else if(matKhau.length() < 6){
            viewDangNhap.thaoTacThatBai("Mật khẩu không hợp lệ, ít nhất 6 ký tự");
            return;
        }else{
            NguoiDung nguoiDung = modelDangNhapDangKy.dangNhap(email, matKhau);
            if(nguoiDung != null){
                viewDangNhap.thaoTacThanhCong(nguoiDung);
            }else{
                viewDangNhap.thaoTacThatBai("Email hoặc Mật khẩu không  đúng");
            }
        }
    }
}
