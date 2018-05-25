package com.example.tieu_nt.vshop.Model;

/**
 * Created by tieu_nt on 5/25/2018.
 */

public class DangNhap {
    private static DangNhap dangNhap;
    private NguoiDung nguoiDung;

    private DangNhap(){

    }

    public static DangNhap getInstance(){
        if(dangNhap == null){
            dangNhap = new DangNhap();
        }

        return dangNhap;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
