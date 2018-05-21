package com.example.tieu_nt.vshop.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class BottomSheetLocSanPham extends BottomSheetDialogFragment{
    private ArrayAdapter arrayAdapter;
    private String[] strings = {"Sam Sung", "Apple", "Nokia", "Xiaomi"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_locsanpham, container, false);
        final Spinner spinnerThuongHieu = view.findViewById(R.id.spinerThuongHieu);
        arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_item_spiner, R.id.tvThuongHieu, strings);
        spinnerThuongHieu.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        EditText edtToiThieu = view.findViewById(R.id.edtToiThieu);
        EditText edtToiDa = view.findViewById(R.id.edtToiDa);
        RatingBar rbDanhGia = view.findViewById(R.id.rbDanhGia);
        Button btnHuy = view.findViewById(R.id.btnXoa);
        Button btnHoanThanh = view.findViewById(R.id.btnHoanThanh);
        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), spinnerThuongHieu.getSelectedItemPosition() + " ", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
