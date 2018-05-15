package com.example.tieu_nt.vshop.View.DonHangCuaToi.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Adapter.AdapterDonHangCuaToi;
import com.example.tieu_nt.vshop.Model.DonHang;
import com.example.tieu_nt.vshop.Model.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMoreScroll;
import com.example.tieu_nt.vshop.Presenter.DonHangCuaToi.PresenterLogicDonHangCuaToi;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.ViewHienThiDanhSachDonHang;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import java.util.List;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class FragmentDonHangDaHuy extends Fragment implements ViewHienThiDanhSachDonHang, ILoadMore {
    private RecyclerView recyclerDonHang;
    private TextView tvThongBao;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterDonHangCuaToi adapterDonHangCuaToi;
    private List<DonHang> dsDonHang;
    private int idKhachHang = 0;
    private PresenterLogicDonHangCuaToi presenterLogicDonHangCuaToi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pager_donhangcuatoi, container, false);
        recyclerDonHang = view.findViewById(R.id.recyclerDonHang);
        tvThongBao = view.findViewById(R.id.tvThongBao);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerDonHang.setLayoutManager(layoutManager);
        if(TrangChuActivity.khachHang != null){
            idKhachHang = idKhachHang;
        }
        presenterLogicDonHangCuaToi = new PresenterLogicDonHangCuaToi(this);
        presenterLogicDonHangCuaToi.layDanhSachDonHang("");

        return view;
    }

    @Override
    public void hienThiDanhSachDonHang(List<DonHang> dsDonHang) {
        this.dsDonHang = dsDonHang;
        tvThongBao.setVisibility(View.GONE);
        adapterDonHangCuaToi = new AdapterDonHangCuaToi(getActivity(), dsDonHang, 1);
        recyclerDonHang.setAdapter(adapterDonHangCuaToi);
        recyclerDonHang.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        recyclerDonHang.post(new Runnable() {
            @Override
            public void run() {
                adapterDonHangCuaToi.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        List<DonHang> donHangLoadMore = presenterLogicDonHangCuaToi.layDanhSachDonHangLoadMore(duongDan);
        if(donHangLoadMore.size() > 0){
            this.dsDonHang.addAll(donHangLoadMore);
            recyclerDonHang.post(new Runnable() {
                @Override
                public void run() {
                    adapterDonHangCuaToi.notifyDataSetChanged();
                }
            });
        }
    }
}
