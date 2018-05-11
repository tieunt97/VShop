package com.example.tieu_nt.vshop.Model.Data;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class ModelDangNhapDangKy {
    private static ModelDangNhapDangKy modelDangNhapDangKy;
    private ModelDangNhapDangKy(){}

    public static ModelDangNhapDangKy getInstance(){
        if(modelDangNhapDangKy == null)
            modelDangNhapDangKy = new ModelDangNhapDangKy();

        return modelDangNhapDangKy;
    }

    public int dangNhap(String email, String matKhau){

        return 1;
    }

    public boolean dangKy(String email, String matKhau){

        return  false;
    }
}
