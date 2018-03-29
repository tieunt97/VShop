package com.example.tieu_nt.vshop.View.DangNhap.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.Model.TaiKhoan;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tieu_nt on 3/16/2018.
 */

public class FragmentDangNhap extends Fragment implements View.OnClickListener{
    private CallbackManager callbackManager;
    private EditText edtEmail;
    private TextInputEditText edtMatKhau;
    private Button btnDangNhap, btnBoQua, btnDangNhapFB, btnDangNhapGG;
    private View view;
    private TaiKhoan taiKhoan;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


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
                dangNhap();
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

    private void dangNhap(){
        String email = edtEmail.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        String msg = "";

        if(email.equals("") && matKhau.equals("")){
            msg = "Bạn chưa nhập Email và Mật khẩu";
        }else if(email.equals("")){
            msg = "Bạn chưa nhập Email";
        }else if(matKhau.equals("")){
            msg = "Bạn chưa nhập Mật khẩu";
        }else if(!validate(email)){
            msg = "Email không hợp lệ";
        }else if(matKhau.length() < 6){
            msg = "Mật khẩu không hợp lệ , ít nhất 6 ký tự";
        }

        if(!msg.equals("")){
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

}
