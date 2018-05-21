package com.example.tieu_nt.vshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.tieu_nt.vshop.Model.ThuongHieu;
import com.example.tieu_nt.vshop.R;
import com.example.tieu_nt.vshop.View.TrangChu.HienThiSanPhamTheoThuongHieuActivity;
import com.example.tieu_nt.vshop.View.TrangChu.TrangChuActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tieu_nt on 4/19/2018.
 */

public class AdapterThuongHieu extends RecyclerView.Adapter<AdapterThuongHieu.ViewHolder>{
    private Context context;
    private List<ThuongHieu> dsThuongHieu;

    public AdapterThuongHieu(Context context, List<ThuongHieu> dsThuongHieu) {
        this.context = context;
        this.dsThuongHieu = dsThuongHieu;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_thuonghieu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ThuongHieu thuongHieu = dsThuongHieu.get(position);
        Picasso.get().load(thuongHieu.getHinhThuongHieu()).into(holder.imgHinhThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBarDownload.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        holder.frameThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iHienThiSPTheoThuongHieu = new Intent(context, HienThiSanPhamTheoThuongHieuActivity.class);
                iHienThiSPTheoThuongHieu.putExtra("thuongHieu", thuongHieu);
                ((Activity) context).startActivityForResult(iHienThiSPTheoThuongHieu, TrangChuActivity.REQUEST_THUONGHIEU);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsThuongHieu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameThuongHieu;
        ImageView imgHinhThuongHieu;
        ProgressBar progressBarDownload;
        public ViewHolder(View itemView) {
            super(itemView);
            frameThuongHieu = (FrameLayout) itemView.findViewById(R.id.frameThuongHieu);
            imgHinhThuongHieu = (ImageView) itemView.findViewById(R.id.imgHinhThuongHieu);
            progressBarDownload = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
        }
    }
}
