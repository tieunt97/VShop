package com.example.tieu_nt.vshop.View.TrangChu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/21/2018.
 */

public class BottomSheetDanhGiaSanPham extends BottomSheetDialogFragment{
    private RatingBar rbDanhGia;
    private EditText edtTieuDe, edtNoiDung;
    Button btnDanhGia, btnHuy;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_danhgiasanpham, container, false);
        rbDanhGia = view.findViewById(R.id.rbDanhGia);
        edtTieuDe = view.findViewById(R.id.edtTieuDe);
        edtTieuDe = view.findViewById(R.id.edtNoiDung);
        btnDanhGia = view.findViewById(R.id.btnDanhGia);
        btnHuy = view.findViewById(R.id.btnHuy);

        return view;
    }
}
