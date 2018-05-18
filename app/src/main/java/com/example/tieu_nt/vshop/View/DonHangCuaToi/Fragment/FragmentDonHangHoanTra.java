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
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDonHang;
import com.example.tieu_nt.vshop.Presenter.DonHangCuaToi.PresenterLogicDonHangCuaToi;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.DonHangCuaToi.ViewHienThiDanhSachDonHang;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

import java.util.List;

/**
 * Created by tieu_nt on 4/27/2018.
 */

public class FragmentDonHangHoanTra extends Fragment implements ViewHienThiDanhSachDonHang, ILoadMore {
    private RecyclerView recyclerDonHang;
    private TextView tvThongBao;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterDonHangCuaToi adapterDonHangCuaToi;
    private TrangDonHang trangDonHang;
    private List<DonHang> dsDonHang;
    private int idKhachHang = 0;
    private String duongDan = "";
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
        presenterLogicDonHangCuaToi.layDanhSachDonHang(duongDan);

        return view;
    }

    @Override
    public void hienThiDanhSachDonHang(TrangDonHang trangDonHang) {
        this.trangDonHang = trangDonHang;
        this.dsDonHang = this.trangDonHang.getDsDonHang();
        tvThongBao.setVisibility(View.GONE);
        adapterDonHangCuaToi = new AdapterDonHangCuaToi(getActivity(), dsDonHang);
        recyclerDonHang.setAdapter(adapterDonHangCuaToi);
        recyclerDonHang.addOnScrollListener(new LoadMoreScroll(layoutManager, this,
                this.trangDonHang.isTrangCuoi(), this.trangDonHang.getNextPage()));
        recyclerDonHang.post(new Runnable() {
            @Override
            public void run() {
                adapterDonHangCuaToi.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(String duongDan) {
        trangDonHang = presenterLogicDonHangCuaToi.layDanhSachDonHangLoadMore(duongDan);
        if(trangDonHang.getDsDonHang().size() > 0){
            this.dsDonHang.addAll(trangDonHang.getDsDonHang());
            recyclerDonHang.post(new Runnable() {
                @Override
                public void run() {
                    adapterDonHangCuaToi.notifyDataSetChanged();
                }
            });
        }
    }
}
