package com.example.tieu_nt.vshop.View.TrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.vshop.Adapter.AdapterViewPagerSlider;
import com.example.tieu_nt.vshop.Model.KhachHang;
import com.example.tieu_nt.vshop.Model.Data.ModelKhachHang;
import com.example.tieu_nt.vshop.Model.SanPham;
import com.example.tieu_nt.vshop.Presenter.SanPham.PresenterLogicChiTietSanPham;
import com.example.tieu_nt.vshop.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 4/20/2018.
 */

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener,
        View.OnClickListener{
    private Toolbar toolbar;
    private ViewPager viewPagerSlider;
    private TextView tvSoAnh, tvTenSP, tvGia, tvSoDanhGia, tvDiaChi, tvThayDoiDiaChi, tvChiTietSP, tvXemThem,
            tvXemTatCaDanhGia, tvDanhGia5, tvDanhGia4, tvDanhGia3, tvDanhGia2, tvDanhGia1, tvDanhGiaTB, tvSoDanhGia1;
    private Button btnThemHang;
    private ImageView imgShare, imgThich;
    private RatingBar rbDanhGia, rbDanhGia1;
    private ProgressBar pb5sao, pb4sao, pb3sao, pb2sao, pb1sao;
    private RecyclerView recyclerDanhGia;
    private List<Fragment> fragmentHinhSP = new ArrayList<>();
    private PresenterLogicChiTietSanPham presenterChiTietSanPham;
    private SanPham sanPham;
    private KhachHang khachHang;
    private ModelKhachHang modelKhachHang;
    private boolean xemThem = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        anhXa();
        modelKhachHang = ModelKhachHang.getInstance();
        try{
            khachHang = TrangChuActivity.khachHang;
        }catch (NullPointerException e){
            Log.d("DangNhap", "Chưa đăng nhập");
        }

        setSupportActionBar(toolbar);
        sanPham = (SanPham) getIntent().getSerializableExtra("sanPham");
        presenterChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterChiTietSanPham.layChiTietSanPham(sanPham.getIdSanPham());
        setActions();
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPagerSlider = (ViewPager) findViewById(R.id.viewPagerSlider);
        tvSoAnh = (TextView) findViewById(R.id.tvSoAnh);
        tvTenSP = (TextView) findViewById(R.id.tvTenSanPham);
        tvGia = (TextView) findViewById(R.id.tvGiaSP);
        tvSoDanhGia = (TextView) findViewById(R.id.tvSoDanhGia);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        tvThayDoiDiaChi = (TextView) findViewById(R.id.tvThayDoi);
        tvChiTietSP = (TextView) findViewById(R.id.tvChiTietSanPham);
        tvXemThem = (TextView) findViewById(R.id.tvXemThem);
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
    }

    private void setActions(){
        imgShare.setOnClickListener(this);
        imgThich.setOnClickListener(this);
        tvThayDoiDiaChi.setOnClickListener(this);
        btnThemHang.setOnClickListener(this);
    }

    @Override
    public void hienThiSliderSP(List<String> dsHinhSP) {
        int soHinh = dsHinhSP.size();
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
        if (sanPham.isYeuThich()){
            imgThich.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.like_true));
        }else {
            imgThich.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.like_false));
        }
    }

    @Override
    public void hienThiChiTietSanPham(SanPham sanPham) {
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
        tvChiTietSP.setText(sanPham.getMoTa());
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
                }
            }
        });

        if(sanPham.getDanhGiaTB() > 0){
            float danhGiaTb = (float) Math.round((sanPham.getDanhGiaTB()*10)/10);
            tvDanhGiaTB.setText(String.valueOf(danhGiaTb));
            rbDanhGia1.setRating(danhGiaTb);
        }else {
            tvDanhGiaTB.setText(String.valueOf(5.0));
            rbDanhGia.setRating((float) 5.0);
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        String text = tvSoAnh.getText().toString();
        tvSoAnh.setText(position + 1 + text.substring(1));
    }

    @Override
    public void onPageSelected(int position) {

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
                if (khachHang != null){
                    sanPham.setYeuThich(!sanPham.isYeuThich());
                    modelKhachHang.capNhatSanPhamYeuThich(khachHang.getIdKhachHang(), sanPham.getIdSanPham());
                }
                break;
            case R.id.btnThemVaoGioHang:
                if (khachHang  != null){
                    if(modelKhachHang.themSanPhamGioHang(khachHang.getIdKhachHang(), sanPham.getIdSanPham()))
                        Toast.makeText(this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Sản phẩm đã được thêm trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
