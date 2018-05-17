package com.example.tieu_nt.vshop.View.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.Model.TaiKhoan;
import com.example.tieu_nt.vshop.View.DangNhap.ViewDangKy;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class FragmentDangKy extends Fragment implements View.OnClickListener, TextWatcher, View.OnTouchListener,
        ViewDangKy{
    private View view;
    private EditText edtHoTen, edtEmail;
    private TextInputEditText edtMatKhau, edtXacNhanMatKhau;
    private Button btnDangKy, btnBoQua;
    private TaiKhoan taiKhoan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);
        anhXa();

        setActions();
        return view;
    }

    private void anhXa(){
        edtHoTen = (EditText) view.findViewById(R.id.edtHoTen);
        edtEmail = (EditText) view.findViewById(R.id.edtEmailDangKy);
        edtMatKhau = (TextInputEditText) view.findViewById(R.id.edtMatKhauDangKy);
        edtXacNhanMatKhau = (TextInputEditText) view.findViewById(R.id.edtXacNhanMatKhauDangKy);
        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
        btnBoQua = (Button) view.findViewById(R.id.btnBoQuaDangKy);
    }

    private void setActions(){
        btnDangKy.setOnClickListener(this);
        btnBoQua.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangKy:
                break;
            case R.id.btnBoQuaDangKy:
                taiKhoan = new TaiKhoan();
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void dangKyThanhCong() {

    }

    @Override
    public void dangKyThatBai(String msg) {

    }
}