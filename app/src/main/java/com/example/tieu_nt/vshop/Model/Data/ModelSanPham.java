package com.example.tieu_nt.vshop.Model.Data;

import com.example.tieu_nt.vshop.Model.SanPham;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class ModelSanPham {
    private static ModelSanPham modelSanPham;

    private ModelSanPham(){}

    public static ModelSanPham getInstance(){
        if(modelSanPham == null)
            modelSanPham = new ModelSanPham();

        return modelSanPham;
    }

    public void layChiTietSanPham(SanPham sanPham){

    }
}
