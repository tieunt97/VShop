package com.example.tieu_nt.vshop.View.TrangChu;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Adapter.AdapterDanhGia;
import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.vshop.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.vshop.Model.LoadMore.TrangDanhGia;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicDanhGiaSanPham;
import com.example.tieu_nt.vshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public class DanhGiaVaNhanXetActivity extends AppCompatActivity implements View.OnClickListener,
ViewHienThiDanhSachDanhGia, ILoadMore{
    private Toolbar toolbar;
    private ImageView imgHinhSP;
    private TextView tvTenSP, tvDanhGiaSanPham, tvDanhGia5, tvDanhGia4, tvDanhGia3, tvDanhGia2, tvDanhGia1,
            tvDanhGiaTB, tvSoDanhGia1;
    private ProgressBar pb5sao, pb4sao, pb3sao, pb2sao, pb1sao;
    private RatingBar rbDanhGia1;
    private SanPham sanPham;
    private TrangDanhGia trangDanhGia;
    private List<DanhGia> dsDanhGia;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerDanhGia;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterDanhGia adapterDanhGia;
    private PresenterLogicDanhGiaSanPham presenterLogicDanhGiaSanPham;
    private String duongDan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgia_nhanxet);
        anhXa();

        layoutManager = new LinearLayoutManager(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Đánh giá & Nhận xét");
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        sanPham = (SanPham) getIntent().getSerializableExtra("sanPham");
        duongDan = TrangChuActivity.SERVER + "/products/" + sanPham.getIdSanPham() + "/allValuations";
        hienThiThongTinSanPham();

        setAction();

        presenterLogicDanhGiaSanPham = new PresenterLogicDanhGiaSanPham(this);
        presenterLogicDanhGiaSanPham.layDanhSachDanhGia(duongDan);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterLogicDanhGiaSanPham.layDanhSachDanhGia(duongDan);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgHinhSP = (ImageView) findViewById(R.id.imgHinhSP);
        tvTenSP = (TextView) findViewById(R.id.tvTenSanPham);
        tvDanhGiaSanPham = (TextView) findViewById(R.id.tvDanhGiaSanPham);
        tvDanhGia5 = (TextView) findViewById(R.id.tvSoDanhGia5Sao);
        tvDanhGia4 = (TextView) findViewById(R.id.tvSoDanhGia4Sao);
        tvDanhGia3 = (TextView) findViewById(R.id.tvSoDanhGia3Sao);
        tvDanhGia2 = (TextView) findViewById(R.id.tvSoDanhGia2Sao);
        tvDanhGia1 = (TextView) findViewById(R.id.tvSoDanhGia1Sao);
        tvDanhGiaTB = (TextView) findViewById(R.id.tvDanhGiaTB);
        tvSoDanhGia1 = (TextView) findViewById(R.id.tvSoDanhGia1);
        rbDanhGia1 = (RatingBar) findViewById(R.id.rbDanhGia1);
        pb5sao = (ProgressBar) findViewById(R.id.pb5Sao);
        pb4sao = (ProgressBar) findViewById(R.id.pb4Sao);
        pb3sao = (ProgressBar) findViewById(R.id.pb3Sao);
        pb2sao = (ProgressBar) findViewById(R.id.pb2Sao);
        pb1sao = (ProgressBar) findViewById(R.id.pb1Sao);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerDanhGia = (RecyclerView) findViewById(R.id.recyclerDanhGia);
    }

    private void setAction(){
        tvDanhGiaSanPham.setOnClickListener(this);
    }

    private void hienThiThongTinSanPham(){
        Picasso.get().load(sanPham.getHinhSanPham()).into(imgHinhSP);
        tvTenSP.setText(sanPham.getTenSanPham());
        if(sanPham.getDanhGiaTB() > 0){
            float danhGiaTb = (float) Math.round((sanPham.getDanhGiaTB()*10))/10;
            tvDanhGiaTB.setText(String.valueOf(danhGiaTb));
            rbDanhGia1.setRating(danhGiaTb);
        }else {
            tvDanhGiaTB.setText(String.valueOf(5.0));
            rbDanhGia1.setRating((float) 5.0);
        }
        tvSoDanhGia1.setText(sanPham.getSoLuotDanhGia() + " Đánh giá");

        int soDanhGia = sanPham.getSoLuotDanhGia();
        if(soDanhGia > 0){
            int[] dsSoSao = sanPham.getDsSoSao();
            pb1sao.setMax(soDanhGia);
            pb1sao.setProgress(dsSoSao[4]);
            tvDanhGia1.setText(String.valueOf(dsSoSao[4]));
            pb2sao.setMax(soDanhGia);
            pb2sao.setProgress(dsSoSao[3]);
            tvDanhGia2.setText(String.valueOf(dsSoSao[3]));
            pb3sao.setMax(soDanhGia);
            pb3sao.setProgress(dsSoSao[2]);
            tvDanhGia3.setText(String.valueOf(dsSoSao[2]));
            pb4sao.setMax(soDanhGia);
            pb4sao.setProgress(dsSoSao[1]);
            tvDanhGia4.setText(String.valueOf(dsSoSao[1]));
            pb5sao.setMax(soDanhGia);
            pb5sao.setProgress(dsSoSao[0]);
            tvDanhGia5.setText(String.valueOf(dsSoSao[0]));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvDanhGiaSanPham:
                Toast.makeText(this, "Đánh giá sản phẩm", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void loadMore(String duongDan) {
        trangDanhGia = presenterLogicDanhGiaSanPham.layDSDanhGiaLoadMore(duongDan);
        if(trangDanhGia.getDsDanhGia().size() > 0){
            dsDanhGia.addAll(trangDanhGia.getDsDanhGia());
            recyclerDanhGia.post(new Runnable() {
                @Override
                public void run() {
                    adapterDanhGia.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void hienThiDanhSachDanhGia(TrangDanhGia trangDanhGia) {
        this.trangDanhGia = trangDanhGia;
        this.dsDanhGia = this.trangDanhGia.getDsDanhGia();
        recyclerDanhGia.setLayoutManager(layoutManager);
        adapterDanhGia = new AdapterDanhGia(this, this.dsDanhGia);
        recyclerDanhGia.setAdapter(adapterDanhGia);
        recyclerDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager, this, this.trangDanhGia.isTrangCuoi(),
                this.trangDanhGia.getNextPage()));
        adapterDanhGia.notifyDataSetChanged();
    }
}
