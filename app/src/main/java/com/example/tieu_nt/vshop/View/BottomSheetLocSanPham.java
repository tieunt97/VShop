package com.example.tieu_nt.vshop.View;

import android.app.Activity;
import android.content.Context;
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

import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.Presenter.TrangChu.PresenterLogicThuongHieu;
import com.example.tieu_nt.vshop.R;

import java.util.List;

/**
 * Created by tieu_nt on 5/18/2018.
 */

public class BottomSheetLocSanPham extends BottomSheetDialogFragment{
    private ArrayAdapter arrayAdapter;
    private String[] strings;
    private PresenterLogicThuongHieu presenterLogicThuongHieu;
    private LocSanPham locSanPham;

    //dung cho phien ban 5.0 tro len
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        locSanPham = (LocSanPham) context;
    }

    //dung cho phien ban 5.0 tro xuong
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        locSanPham = (LocSanPham) activity;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_locsanpham, container, false);
        final Spinner spinnerThuongHieu = view.findViewById(R.id.spinerThuongHieu);
        presenterLogicThuongHieu = new PresenterLogicThuongHieu();
        final List<ThuongHieu> dsThuongHieu = presenterLogicThuongHieu.layDSThuongHieuLoc();
        int size = dsThuongHieu.size();
        strings = new String[size];
        for (int i = 0; i < size; i++){
            strings[i] = dsThuongHieu.get(i).getTenThuongHieu();
        }
        arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_item_spiner, R.id.tvThuongHieu, strings);
        spinnerThuongHieu.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        final EditText edtToiThieu = view.findViewById(R.id.edtToiThieu);
        final EditText edtToiDa = view.findViewById(R.id.edtToiDa);
        final RatingBar rbDanhGia = view.findViewById(R.id.rbDanhGia);
        Button btnHuy = view.findViewById(R.id.btnXoa);
        Button btnHoanThanh = view.findViewById(R.id.btnHoanThanh);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtToiThieu.setText("");
                edtToiDa.setText("");
                rbDanhGia.setRating(0);
            }
        });

        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idThuongHieu = dsThuongHieu.get(spinnerThuongHieu.getSelectedItemPosition()).getIdThuongHieu();
                int giaThap = 0;
                int giaCao = 0;
                try {
                    giaThap = Integer.parseInt(edtToiThieu.getText().toString());
                    giaCao = Integer.parseInt(edtToiDa.getText().toString());
                }catch (NumberFormatException exc){

                }
                locSanPham.locSanPham(idThuongHieu, giaThap, giaCao, rbDanhGia.getRating());
            }
        });

        return view;
    }
}
