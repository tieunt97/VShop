package com.example.tieu_nt.vshop.View.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.DangNhap;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Presenter.DangNhapDangKy.PresenterLogicDangKy;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.DangNhap.ViewDangNhapDangKy;
import com.example.tieu_nt.vshop.View.Shiper.ShiperActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class FragmentDangKy extends Fragment implements View.OnClickListener, TextWatcher, View.OnTouchListener,
        ViewDangNhapDangKy{
    private View view;
    private EditText edtHoTen, edtEmail;
    private TextInputEditText edtMatKhau, edtXacNhanMatKhau;
    private Button btnDangKy, btnBoQua;
    private PresenterLogicDangKy presenterLogicDangKy;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);
        anhXa();

        presenterLogicDangKy = new PresenterLogicDangKy(this);

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
                presenterLogicDangKy.dangKyTaiKhoan(edtHoTen.getText().toString(), edtEmail.getText().toString(),
                        edtMatKhau.getText().toString(), edtXacNhanMatKhau.getText().toString());
                break;
            case R.id.btnBoQuaDangKy:
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
    public void thaoTacThanhCong(NguoiDung nguoiDung) {
        DangNhap.getInstance().setNguoiDung(nguoiDung);
        if(nguoiDung.getLevel() == NguoiDung.LEVEL_KHACHHANG){
            Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
            startActivity(iTrangChu);
        }else if(nguoiDung.getLevel() == NguoiDung.LEVEL_NVGIAOHANG){
            Intent iShiper = new Intent(getActivity(), ShiperActivity.class);
            startActivity(iShiper);
        }
    }

    @Override
    public void thaoTacThatBai(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog_thongbao_vshop, null, false);
        TextView tvNoiDung = (TextView) view.findViewById(R.id.tvNoiDung);
        tvNoiDung.setText(msg);
        Button btnDong = (Button) view.findViewById(R.id.btnDong);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        //đóng sau 3s
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    if(alertDialog.isShowing())
                        alertDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}