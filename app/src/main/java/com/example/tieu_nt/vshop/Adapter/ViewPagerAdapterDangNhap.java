package com.example.tieu_nt.vshop.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tieu_nt.vshop.View.DangNhap.Fragment.FragmentDangKy;
import com.example.tieu_nt.vshop.View.DangNhap.Fragment.FragmentDangNhap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter{
    private List<Fragment> dsFragment;
    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
        dsFragment = new ArrayList<>();
        dsFragment.add(new FragmentDangNhap());
        dsFragment.add(new FragmentDangKy());
    }

    @Override
    public Fragment getItem(int position) {
        return dsFragment.get(position);
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
