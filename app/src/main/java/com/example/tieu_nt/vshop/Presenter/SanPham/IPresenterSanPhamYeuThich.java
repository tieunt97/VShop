package com.example.tieu_nt.vshop.Presenter.SanPham;

/**
 * Created by tieu_nt on 5/24/2018.
 */

public interface IPresenterSanPhamYeuThich {
    boolean kiemTraSanPham(String token, int idSanPham);
    boolean capNhatSanPhamYeuThich(String token, boolean isThich, int idSanPham);
}
