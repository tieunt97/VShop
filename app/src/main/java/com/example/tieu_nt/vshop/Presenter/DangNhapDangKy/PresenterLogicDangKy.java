package com.example.tieu_nt.vshop.Presenter.DangNhapDangKy;

import com.example.tieu_nt.vshop.Model.Data.ModelDangNhapDangKy;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.View.DangNhap.DangNhapActivity;
import com.example.tieu_nt.vshop.View.DangNhap.ViewDangNhapDangKy;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public class PresenterLogicDangKy implements IPresenterDangKy{
    private ViewDangNhapDangKy viewDangNhapDangKy;
    private ModelDangNhapDangKy modelDangNhapDangKy;


    public PresenterLogicDangKy(ViewDangNhapDangKy viewDangNhapDangKy) {
        this.viewDangNhapDangKy = viewDangNhapDangKy;
        this.modelDangNhapDangKy = ModelDangNhapDangKy.getInstance();
    }

    @Override
    public void dangKyTaiKhoan(String hoTen, String email, String matKhau, String xacNhanMK) {
        if(hoTen.equals("")){
            viewDangNhapDangKy.thaoTacThatBai("Bạn chưa nhập Họ tên");
        }else if(email.equals("")){
            viewDangNhapDangKy.thaoTacThatBai("Bạn chưa nhập Email");
        }else if(!DangNhapActivity.validate(email)){
            viewDangNhapDangKy.thaoTacThatBai("Email không hợp lệ");
        }else if(matKhau.equals("")){
            viewDangNhapDangKy.thaoTacThatBai("Bạn chưa nhập Mật khẩu");
        }else if (matKhau.length() < 6){
            viewDangNhapDangKy.thaoTacThatBai("Độ dài mật khẩu ít nhất 6 ký tự");
        }else if(!xacNhanMK.equals(matKhau)){
            viewDangNhapDangKy.thaoTacThatBai("Mật khẩu xác nhận không khớp");
        }else{
            NguoiDung nguoiDung = modelDangNhapDangKy.dangKy(hoTen, email, matKhau);
            if(nguoiDung == null){
                viewDangNhapDangKy.thaoTacThatBai("Email này đã được đăng ký");
            }else{
                viewDangNhapDangKy.thaoTacThanhCong(nguoiDung);
            }
        }
    }
}
