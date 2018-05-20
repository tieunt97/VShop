package com.example.tieu_nt.vshop.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tieu_nt.vshop.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 5/17/2018.
 */

public class MainActivity extends AppCompatActivity{
    public static int IMG_GALLERY_REQUEST = 1;
    protected CircleImageView imgInfo;

    protected void selectImage(){
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, IMG_GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == IMG_GALLERY_REQUEST){
                Uri uri = data.getData();
                imgInfo.setImageURI(uri);
            }
        }
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_thongbao_xacnhan, null);
        Button btnHuy = (Button) view1.findViewById(R.id.btnHuy);
        Button btnDongY = (Button) view1.findViewById(R.id.btnDongY);
        btnDongY.setText("Thoát");
        TextView tvNoiDung = (TextView) view1.findViewById(R.id.tvNoiDung);
        tvNoiDung.setText("Bạn có chắc muốn thoát ứng dụng?");

        builder.setView(view1);
        final AlertDialog dialogCloseApp = builder.create();
        dialogCloseApp.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCloseApp.dismiss();
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCloseApp.dismiss();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
