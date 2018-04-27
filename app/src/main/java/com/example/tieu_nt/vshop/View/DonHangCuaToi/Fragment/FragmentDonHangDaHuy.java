package com.example.tieu_nt.vshop.View.DonHangCuaToi.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class FragmentDonHangDaHuy extends Fragment{
    private RecyclerView recyclerDonHang;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pager_donhangcuatoi, container, false);
        recyclerDonHang = view.findViewById(R.id.recyclerDonHang);

        return view;
    }
}
