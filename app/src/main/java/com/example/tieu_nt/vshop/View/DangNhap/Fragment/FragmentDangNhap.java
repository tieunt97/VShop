package com.example.tieu_nt.vshop.View.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Model.DangNhap;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Presenter.DangNhapDangKy.PresenterLogicDanhNhap;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.DangNhap.ViewDangNhapDangKy;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class FragmentDangNhap extends Fragment implements View.OnClickListener, ViewDangNhapDangKy {
    private EditText edtEmail;
    private TextInputEditText edtMatKhau;
    private Button btnDangNhap, btnBoQua;
    private View view;
    private AlertDialog.Builder builder;
    private PresenterLogicDanhNhap presenterDanhNhap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
        anhXa();

        setActions();
        presenterDanhNhap = new PresenterLogicDanhNhap(this);

        return view;
    }

    private void anhXa(){
        edtEmail = (EditText) view.findViewById(R.id.edtEmailDangNhap);
        edtMatKhau = (TextInputEditText) view.findViewById(R.id.edtMatKhauDangNhap);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        btnBoQua = (Button) view.findViewById(R.id.btnBoQuaDangNhap);
    }

    private void setActions(){
        btnDangNhap.setOnClickListener(this);
        btnBoQua.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangNhap:
                presenterDanhNhap.kiemTraDangNhap(edtEmail.getText().toString(),  edtMatKhau.getText().toString());
                break;
            case R.id.btnBoQuaDangNhap:
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void thaoTacThanhCong(NguoiDung nguoiDung) {
        DangNhap.getInstance().setNguoiDung(nguoiDung);
        if(nguoiDung.getLevel() == NguoiDung.LEVEL_KHACHHANG){
            Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
            startActivity(iTrangChu);
        }
    }

    @Override
    public void thaoTacThatBai(String msg) {
        builder = new AlertDialog.Builder(getActivity());
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
