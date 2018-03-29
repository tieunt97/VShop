package com.example.tieu_nt.vshop.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tieu_nt.vshop.View.TrangChu.FragmentChuongTrinhKhuyenMai;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentDienTu;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentLamDep;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentMeVaBe;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentNhaCuaVaDoiSong;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentNoiBat;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentTheThaoVaDuLich;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentThoiTrang;
import com.example.tieu_nt.vshop.View.TrangChu.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 3/15/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> list = new ArrayList<>();
    private List<String> titles = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list.add(new FragmentNoiBat());
        list.add(new FragmentChuongTrinhKhuyenMai());
        list.add(new FragmentDienTu());
        list.add(new FragmentNhaCuaVaDoiSong());
        list.add(new FragmentMeVaBe());
        list.add(new FragmentLamDep());
        list.add(new FragmentThoiTrang());
        list.add(new FragmentTheThaoVaDuLich());
        list.add(new FragmentThuongHieu());

        titles.add("Nổi bật");
        titles.add("Chương trình khuyến mãi");
        titles.add("Điên tử");
        titles.add("Nhà cửa và đời sống");
        titles.add("Mẹ và bé");
        titles.add("Làm đẹp");
        titles.add("Thời trang");
        titles.add("Thể thao & du lịch");
        titles.add("Thương hiệu");
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
