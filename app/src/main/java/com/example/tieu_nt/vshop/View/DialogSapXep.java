package com.example.tieu_nt.vshop.View;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 5/22/2018.
 */

public class DialogSapXep extends AlertDialog.Builder{
    private Context context;
    private ImageView[] imgSapXep = new ImageView[4];
    private RelativeLayout[] relaSapXep = new RelativeLayout[4];
    private int viTriSapXep = -1;
    private final String SAPXEP_SPMOI = "idSanPham", SAPXEP_GIA = "giaChuan", SAPXEP_DANHGIA = "soDanhGia", SAPXEP_GIAM = "DESC", SAPXEP_TANG = "ASC";
    private String sapXep = "", giaTri = "";
    private AlertDialog alertDialog;
    private SapXepSanPham sapXepSanPham;


    public DialogSapXep(Context context, SapXepSanPham sapXepSanPham){
        super(context);
        this.context = context;
        this.sapXepSanPham = sapXepSanPham;
    }

    @Override
    public AlertDialog show() {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_sapxep, null, false);
        relaSapXep[0] = view.findViewById(R.id.relaGiaCaoDenThap);
        relaSapXep[1] = view.findViewById(R.id.relaGiaThapDenCao);
        relaSapXep[2] = view.findViewById(R.id.relaSanPhamMoi);
        relaSapXep[3] = view.findViewById(R.id.relaDanhGia);

        imgSapXep[0] = view.findViewById(R.id.imgGiaCaoDenThap);
        imgSapXep[1] = view.findViewById(R.id.imgGiaThapDenCao);
        imgSapXep[2] = view.findViewById(R.id.imgSanPhamMoi);
        imgSapXep[3] = view.findViewById(R.id.imgDanhGia);

        Button btnHuy = (Button) view.findViewById(R.id.btnHuy);
        Button btnApDung = (Button) view.findViewById(R.id.btnApDung);
        setView(view);

        alertDialog = super.show();

        relaSapXep[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 0){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 0;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_GIA;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 1){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 1;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_GIA;
                    sapXep = SAPXEP_TANG;
                }
            }
        });

        relaSapXep[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 2){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 2;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_SPMOI;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 3){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 3;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_DANHGIA;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sapXepSanPham.sapXep(giaTri, sapXep);
            }
        });

        return alertDialog;
    }
}
