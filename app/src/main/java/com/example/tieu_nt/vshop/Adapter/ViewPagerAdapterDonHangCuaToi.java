package com.example.tieu_nt.vshop.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tieu_nt.vshop.View.DonHangCuaToi.Fragment.FragmentDonHangCuaToi;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.Fragment.FragmentDonHangDaHuy;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.Fragment.FragmentDonHangHoanTra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class ViewPagerAdapterDonHangCuaToi extends FragmentPagerAdapter {
    List<Fragment> list;
    List<String> titles;

    public ViewPagerAdapterDonHangCuaToi(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        list.add(new FragmentDonHangCuaToi());
        list.add(new FragmentDonHangHoanTra());
        list.add(new FragmentDonHangDaHuy());

        titles = new ArrayList<>();
        titles.add("Đơn hàng của tôi");
        titles.add("Hoàn trả");
        titles.add("Đơn hàng đã hủy");
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
