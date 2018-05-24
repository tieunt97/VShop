package com.example.tieu_nt.vshop.View;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/24/2018.
 */

public class DialogTimKiem extends AlertDialog.Builder{
    private Context context;
    private TimKiemSanPham timKiemSanPham;
    private AlertDialog alertDialog;


    public DialogTimKiem(@NonNull Context context, TimKiemSanPham timKiemSanPham) {
        super(context);
        this.context = context;
        this.timKiemSanPham = timKiemSanPham;
    }

    @Override
    public AlertDialog show() {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_timkiem, null, false);
        final EditText edtTimKiem = view.findViewById(R.id.edtTimKiem);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        Button btnTimKiem = view.findViewById(R.id.btnTimKiem);
        setView(view);
        alertDialog = super.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                timKiemSanPham.timKiemSanPham(edtTimKiem.getText().toString());
            }
        });
        return alertDialog;
    }
}
