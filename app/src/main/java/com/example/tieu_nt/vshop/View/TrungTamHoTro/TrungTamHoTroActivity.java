package com.example.tieu_nt.vshop.View.TrungTamHoTro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Adapter.AdapterMenu;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.MainActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 5/9/2018.
 */

public class TrungTamHoTroActivity extends MainActivity implements View.OnClickListener{
    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterMenu adapterMenu;
    private CircleImageView imgInfo;
    private RelativeLayout relaCall, relaSendMail, relaHuongDanSD, relaChat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trungtamhotro);
        anhXa();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(TrungTamHoTroActivity.this, drawerLayout, R.string.open, R.string.close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                trangChu.setTranslationX(slideOffset * drawerView.getWidth());
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        adapterMenu = new AdapterMenu(TrungTamHoTroActivity.this, drawerLayout, 5);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMenu);

        setActions();
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.trangChu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imgInfo = (CircleImageView) findViewById(R.id.imgInfo);
        relaHuongDanSD = (RelativeLayout) findViewById(R.id.relaHuongDanSD);
        relaChat = (RelativeLayout) findViewById(R.id.relaChat);
        relaCall = (RelativeLayout) findViewById(R.id.relaCall);
        relaSendMail = (RelativeLayout) findViewById(R.id.relaSendMail);
    }

    private void setActions(){
        relaHuongDanSD.setOnClickListener(this);
        relaChat.setOnClickListener(this);
        relaCall.setOnClickListener(this);
        relaSendMail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.relaHuongDanSD:
                break;
            case R.id.relaChat:
                break;
            case R.id.relaCall:
                Intent iCall = new Intent(Intent.ACTION_CALL);
                iCall.setData(Uri.parse("tel:0123456789"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(iCall);
                break;
            case R.id.relaSendMail:
                Intent iSendMail = new Intent(Intent.ACTION_SEND);
                iSendMail.setType("message/rfc822");
                iSendMail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"tieunt.bk97@gmail.com"});
                iSendMail.putExtra(Intent.EXTRA_SUBJECT, "Hỗ trợ người dùng VShop");
                try{
                    startActivity(iSendMail);
                }catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(TrungTamHoTroActivity.this, "Bạn cần cài đặt Gmail của Google để sử dụng chức năng này", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
