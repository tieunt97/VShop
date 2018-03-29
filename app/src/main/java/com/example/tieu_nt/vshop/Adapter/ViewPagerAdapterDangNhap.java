package com.example.tieu_nt.vshop.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tieu_nt.vshop.View.DangNhap.Fragment.FragmentDangKy;
import com.example.tieu_nt.vshop.View.DangNhap.Fragment.FragmentDangNhap;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter{
    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentDangNhap dangNhap = new FragmentDangNhap();
                return dangNhap;
            case 1:
                FragmentDangKy dangKy = new FragmentDangKy();
                return dangKy;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng ký";
            default:
                return null;
        }
    }
}
