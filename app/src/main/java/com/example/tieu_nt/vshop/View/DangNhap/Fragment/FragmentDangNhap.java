package com.example.tieu_nt.vshop.View.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.vshop.Presenter.DangNhapDangKy.PresenterLogicDanhNhap;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.Model.TaiKhoan;
import com.example.tieu_nt.vshop.View.DangNhap.ViewDangNhap;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class FragmentDangNhap extends Fragment implements View.OnClickListener, ViewDangNhap{
    private CallbackManager callbackManager;
    private EditText edtEmail;
    private TextInputEditText edtMatKhau;
    private Button btnDangNhap, btnBoQua, btnDangNhapFB, btnDangNhapGG;
    private View view;
    private TaiKhoan taiKhoan;
    private AlertDialog.Builder builder;
    private PresenterLogicDanhNhap presenterDanhNhap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("kiemTra", "Thanh cong");
            }

            @Override
            public void onCancel() {
                Log.d("kiemTra", "Thoat");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("kiemTra", "Loi");
            }
        });
        anhXa();

        setActions();
        presenterDanhNhap = new PresenterLogicDanhNhap(this);

        //lay key hash cua app
//        // Add code to print out the key hash
//        try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
//                    "com.example.tieu_nt.vshop",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void anhXa(){
        edtEmail = (EditText) view.findViewById(R.id.edtEmailDangNhap);
        edtMatKhau = (TextInputEditText) view.findViewById(R.id.edtMatKhauDangNhap);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        btnBoQua = (Button) view.findViewById(R.id.btnBoQuaDangNhap);
        btnDangNhapFB = (Button) view.findViewById(R.id.btnDangNhapFB);
        btnDangNhapGG = (Button) view.findViewById(R.id.btnDangNhapGG);
    }

    private void setActions(){
        btnDangNhap.setOnClickListener(this);
        btnBoQua.setOnClickListener(this);
        btnDangNhapFB.setOnClickListener(this);
        btnDangNhapGG.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangNhap:
                presenterDanhNhap.kiemTraDangNhap(edtEmail.getText().toString(),  edtMatKhau.getText().toString());
                break;
            case R.id.btnBoQuaDangNhap:
                taiKhoan = new TaiKhoan();
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDangNhapFB:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGG:
                break;
        }
    }

    @Override
    public void dangNhapThanhCong(int idKhachHang) {
        Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
        iTrangChu.putExtra("idKhachHang", idKhachHang);
        startActivity(iTrangChu);
    }

    @Override
    public void dangNhapThatBai(String msg) {
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
