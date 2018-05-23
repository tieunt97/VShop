package com.example.tieu_nt.vshop.View.TrangChu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Adapter.AdapterDanhGia;
import com.example.tieu_nt.vshop.Adapter.AdapterViewPagerSlider;
import com.example.tieu_nt.vshop.ConnectInternet.DownloadHinhSanPham;
import com.example.tieu_nt.vshop.Model.ChiTietSanPham;
import com.example.tieu_nt.vshop.Model.DanhGia;
import com.example.tieu_nt.vshop.Model.NguoiDung;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicChiTietSanPham;
import com.example.tieu_nt.vshop.R;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 4/20/2018.
 */

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener,
        View.OnClickListener{
    private Toolbar toolbar;
    private ViewPager viewPagerSlider;
    private TextView tvSoAnh, tvTenSP, tvGia, tvSoDanhGia, tvDiaChi, tvChiTietSP, tvXemThem, tvSoSPGioHang,
            tvXemTatCaDanhGia, tvDanhGia5, tvDanhGia4, tvDanhGia3, tvDanhGia2, tvDanhGia1, tvDanhGiaTB, tvSoDanhGia1;
    private Button btnThemHang;
    private ImageView imgShare, imgThich;
    private RatingBar rbDanhGia, rbDanhGia1;
    private LinearLayout linearXemDanhGia;
    private ProgressBar pb5sao, pb4sao, pb3sao, pb2sao, pb1sao;
    private RecyclerView recyclerDanhGia;
    private Menu menu;
    private List<Fragment> fragmentHinhSP = new ArrayList<>();
    private PresenterLogicChiTietSanPham presenterChiTietSanPham;
    private PresenterLogicGioHang presenterLogicGioHang;
    private SanPham sanPham;
    private NguoiDung khachHang;
    private boolean xemThem = true;
    private int soSP = 0;
    private int soHinh = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        anhXa();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(this.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);

        khachHang = TrangChuActivity.nguoiDung;

        int idSanPham = getIntent().getIntExtra("idSanPham", 0);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterChiTietSanPham = new PresenterLogicChiTietSanPham(this, this);
        presenterChiTietSanPham.layChiTietSanPham(idSanPham);
        setActions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_giohang, menu);
        this.menu = menu;
        MenuItem iGioHang = menu.findItem(R.id.itemGioHang);
        View itemGioHang = MenuItemCompat.getActionView(iGioHang);
        tvSoSPGioHang = (TextView) itemGioHang.findViewById(R.id.tvSoSPGioHang);

        List<SanPham> dsSanPhamGioHang = presenterLogicGioHang.layDSSanPhamGioHang();
        if(dsSanPhamGioHang.size() == 0) {
            tvSoSPGioHang.setVisibility(View.GONE);
        }else{
            for(SanPham sp: dsSanPhamGioHang){
                soSP += sp.getSoLuong();
            }
            tvSoSPGioHang.setVisibility(View.VISIBLE);
            tvSoSPGioHang.setText(String.valueOf(soSP));
        }

        itemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivityForResult(iGioHang, TrangChuActivity.REQUEST_GIOHANG);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPagerSlider = (ViewPager) findViewById(R.id.viewPagerSlider);
        tvSoAnh = (TextView) findViewById(R.id.tvSoAnh);
        tvTenSP = (TextView) findViewById(R.id.tvTenSanPham);
        tvGia = (TextView) findViewById(R.id.tvGiaSP);
        tvSoDanhGia = (TextView) findViewById(R.id.tvSoDanhGia);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        tvChiTietSP = (TextView) findViewById(R.id.tvChiTietSanPham);
        tvXemThem = (TextView) findViewById(R.id.tvXemThem);
        linearXemDanhGia = (LinearLayout) findViewById(R.id.linearXemDanhGia);
        tvXemTatCaDanhGia = (TextView) findViewById(R.id.tvXemTatCaDanhGia);
        tvDanhGia5 = (TextView) findViewById(R.id.tvSoDanhGia5Sao);
        tvDanhGia4 = (TextView) findViewById(R.id.tvSoDanhGia4Sao);
        tvDanhGia3 = (TextView) findViewById(R.id.tvSoDanhGia3Sao);
        tvDanhGia2 = (TextView) findViewById(R.id.tvSoDanhGia2Sao);
        tvDanhGia1 = (TextView) findViewById(R.id.tvSoDanhGia1Sao);
        tvDanhGiaTB = (TextView) findViewById(R.id.tvDanhGiaTB);
        tvSoDanhGia1 = (TextView) findViewById(R.id.tvSoDanhGia1);
        btnThemHang = (Button) findViewById(R.id.btnThemVaoGioHang);
        rbDanhGia = (RatingBar) findViewById(R.id.rbDanhGia);
        rbDanhGia1 = (RatingBar) findViewById(R.id.rbDanhGia1);
        imgShare = (ImageView) findViewById(R.id.imgShare);
        imgThich = (ImageView) findViewById(R.id.imgThich);
        pb5sao = (ProgressBar) findViewById(R.id.pb5Sao);
        pb4sao = (ProgressBar) findViewById(R.id.pb4Sao);
        pb3sao = (ProgressBar) findViewById(R.id.pb3Sao);
        pb2sao = (ProgressBar) findViewById(R.id.pb2Sao);
        pb1sao = (ProgressBar) findViewById(R.id.pb1Sao);
        recyclerDanhGia = (RecyclerView) findViewById(R.id.recyclerDanhGia);
    }

    private void setActions(){
        imgShare.setOnClickListener(this);
        imgThich.setOnClickListener(this);
        linearXemDanhGia.setOnClickListener(this);
        btnThemHang.setOnClickListener(this);
    }

    @Override
    public void hienThiSliderSP(List<String> dsHinhSP) {
        soHinh = dsHinhSP.size();
        for (int i = 0; i < soHinh; i++){
            FragmentHinhSanPham fragmentHinhSanPham = new FragmentHinhSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkHinh", dsHinhSP.get(i));
            fragmentHinhSanPham.setArguments(bundle);

            fragmentHinhSP.add(fragmentHinhSanPham);
        }

        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragmentHinhSP);
        viewPagerSlider.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();
        tvSoAnh.setText("1/" + soHinh);
        viewPagerSlider.setOnPageChangeListener(this);
        changeImgYeuThich();
    }

    @Override
    public void hienThiChiTietSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
        tvTenSP.setText(sanPham.getTenSanPham());
        NumberFormat numberFormat =  new DecimalFormat("###,###");
        tvGia.setText(numberFormat.format(sanPham.getGiaChuan()) + " đ");
        if(sanPham.getSoLuotDanhGia() == 0){
            rbDanhGia.setRating((float) 5.0);
        }else{
            rbDanhGia.setRating(sanPham.getDanhGiaTB());
        }
        tvSoDanhGia.setText("(" + sanPham.getSoLuotDanhGia() + ")");
        if(khachHang != null){
            tvDiaChi.setText(khachHang.getDiaChi());
        }
        String chiTietSanPham = "&#8226 Thương hiệu: " + sanPham.getThuongHieu();
        for (ChiTietSanPham ctsp: sanPham.getDsChiTietSanPham()){
            chiTietSanPham +="<br/>&#8226 " + ctsp.getTenChiTiet() + ": " + ctsp.getGiaTri();
        }

        tvChiTietSP.setText(Html.fromHtml(chiTietSanPham));
        tvChiTietSP.post(new Runnable() {
            @Override
            public void run() {
                int line = tvChiTietSP.getLineCount();
                if(line > 2){
                    tvChiTietSP.setMaxLines(2);
                    tvXemThem.setVisibility(View.VISIBLE);
                    tvXemThem.setText("Xem thêm");
                    tvXemThem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (xemThem){
                                tvChiTietSP.setMaxLines(Integer.MAX_VALUE);
                                xemThem = !xemThem;
                                tvXemThem.setText("Thu lại");
                            }else{
                                tvChiTietSP.setMaxLines(2);
                                xemThem = !xemThem;
                                tvXemThem.setText("Xem thêm");
                            }
                        }
                    });
                }else{
                    tvXemThem.setVisibility(View.GONE);
                }
            }
        });

        if(sanPham.getDanhGiaTB() > 0){
            float danhGiaTb = (float) Math.round((sanPham.getDanhGiaTB()*10))/10;
            tvDanhGiaTB.setText(String.valueOf(danhGiaTb));
            rbDanhGia1.setRating(sanPham.getDanhGiaTB());
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
    public void hienThiDSDanhGia(List<DanhGia> dsDanhGia) {
        recyclerDanhGia.setLayoutManager(new LinearLayoutManager(this));
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, dsDanhGia);
        recyclerDanhGia.setAdapter(adapterDanhGia);
        adapterDanhGia.notifyDataSetChanged();
        if (sanPham.getSoLuotDanhGia() > 3){
            tvXemTatCaDanhGia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String text = tvSoAnh.getText().toString();
        tvSoAnh.setText(position + 1 + "/" + soHinh);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvThayDoi:
                break;
            case R.id.imgShare:
                break;
            case R.id.imgThich:
                capNhatSanPhamYeuThich();
                break;
            case R.id.linearXemDanhGia:
                Intent iDanhGia = new Intent(this, DanhGiaVaNhanXetActivity.class);
                iDanhGia.putExtra("sanPham", sanPham);
                startActivity(iDanhGia);
                break;
            case R.id.btnThemVaoGioHang:
                themSanPhamGioHang();
                break;
        }
    }

    private void capNhatSanPhamYeuThich(){
        if(khachHang == null){
            Toast.makeText(this, "Bạn cần đăng nhập để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
        }else{
            if(sanPham.isYeuThich()){
                if(presenterChiTietSanPham.themSanPhamYeuThich(sanPham.getIdSanPham())){
                    sanPham.setYeuThich(!sanPham.isYeuThich());
                    changeImgYeuThich();
                    Toast.makeText(this, "Đã thêm sản phẩm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }else{
                if(presenterChiTietSanPham.xoaSanPhamYeuThich(sanPham.getIdSanPham())){
                    sanPham.setYeuThich(!sanPham.isYeuThich());
                    changeImgYeuThich();
                    Toast.makeText(this, "Đã xóa sản phẩm khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void themSanPhamGioHang(){
        DownloadHinhSanPham downloadHinhSanPham = new DownloadHinhSanPham(sanPham.getHinhSanPham());
        downloadHinhSanPham.execute();
        Bitmap bitmap = null;
        try {
            bitmap = downloadHinhSanPham.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if(bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            sanPham.setHinhSPGioHang(outputStream.toByteArray());
            sanPham.setSoLuong(1);
        }
        if(presenterLogicGioHang.themSanPhamGioHang(sanPham)){
            if(soSP == 0){
                tvSoSPGioHang.setVisibility(View.VISIBLE);
            }
            soSP += 1;
            tvSoSPGioHang.setText(String.valueOf(soSP));
            Toast.makeText(this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeImgYeuThich(){
        if (sanPham.isYeuThich()){
            imgThich.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.like_true));
        }else {
            imgThich.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.like_false));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == TrangChuActivity.REQUEST_GIOHANG){
            int soSP = data.getIntExtra("soSP", 0);
            if(soSP == 0){
                tvSoSPGioHang.setVisibility(View.GONE);
            }
            tvSoSPGioHang.setText(String.valueOf(soSP));
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("soSP", soSP);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
